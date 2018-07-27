package de.phash.manuel.mathetrainer

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import java.util.ArrayList
import java.util.Locale
import java.util.Objects

class EinfachesRechnen : AppCompatActivity() {

    internal var buttons = ArrayList<Button>(4)

    private var aktAufgabe: MatheAufgabe? = null
    private var rechenart: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_einfaches_rechnen)
        rechenart = Objects.requireNonNull(intent.extras).get(Konstanten.RECHENART)!!.toString()
        buttons.add(findViewById(R.id.loesung1) as Button)
        buttons.add(findViewById(R.id.loesung2) as Button)
        buttons.add(findViewById(R.id.loesung3) as Button)
        buttons.add(findViewById(R.id.loesung4) as Button)

        updateViews()
    }

    fun loesungButtonClick(view: View) {
        val b = view as Button
        val gedrueckt = Integer.valueOf(b.text.toString())
        Log.i("button", gedrueckt.toString() + "")

        if (aktAufgabe!!.richtigeLoesung == gedrueckt) {
            richtig++
            aufgabe++
            versuche++
            Toast.makeText(this, "Richtig!", Toast.LENGTH_SHORT).show()
            updateViews()
        } else {
            versuche++
            b.setBackgroundColor(Color.RED)
            updateVersuche()
            Toast.makeText(this, "leider falsch", Toast.LENGTH_SHORT).show()

        }
    }

    private fun updateVersuche() {
        val versucheView = findViewById(R.id.versucheView) as TextView
        versucheView.setText(versuche.toString())
    }

    private fun updateViews() {
        updateVersuche()
        aktAufgabe = erzeugeMatheAufgabe()
        val aufgabeTextView = findViewById(R.id.aktuelleAufgabe) as TextView
        aufgabeTextView.text = aktAufgabe!!.aufgabe

        val geloesteAufgabenTextView = findViewById(R.id.geloesteAufgaben) as TextView
        geloesteAufgabenTextView.text = String.format(Locale.GERMAN, "%d", richtig)


        var i = 0
        for (ele in aktAufgabe!!.loesungen) {
            buttons[i].setBackgroundColor(Color.LTGRAY)
            buttons[i].text = Integer.toString(ele!!)
            i++
        }
    }

    private fun erzeugeMatheAufgabe(): MatheAufgabe? {
        if (rechenart == Konstanten.ADD)
            return MatheAufgaben.getAddition(4, 20)
        return if (rechenart == Konstanten.SUB) MatheAufgaben.getSubstraktion(4, 20) else null
    }

    fun hauptMenue(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

