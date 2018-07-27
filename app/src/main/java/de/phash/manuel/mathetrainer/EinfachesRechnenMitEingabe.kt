package de.phash.manuel.mathetrainer

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
    internal var aufgabe: Int = 0
    internal var richtig: Int = 0
    internal var versuche: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_einfaches_rechnen_mit_eingabe)
        rechenart = (if (intent.extras.get(Konstanten.RECHENART) != null) intent.extras.get(Konstanten.RECHENART)
        else {
            throw NullPointerException("Expression 'intent.extras.get(Konstanten.RECHENART)' must not be null")
        }).toString()
        updateViews();
    }

    private fun updateViews() {
        updateVersuche()
        aktAufgabe = erzeugeMatheAufgabe()
        val aufgabeTextView = findViewById(R.id.aktuelleAufgabe) as TextView
        aufgabeTextView.text = aktAufgabe?.aufgabe

        val geloesteAufgabenTextView = findViewById(R.id.geloesteAufgaben) as TextView
        geloesteAufgabenTextView.text = String.format(Locale.GERMAN, "%d", richtig)
    }

    public fun loesungButtonClick(view: View) {
        var eingabeFeld = findViewById(R.id.eingabeZahl) as TextView
        var eingabe = Integer.parseInt(eingabeFeld.text.toString());
        if (eingabe == aktAufgabe?.richtigeLoesung) {
            richtig++
            versuche++
            aufgabe++
            Toast.makeText(this, "Richtig!", Toast.LENGTH_SHORT).show()
            updateViews()
        } else {
            versuche++
            updateVersuche()
            var richtung = if (eingabe < aktAufgabe?.richtigeLoesung) "niedrig" else "hoch"
            Toast.makeText(this, "leider falsch, dein Ergebnis war zu " + richtung, Toast.LENGTH_LONG).show()
        }
    }

    public fun hauptMenue(view: View) {
        startActivity(Intent(this, EinfachesRechnen::class.java))
    }

    private fun erzeugeMatheAufgabe(): MatheAufgabe {
        if (rechenart == Konstanten.ADDERG)
            return MatheAufgaben.getAddition(1, 20)
        if (rechenart == Konstanten.SUBERG)
            return MatheAufgaben.getSubstraktion(1, 20)
        else return MatheAufgaben.getSubstraktion(1, 20)
    }

    private fun updateVersuche() {
        val versucheView = findViewById(R.id.versucheView) as TextView
        versucheView.setText(versuche.toString())
    }
}
