package de.phash.manuel.mathetrainer

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

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
            increaseAllStates()
            Toast.makeText(this, "Richtig!", Toast.LENGTH_SHORT).show()
            updateViews()
        } else {
            val sharedPref = this.getSharedPreferences("de.phash.manuel.mathetrainer", Context.MODE_PRIVATE)

            var versuchPers = sharedPref.getInt("versuche", 0)
            sharedPref.edit().putInt("versuche", versuchPers++).apply()
            Status.instance.versuche++
            b.setBackgroundColor(Color.RED)
            updateVersuche()
            Toast.makeText(this, "leider falsch", Toast.LENGTH_SHORT).show()

        }
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
    private fun updateVersuche() {
        val versucheView = findViewById(R.id.versucheView) as TextView
        versucheView.text = Status.instance.versuche.toString()
    }

    private fun updateViews() {
        updateVersuche()

        aktAufgabe = erzeugeMatheAufgabe(Status.instance.bisMax)

        val aufgabeTextView = findViewById(R.id.aktuelleAufgabe) as TextView
        aufgabeTextView.text = aktAufgabe!!.aufgabe

        val geloesteAufgabenTextView = findViewById(R.id.geloesteAufgaben) as TextView
        geloesteAufgabenTextView.text = String.format(Locale.GERMAN, "%d", Status.instance.richtig)


        var i = 0
        for (ele in aktAufgabe!!.loesungen) {
            buttons[i].setBackgroundColor(Color.LTGRAY)
            buttons[i].text = String.format(Locale.GERMAN, Integer.toString(ele!!))
            i++
        }
    }

    private fun erzeugeMatheAufgabe(bisMax: Int): MatheAufgabe? {
        if (rechenart == Konstanten.ADD)
            return MatheAufgaben.getAddition(4, bisMax)
        return if (rechenart == Konstanten.SUB) MatheAufgaben.getSubstraktion(4, bisMax) else null
    }

    fun hauptMenue(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

