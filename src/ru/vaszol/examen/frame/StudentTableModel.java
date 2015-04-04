package ru.vaszol.examen.frame;

import javax.swing.table.AbstractTableModel;

/**
 * Created by vas on 04.04.15.
 */
import java.text.DateFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import ru.vaszol.examen.logic.Student;

public class StudentTableModel extends AbstractTableModel {
    // Сделаем хранилище для нашего списка студентов

    private Vector students;

    // Модель при создании получает список студентов
    public StudentTableModel(Vector students) {
        this.students = students;
    }

    // Количество строк равно числу записей
    public int getRowCount() {
        if (students != null) {
            return students.size();
        }
        return 0;
    }

    // Количество столбцов - 4. Фамилия, Имя, Отчество, Дата рождения
    public int getColumnCount() {
        return 4;
    }

    // Вернем наименование колонки
    public String getColumnName(int column) {
        String[] colNames = {"Фамилия", "Имя", "Отчество", "Дата"};
        return colNames[column];
    }

    // Возвращаем данные для определенной строки и столбца
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (students != null) {
            // Получаем из вектора студента
            Student st = (Student) students.get(rowIndex);
            // В зависимости от колонки возвращаем имя, фамилия и т.д.
            switch (columnIndex) {
                case 0:
                    return st.getSurName();
                case 1:
                    return st.getFirstName();
                case 2:
                    return st.getPatronymic();
                case 3:
                    return DateFormat.getDateInstance(DateFormat.SHORT).format(
                            st.getDateOfBirth());
            }
        }
        return null;
    }

    // Добавим метод, который возвращает студента по номеру строки
    // Это нам пригодится чуть позже
    public Student getStudent(int rowIndex) {
        if (students != null) {
            if (rowIndex < students.size() && rowIndex >= 0) {
                return (Student) students.get(rowIndex);
            }
        }
        return null;
    }
}
