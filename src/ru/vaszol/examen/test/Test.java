package ru.vaszol.examen.test;

/**
 * Created by vas on 03.04.2015.
 * проводим сравнение по алфавиту
 */
import java.text.Collator;
import java.util.Locale;

public class Test
{
    public static void main(String[] arg) {

        Collator c = Collator.getInstance(new Locale("ru"));
        // Если закомментировать следующую строку, то "ПАВЕЛ" будет не равен "Павел"
        // (c.compare!=0) что по идее не совсем корректно
        c.setStrength(Collator.PRIMARY);
        System.out.println(c.compare("ПАВЕЛ", "ПАВЕЛ"));
        System.out.println(c.compare("ПАВЕЛ", "Павел"));
        System.out.println(c.compare("ПАВЕЛ", "артем"));
        System.out.println("ПАВЕЛ".compareTo("артем"));
    }
}