package de.phash.manuel.mathetrainer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
        updateVersuche()


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
            Toast.makeText(this, "Richtig!", Toast.LENGTH_SHORT).show()
            updateViews()
        } else {
            Status.instance.versuche++
            val sharedPref = this.getSharedPreferences("de.phash.manuel.mathetrainer", Context.MODE_PRIVATE)

            var versuchPers = sharedPref.getInt("versuche", 0)
            sharedPref.edit().putInt("versuche", versuchPers++).apply()
            updateVersuche()
            var richtung = if (eingabe < aktAufgabe.richtigeLoesung) "niedrig" else "hoch"
            Toast.makeText(this, "leider falsch, dein Ergebnis war zu " + richtung, Toast.LENGTH_LONG).show()
        }
        eingabeFeld.text = ""
    }

    private fun increaseAllStates() {
        Status.instance.richtig++
        Status.instance.versuche++
        Status.instance.aufgabe++
        val sharedPref = this.getSharedPreferences("de.phash.manuel.mathetrainer", Context.MODE_PRIVATE)
        var richtigPers = sharedPref.getInt("richtieg", 0)
        sharedPref.edit().putInt("richtige", richtigPers++).apply()
        var versuchPers = sharedPref.getInt("versuche", 0)
        sharedPref.edit().putInt("versuche", versuchPers++).apply()

        var aufgabePers = sharedPref.getInt("aufgaben", 0)
        sharedPref.edit().putInt("aufgaben", aufgabePers++).apply()

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

    private fun updateVersuche() {
        val versucheView = findViewById(R.id.versucheView) as TextView
        versucheView.text = Status.instance.versuche.toString()
    }
}
