package de.phash.manuel.mathetrainer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
