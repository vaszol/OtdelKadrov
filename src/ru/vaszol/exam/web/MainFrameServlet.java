package ru.vaszol.exam.web;

/**
 * Created by vas on 09.04.15.
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.vaszol.exam.logic.Group;
import ru.vaszol.exam.logic.ManagementSystem;
import ru.vaszol.exam.logic.Student;

import ru.vaszol.exam.web.forms.MainFrameForm;
import ru.vaszol.exam.web.forms.StudentForm;

public class MainFrameServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Установка кодировки для принятия параметров
        req.setCharacterEncoding("UTF-8");
        int answer = 0;
        try {
            answer = checkAction(req);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }
        if (answer == 1) {
            // Тут надо сделать вызов другой формы, которая перенаправит сервлет
            // на другую JSP для ввода данных о новом студенте
            try {
                Student s = new Student();
                s.setStudentId(0);
                s.setDateOfBirth(new Date());
                s.setEducationYear(Calendar.getInstance().get(Calendar.YEAR));
                Collection groups = ManagementSystem.getInstance().getGroups();
                StudentForm sForm = new StudentForm();
                sForm.initFromStudent(s);
                sForm.setGroups(groups);
                req.setAttribute("student", sForm);
                getServletContext().getRequestDispatcher("/StudentFrame.jsp").forward(req, resp);
                return;
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }

        }

        if (answer == 2) {
            // Тут надо сделать вызов другой формы, которая перенаправит сервлет
            // на другую JSP для ввода данных о студенте
            try {
                if (req.getParameter("studentId") != null) {
                    int stId = Integer.parseInt(req.getParameter("studentId"));
                    Student s = ManagementSystem.getInstance().getStudentById(stId);
                    Collection groups = ManagementSystem.getInstance().getGroups();
                    StudentForm sForm = new StudentForm();
                    sForm.initFromStudent(s);
                    sForm.setGroups(groups);
                    req.setAttribute("student", sForm);
                    getServletContext().getRequestDispatcher("/StudentFrame.jsp").forward(req, resp);
                    return;
                }
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }
        }
        String gs = req.getParameter("groupId");
        String ys = req.getParameter("year");

        if (answer == 3) {
            // Здесь мы перемещаем стедунтов в другую группу
            String newGs = req.getParameter("newGroupId");
            String newYs = req.getParameter("newYear");
            try {
                Group g = new Group();
                g.setGroupId(Integer.parseInt(gs));
                Group ng = new Group();
                ng.setGroupId(Integer.parseInt(newGs));
                ManagementSystem.getInstance().moveStudentsToGroup(g, Integer.parseInt(ys), ng, Integer.parseInt(newYs));
                // Теперь мы будем показывать группу, куда переместили
                gs = newGs;
                ys = newYs;
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }
        }
        int groupId = -1;
        if (gs != null) {
            groupId = Integer.parseInt(gs);
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (ys != null) {
            year = Integer.parseInt(ys);
        }
        MainFrameForm form = new MainFrameForm();
        try {
            Collection groups = ManagementSystem.getInstance().getGroups();
            Group g = new Group();
            g.setGroupId(groupId);
            if (groupId == -1) {
                Iterator i = groups.iterator();
                g = (Group) i.next();
            }
            Collection students = ManagementSystem.getInstance().getStudentsFromGroup(g, year);
            form.setGroupId(g.getGroupId());
            form.setYear(year);
            form.setGroups(groups);
            form.setStudents(students);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }

        req.setAttribute("form", form);
        getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }

    // Здесь мы проверям какое действие нам надо сделать – и возвращаем ответ
    private int checkAction(HttpServletRequest req) throws SQLException {
        if (req.getParameter("Add") != null) {
            return 1;
        }
        if (req.getParameter("Edit") != null) {
            return 2;
        }
        if (req.getParameter("MoveGroup") != null) {
            return 3;
        }
        if (req.getParameter("Delete") != null) {
            if (req.getParameter("studentId") != null) {
                Student s = new Student();
                s.setStudentId(Integer.parseInt(req.getParameter("studentId")));
                ManagementSystem.getInstance().deleteStudent(s);
            }
            return 0;
        }
        return 0;
    }

    // Переопределим стандартные методы
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}