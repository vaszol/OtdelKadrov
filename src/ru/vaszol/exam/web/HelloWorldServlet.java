package ru.vaszol.exam.web;

/**
 * Created by vas on 05.04.15.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.vaszol.exam.logic.Group;
import ru.vaszol.exam.logic.ManagementSystem;

public class HelloWorldServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.println("<B>Список групп</B>");
        pw.println("<table border=1>");
        try {
            List<Group> l = ManagementSystem.getInstance().getGroups();
            for (Group gr : l) {
                pw.println("<tr>");
                pw.println("<td>" + gr.getGroupId() + "</td>");
                pw.println("<td>" + gr.getNameGroup() + "</td>");
                pw.println("<td>" + gr.getCurator() + "</td>");
                pw.println("<td>" + gr.getSpeciality() + "</td>");
                pw.println("</tr>");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        pw.println("</table>");
    }
}