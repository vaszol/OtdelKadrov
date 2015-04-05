package ru.vaszol.exam.frame;

/**
 * Created by vas on 04.04.15.
 */
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ru.vaszol.exam.logic.Group;

public class GroupDialog extends JDialog implements ActionListener {

    private static final int D_HEIGHT = 150;   // высота
    private final static int D_WIDTH = 200;   // ширина
    private JSpinner spYear;
    private JComboBox groupList;
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private boolean result = false;

    public GroupDialog(int year, List groups) {
        // Установить заголовок
        setTitle("Перенос группы");

        // Создаем сложный layout для нашего окна
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        // Создаем переменную для установки правил размещения
        GridBagConstraints c = new GridBagConstraints();
        // Сразу задаем отступ от границ для каждого элемента
        c.insets = new Insets(5, 5, 5, 5);

        // Первый элемент - заголовок для поля выбора групп
        JLabel l = new JLabel("Новая группа:");
        // После него можно будет еще помещать компоненты
        c.gridwidth = GridBagConstraints.RELATIVE;
        // Не заполняем все пространство, отведенное компоненту
        c.fill = GridBagConstraints.NONE;
        // "Привязываем" компонент к правому краю
        c.anchor = GridBagConstraints.EAST;
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(l, c);
        // Добавляем компонент
        getContentPane().add(l);

        // Второй элемент - список групп
        groupList = new JComboBox(new Vector(groups));
        // Элемент занимает всю оставшуюся ширину
        c.gridwidth = GridBagConstraints.REMAINDER;
        // Растягиваем компонент по всему пространству для него
        c.fill = GridBagConstraints.BOTH;
        // "Привязываем" его к левой части
        c.anchor = GridBagConstraints.WEST;
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(groupList, c);
        // Добавляем компонент
        getContentPane().add(groupList);

        // Третий элемент - заголовок для поля выбора года
        l = new JLabel("Новый год:");
        // После него можно будет еще помещать компоненты
        c.gridwidth = GridBagConstraints.RELATIVE;
        // Не заполняем все пространство, отведенное компоненту
        c.fill = GridBagConstraints.NONE;
        // "Привязываем" компонент к правому краю
        c.anchor = GridBagConstraints.EAST;
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(l, c);
        // Добавляем компонент
        getContentPane().add(l);

        // Сразу увеличиваем группу на один год - для перевода
        spYear = new JSpinner(new SpinnerNumberModel(year + 1, 1900, 2100, 1));
        // Элемент занимает всю оставшуюся ширину
        c.gridwidth = GridBagConstraints.REMAINDER;
        // Растягиваем компонент по всему пространству для него
        c.fill = GridBagConstraints.BOTH;
        // "Привязываем" его к левой части
        c.anchor = GridBagConstraints.WEST;
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(spYear, c);
        // Добавляем компонент
        getContentPane().add(spYear);

        c.gridwidth = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.BOTH;
        btnOk.setName("OK");
        // Добавляем листенер для кнопки
        btnOk.addActionListener(this);
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(btnOk, c);
        // Добавляем компонент
        getContentPane().add(btnOk);

        btnCancel.setName("Cancel");
        // Добавляем листенер для кнопки
        btnCancel.addActionListener(this);
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(btnCancel, c);
        // Добавляем компонент
        getContentPane().add(btnCancel);

        // Устанавливаем поведение формы при закрытии - не закрывать совсем.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Получаем размеры экрана
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        // А теперь просто помещаем его по центру, вычисляя координаты на основе полученной информации
        setBounds(((int) d.getWidth() - GroupDialog.D_WIDTH) / 2, ((int) d.getHeight() - GroupDialog.D_HEIGHT) / 2,
                GroupDialog.D_WIDTH, GroupDialog.D_HEIGHT);
    }

    // Возврат года, который установлен на форме
    public int getYear() {
        return ((SpinnerNumberModel) spYear.getModel()).getNumber().intValue();
    }

    // Возврат группы, которая установлена на форме
    public Group getGroup() {
        if (groupList.getModel().getSize() > 0) {
            return (Group) groupList.getSelectedItem();
        }
        return null;
    }

    // Получить результат, true - кнопка ОК, false - кнопка Cancel
    public boolean getResult() {
        return result;
    }

    // Обработка нжатия кнопок
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src.getName().equals("OK")) {
            result = true;
        }
        if (src.getName().equals("Cancel")) {
            result = false;
        }
        setVisible(false);
    }
}