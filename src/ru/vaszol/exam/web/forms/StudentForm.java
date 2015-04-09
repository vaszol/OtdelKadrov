package ru.vaszol.exam.web.forms;

/**
 * Created by vas on 09.04.15.
 */
import java.text.SimpleDateFormat;
import java.util.Collection;
import ru.vaszol.exam.logic.Student;

public class StudentForm
{
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    private int studentId;
    private String firstName;
    private String surName;
    private String patronymic;
    private String dateOfBirth;
    private int sex;
    private int groupId;
    private int educationYear;
    private Collection groups;

    public void initFromStudent(Student st) {
        this.studentId = st.getStudentId();
        this.firstName = st.getFirstName();
        this.surName = st.getSurName();
        this.patronymic = st.getPatronymic();
        this.dateOfBirth = sdf.format(st.getDateOfBirth());
        if (st.getSex() == 'М') {
            this.sex = 0;
        } else {
            this.sex = 1;
        }
        this.groupId = st.getGroupId();
        this.educationYear = st.getEducationYear();
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getEducationYear() {
        return educationYear;
    }

    public void setEducationYear(int educationYear) {
        this.educationYear = educationYear;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setGroups(Collection groups) {
        this.groups = groups;
    }

    public Collection getGroups() {
        return groups;
    }
}