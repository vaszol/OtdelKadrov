package ru.vaszol.examen.logic;

/**
 * Created by vas on 03.04.2015.
 */
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class ManagementSystem {

    private List<Group> groups;
    private Collection<Student> students;

    // Для шаблона Singletone статическая переменная
    private static ManagementSystem instance;

    // закрытый конструктор
    private ManagementSystem() {
        loadGroups();
        loadStudents();
    }

    // метод getInstance - проверяет, инициализирована ли статическая
    // переменная (в случае надобности делает это) и возвращает ее
    public static synchronized ManagementSystem getInstance() {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }

    // Метод, который вызывается при запуске класса (отключен)
    /**
    public static void main(String[] args) {
        // Этот код позволяет нам перенаправить стандартный вывод в файл
        // Т.к. на экран выводится не совсем удобочитаемая кодировка,
        // файл в данном случае более удобен
        try {
            System.setOut(new PrintStream("out.txt","utf-8"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ManagementSystem ms = ManagementSystem.getInstance();

        // Просмотр полного списка групп
        printString("Полный список групп");
        printString("*******************");
        List<Group> allGroups = ms.getGroups();
        for (Group gi : allGroups) {
            printString(gi);
        }
        printString();

        // Просмотр полного списка студентов
        printString("Полный список студентов");
        printString("***********************");
        Collection<Student> allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        // Вывод списков студентов по группам
        printString("Список студентов по группам");
        printString("***************************");
        List<Group> groups = ms.getGroups();
        // Проверяем все группы
        for (Group gi : groups) {
            printString("---> Группа:" + gi.getNameGroup());
            // Получаем список студентов для конкретной группы
            Collection<Student> students = ms.getStudentsFromGroup(gi, 2006);
            for (Student si : students) {
                printString(si);
            }
        }
        printString();

        // Создадим нового студента и добавим его в список
        Student s = new Student();
        s.setStudentId(5);
        s.setFirstName("Игорь");
        s.setPatronymic("Владимирович");
        s.setSurName("Перебежкин");
        s.setSex('М');
        Calendar c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        printString("Добавление студента:" + s);
        printString("********************");
        ms.insertStudent(s);
        printString("--->> Полный список студентов после добавления");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        // Изменим данные о студенте - Перебежкин станет у нас Новоперебежкиным
        // Но все остальное будет таким же - создаем студента с таким же ИД
        s = new Student();
        s.setStudentId(5);
        s.setFirstName("Игорь");
        s.setPatronymic("Владимирович");
        s.setSurName("Новоперебежкин");
        s.setSex('М');
        c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        printString("Редактирование данных студента:" + s);
        printString("*******************************");
        ms.updateStudent(s);
        printString("--->> Полный список студентов после редактирования");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        // Удалим нашего студента
        printString("Удаление студента:" + s);
        printString("******************");
        ms.deleteStudent(s);
        printString("--->> Полный список студентов после удаления");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        // Здесь мы переводим всех студентов одной группы в другую
        // Мы знаем, что у нас 2 группы
        // Не совсем элегантное решение, но пока сделаем так
        Group g1 = groups.get(0);
        Group g2 = groups.get(1);
        printString("Перевод студентов из 1-ой во 2-ю группу");
        printString("***************************************");
        ms.moveStudentsToGroup(g1, 2006, g2, 2007);
        printString("--->> Полный список студентов после перевода");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        // Удаляем студентов из группы
        printString("Удаление студентов из группы:" + g2 + " в 2006 году");
        printString("*****************************");
        ms.removeStudentsFromGroup(g2, 2006);
        printString("--->> Полный список студентов после удаления");
        allStudends = ms.getAllStudents();
        for (Iterator i = allStudends.iterator(); i.hasNext();) {
            printString(i.next());
        }
        printString();
    }
*/
    // Метод создает две группы и помещает их в коллекцию для групп
    public void loadGroups() {
        // Проверяем - может быть наш список еще не создан вообще
        if (groups == null) {
            groups = new ArrayList<Group>();
        } else {
            groups.clear();
        }
        Group g = null;

        g = new Group();
        g.setGroupId(1);
        g.setNameGroup("Первая");
        g.setCurator("Доктор Борменталь");
        g.setSpeciality("Создание собачек из человеков");
        groups.add(g);

        g = new Group();
        g.setGroupId(2);
        g.setNameGroup("Вторая");
        g.setCurator("Профессор Преображенский");
        g.setSpeciality("Создание человеков из собачек");
        groups.add(g);
    }

    // Метод создает несколько студентов и помещает их в коллекцию
    public void loadStudents() {
        if (students == null) {
            // Мы используем коллекцию, которая автоматически сортирует свои элементы
            students = new TreeSet<Student>();
        } else {
            students.clear();
        }

        Student s = null;
        Calendar c = Calendar.getInstance();

        // Вторая группа
        s = new Student();
        s.setStudentId(1);
        s.setFirstName("Иван");
        s.setPatronymic("Сергеевич");
        s.setSurName("Степанов");
        s.setSex('М');
        c.set(1990, 3, 20);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);

        s = new Student();
        s.setStudentId(2);
        s.setFirstName("Наталья");
        s.setPatronymic("Андреевна");
        s.setSurName("Чичикова");
        s.setSex('Ж');
        c.set(1990, 6, 10);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);

        // Первая группа
        s = new Student();
        s.setStudentId(3);
        s.setFirstName("Петр");
        s.setPatronymic("Викторович");
        s.setSurName("Сушкин");
        s.setSex('М');
        c.set(1991, 3, 12);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);

        s = new Student();
        s.setStudentId(4);
        s.setFirstName("Вероника");
        s.setPatronymic("Сергеевна");
        s.setSurName("Ковалева");
        s.setSex('Ж');
        c.set(1991, 7, 19);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);
    }

    // Получить список групп
    public List<Group> getGroups() {
        return groups;
    }

    // Получить список всех студентов
    public Collection<Student> getAllStudents() {
        return students;
    }

    // Получить список студентов для определенной группы
    public Collection<Student> getStudentsFromGroup(Group group, int year) {
        Collection<Student> l = new TreeSet<Student>();
        for (Student si : students) {
            if (si.getGroupId() == group.getGroupId() && si.getEducationYear() == year) {
                l.add(si);
            }
        }
        return l;
    }

    // Перевести студентов из одной группы с одним годом обучения в другую группу с другим годом обучения
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) {
        for (Student si : students) {
            if (si.getGroupId() == oldGroup.getGroupId() && si.getEducationYear() == oldYear) {
                si.setGroupId(newGroup.getGroupId());
                si.setEducationYear(newYear);
            }
        }
    }

    // Удалить всех студентов из определенной группы
    public void removeStudentsFromGroup(Group group, int year) {
        // Мы создадим новый список студентов БЕЗ тех, кого мы хотим удалить.
        // Возможно не самый интересный вариант. Можно было бы продемонстрировать
        // более элегантный метод, но он требует погрузиться в коллекции более глубоко
        // Здесь мы не ставим себе такую цель
        Collection<Student> tmp = new TreeSet<Student>();
        for (Student si : students) {
            if (si.getGroupId() != group.getGroupId() || si.getEducationYear() != year) {
                tmp.add(si);
            }
        }
        students = tmp;
    }

    // Добавить студента
    public void insertStudent(Student student) {
        // Просто добавляем объект в коллекцию
        students.add(student);
    }

    // Обновить данные о студенте
    public void updateStudent(Student student) {
        // Надо найти нужного студента (по его ИД) и заменить поля
        Student updStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                // Вот этот студент - запоминаем его и прекращаем цикл
                updStudent = si;
                break;
            }
        }
        updStudent.setFirstName(student.getFirstName());
        updStudent.setPatronymic(student.getPatronymic());
        updStudent.setSurName(student.getSurName());
        updStudent.setSex(student.getSex());
        updStudent.setDateOfBirth(student.getDateOfBirth());
        updStudent.setGroupId(student.getGroupId());
        updStudent.setEducationYear(student.getEducationYear());
    }

    // Удалить студента
    public void deleteStudent(Student student) {
        // Надо найти нужного студента (по его ИД) и удалить
        Student delStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                // Вот этот студент - запоминаем его и прекращаем цикл
                delStudent = si;
                break;
            }
        }
        students.remove(delStudent);
    }

    // Этот код позволяет нам изменить кодировку
    // Такое может произойти если используется IDE - например NetBeans.
    // Тогда вы получаете просто одни вопросы, что крайне неудобно читать
    public static void printString(Object s) {
        //System.out.println(s.toString());
        try {
//            System.out.println(new String(s.toString().getBytes("windows-1251"), "windows-1252"));
            System.out.println(new String(s.toString().getBytes("utf-8"), "utf-8"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    public static void printString() {
        System.out.println();
    }
}