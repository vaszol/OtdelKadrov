package ru.vaszol.examen.test;

/**
 * Created by vas on 03.04.2015.
 * создаем простое окно
 */
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {

    public MainFrame() {
        setBounds(100, 100, 300, 200);
    }

    public static void main(String[] args) {
        // ������ ����� ����� ����������� � ����������� �����
        // event-dispatching thread - EDT
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mf = new MainFrame();
                mf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                mf.setVisible(true);
            }
        });
    }
}