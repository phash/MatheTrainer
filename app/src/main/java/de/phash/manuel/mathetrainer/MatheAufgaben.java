package de.phash.manuel.mathetrainer;

import android.support.v4.math.MathUtils;

import org.apache.commons.lang3.RandomUtils;

import java.util.HashSet;
import java.util.Set;

class MatheAufgaben {
    public static MatheAufgabe getAddition(int loesungen, int bisMax) {

        MatheAufgabe aufgabe = new MatheAufgabe();
        Set<Integer> loesungsSet = new HashSet<>();
        Integer summand1 =0;
        Integer summand2=0;
        do {

         summand1 = RandomUtils.nextInt(0,bisMax);
         summand2 = RandomUtils.nextInt(0,bisMax);

        } while (summand1+summand2>bisMax);
            aufgabe.setRichtigeLoesung(summand1+summand2);
            loesungsSet.add(summand1+summand2);
        do {
            loesungsSet.add(RandomUtils.nextInt(0,bisMax));

        }while(loesungsSet.size()<4);
        aufgabe.setAufgabe(String.format("%d + %d", summand1, summand2));
        aufgabe.setLoesungen(loesungsSet);

        return aufgabe;
    }
}
