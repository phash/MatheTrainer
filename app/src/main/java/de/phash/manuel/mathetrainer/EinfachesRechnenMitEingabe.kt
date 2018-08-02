/**
 * Copyright (c) 2018 Manuel "Phash" Roedig
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package de.phash.manuel.mathetrainer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import java.util.*

class EinfachesRechnenMitEingabe : AppCompatActivity() {
    private lateinit var aktAufgabe: MatheAufgabe
    private lateinit var rechenart: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_einfaches_rechnen_mit_eingabe)
        rechenart = (if (intent.extras.get(Konstanten.RECHENART) != null) intent.extras.get(Konstanten.RECHENART)
        else {
            throw NullPointerException("Expression 'intent.extras.get(Konstanten.RECHENART)' must not be null")
        }).toString()
        updateViews()
    }

    private fun updateViews() {
        updateVersucheView()


        aktAufgabe = erzeugeMatheAufgabe(Status.instance.bisMax)
        val aufgabeTextView = findViewById(R.id.aktuelleAufgabe) as TextView
        aufgabeTextView.text = aktAufgabe.aufgabe

        val geloesteAufgabenTextView = findViewById(R.id.geloesteAufgaben) as TextView
        geloesteAufgabenTextView.text = String.format(Locale.GERMAN, "%d", Status.instance.richtig)
    }

    fun loesungButtonClick(view: View) {
        var eingabeFeld = findViewById(R.id.eingabeZahl) as TextView
        var eingabe = Integer.parseInt(eingabeFeld.text.toString())
        if (eingabe == aktAufgabe.richtigeLoesung) {
            increaseAllStates()
            createHint("Richtig")
            updateViews()
        } else {
            Status.instance.versucheAdd()
            val sharedPref = this.getSharedPreferences("de.phash.manuel.mathetrainer", Context.MODE_PRIVATE)

            var versuchPers = sharedPref.getInt(Status.VERSUCHE, 0)
            sharedPref.edit().putInt(Status.VERSUCHE, versuchPers++).apply()

            sharedPref.edit().putInt(Status.VERSUCHEALLTIME, (sharedPref.getInt(Status.VERSUCHEALLTIME, 0) + 1)).apply()
            updateVersucheView()
            var richtung = if (eingabe < aktAufgabe.richtigeLoesung) "niedrig" else "hoch"

            createHint("leider falsch, dein Ergebnis war zu " + richtung)

        }
        eingabeFeld.text = ""
    }

    private fun createHint(hint: String) {
        val hinweis = findViewById(R.id.hinweis) as TextView
        hinweis.text = hint
    }


    private fun increaseAllStates() {
        StatsUpdateService.instance.increaseAllStates()
    }

    fun hauptMenue(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun erzeugeMatheAufgabe(bisMax: Int): MatheAufgabe {
        if (rechenart == Konstanten.ADDERG)
            return MatheAufgaben.getAddition(1, bisMax)
        if (rechenart == Konstanten.SUBERG)
            return MatheAufgaben.getSubstraktion(1, bisMax)
        else return MatheAufgaben.getSubstraktion(1, bisMax)
    }

    private fun updateVersucheView() {
        val versucheView = findViewById(R.id.versucheView) as TextView
        versucheView.text = Status.instance.versuche.toString()
    }
}
