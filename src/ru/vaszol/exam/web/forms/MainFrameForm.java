package ru.vaszol.exam.web.forms;

/**
 * Created by vas on 09.04.15.
 */
import java.util.Collection;

public class MainFrameForm
{
    private int year;
    private int groupId;
    private Collection groups;
    private Collection students;

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroups(Collection groups) {
        this.groups = groups;
    }

    public Collection getGroups() {
        return groups;
    }

    public void setStudents(Collection students) {
        this.students = students;
    }

    public Collection getStudents() {
        return students;
    }
}