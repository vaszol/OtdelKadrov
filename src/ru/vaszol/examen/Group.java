package ru.vaszol.examen;

/**
 * Created by vas on 03.04.2015.
 */
public class Group {

    // поле ИД ГРУППЫ
    private int groupId;
    // поле ИМЯ ГРУППЫ
    private String nameGroup;
    // поле КУРАТОР
    private String curator;
    // поле СПЕЦИАЛЬНОСТЬ
    private String speciality;

    // get/set для КУРАТОР
    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    // get/set для ИД ГРУППЫ
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    // get/set для ИМЯ ГРУППЫ
    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    // get/set для СПЕЦИАЛЬНОСТЬ
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String toString() {
        return nameGroup;
    }
}