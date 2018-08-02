/**
 * Copyright (c) 2018 Manuel "Phash" Roedig
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package de.phash.manuel.mathetrainer

class Status private constructor() {


    fun richtigAdd() {
        richtig++
    }

    fun versucheAdd() {
        versuche++
    }

    fun aufgabeAdd() {
        aufgabe++
    }

    init {
        println("This ($this) is a singleton")
    }

    private object Holder {
        val INSTANCE = Status()
    }

    companion object {
        val instance: Status by lazy { Holder.INSTANCE }
        const val RICHTIGE = "richtige"
        const val VERSUCHE = "versuche"
        const val AUFGABEN = "aufgaben"
        const val RICHTIGEALLTIME = "richtigeAllTime"
        const val VERSUCHEALLTIME = "versucheAllTime"
        const val AUFGABENALLTIME = "aufgabenAllTime"
    }

    internal var aufgabe: Int = 0
    internal var richtig: Int = 0
    internal var versuche: Int = 0
    internal var bisMax: Int = 20


}

