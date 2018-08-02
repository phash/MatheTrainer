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
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var constraintLayout = findViewById(R.id.constraintLayout)

        var bisMax = findViewById(R.id.bisMaxEingabe) as TextView
        val sharedPref = this.getSharedPreferences("de.phash.manuel.mathetrainer", Context.MODE_PRIVATE)
        bisMax.text = sharedPref.getInt("bisMax", 20).toString()
        bisMax.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.i("textchange", "before Text Changed")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("textchange", "on Text Changed")
            }

            override fun afterTextChanged(input: Editable?) {
                Log.i("textchange", "after Text Changed: " + input.toString())
                if (input.toString().isNotBlank()) {

                    val inp = Integer.parseInt(input.toString())

                    Log.i("textchange", "write to prefs")
                    sharedPref.edit().putInt("bisMax", inp).apply()
                    Status.instance.bisMax = inp
                    // } else bisMax.setText(sharedPref.getInt("bisMax", 20))
                }
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)

        when (item?.itemId) {
            R.id.reset -> {
                resetVersuche()
                return true
            }
            R.id.allTime -> {
                zeigeAllTimeStats()
            }
        }
        return false

    }

    private fun zeigeAllTimeStats() {
        val sharedPref = this.getSharedPreferences("de.phash.manuel.mathetrainer", Context.MODE_PRIVATE)
        AlertDialog.Builder(this).setIcon(R.drawable.abc_ic_star_black_36dp).setTitle("Alle gerechneten Aufgaben!").setMessage(String.format("Bisher hast du %d Aufgaben gerechnet, davon hast du %d richtig gehabt und %d Versuche gebraucht!",
                sharedPref.getInt(Status.AUFGABENALLTIME, 0),
                sharedPref.getInt(Status.RICHTIGEALLTIME, 0),
                sharedPref.getInt(Status.VERSUCHEALLTIME, 0))).show()
    }

    private fun resetVersuche() {
        val sharedPref = this.getSharedPreferences("de.phash.manuel.mathetrainer", Context.MODE_PRIVATE)
        sharedPref.edit().putInt(Status.VERSUCHE, 0).apply()
        Status.instance.versuche = 0

        sharedPref.edit().putInt(Status.AUFGABEN, 0).apply()
        Status.instance.aufgabe = 0
        sharedPref.edit().putInt(Status.RICHTIGE, 0).apply()
        Status.instance.richtig = 0
    }

    fun buttonClick(view: View) {
        val tag = view.tag
        if (tag.equals(Konstanten.ADD) || tag.equals(Konstanten.SUB)) {
            val intent = Intent(this, EinfachesRechnen::class.java).apply {
                putExtra(Konstanten.RECHENART, tag.toString())
            }
            startActivity(intent)
        } else if (tag.equals(Konstanten.ADDERG) || tag.equals(Konstanten.SUBERG)) {
            val intent = Intent(this, EinfachesRechnenMitEingabe::class.java).apply {
                putExtra(Konstanten.RECHENART, tag.toString())
            }
            startActivity(intent)
        }


    }
}
