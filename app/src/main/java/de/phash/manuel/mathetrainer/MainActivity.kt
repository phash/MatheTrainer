package de.phash.manuel.mathetrainer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                val inp = Integer.parseInt(input.toString())

                    Log.i("textchange", "write to prefs")
                    sharedPref.edit().putInt("bisMax", inp).apply()
                    Status.instance.bisMax = inp
                // } else bisMax.setText(sharedPref.getInt("bisMax", 20))
            }

        })

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
