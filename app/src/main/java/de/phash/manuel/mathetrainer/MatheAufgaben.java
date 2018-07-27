package de.phash.manuel.mathetrainer;


import android.util.Log;

import org.apache.commons.lang3.RandomUtils;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class MatheAufgaben {
    public static MatheAufgabe getAddition(int loesungen, int bisMax) {

        MatheAufgabe aufgabe = new MatheAufgabe();
        Set<Integer> loesungsSet = new HashSet<>();
        Integer summand1;
        Integer summand2;
        do {

            summand1 = RandomUtils.nextInt(0, bisMax);
            summand2 = RandomUtils.nextInt(0, bisMax);

        } while (summand1 + summand2 >= bisMax);
        aufgabe.setRichtigeLoesung(summand1 + summand2);
        loesungsSet.add(summand1 + summand2);
        do {
            loesungsSet.add(RandomUtils.nextInt(0, bisMax));

        } while (loesungsSet.size() < loesungen);
        aufgabe.setAufgabe(String.format(Locale.GERMAN, "%d + %d", summand1, summand2));
        aufgabe.setLoesungen(loesungsSet);

        return aufgabe;
    }

    public static MatheAufgabe getSubstraktion(int loesungen, int bisMax) {

        MatheAufgabe aufgabe = new MatheAufgabe();
        Set<Integer> loesungsSet = new HashSet<>();
        Integer minuend;
        Integer subtrahend;
        do {

            minuend = RandomUtils.nextInt(0, bisMax);
            subtrahend = RandomUtils.nextInt(0, bisMax);
            Log.i("sub", "minuend: " + minuend + " subtrahend: " + subtrahend);
        } while (minuend + subtrahend >= bisMax || minuend < subtrahend);
        aufgabe.setRichtigeLoesung(minuend - subtrahend);
        if (loesungen > 1) {

            loesungsSet.add(minuend - subtrahend);
            do {
                loesungsSet.add(RandomUtils.nextInt(0, bisMax));

            } while (loesungsSet.size() < loesungen);
            aufgabe.setAufgabe(String.format(Locale.GERMAN, "%d - %d", minuend, subtrahend));
            aufgabe.setLoesungen(loesungsSet);
        }

        return aufgabe;
    }
}
