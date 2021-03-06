/**
 * Copyright (c) 2018 Manuel "Phash" Roedig
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package de.phash.manuel.mathetrainer;

import java.util.Set;

class MatheAufgabe {

    private Set<Integer> loesungen;
    private String aufgabe;
    private Integer richtigeLoesung;

    public void setLoesungen(Set<Integer> loesungen) {
        this.loesungen = loesungen;
    }

    public void setAufgabe(String format) {
        this.aufgabe=format;

    }

    public Set<Integer> getLoesungen() {
        return loesungen;
    }

    public String getAufgabe() {
        return aufgabe;
    }

    public Integer getRichtigeLoesung() {
        return richtigeLoesung;
    }

    public void setRichtigeLoesung(Integer richtigeLoesung) {
        this.richtigeLoesung = richtigeLoesung;
    }

}
