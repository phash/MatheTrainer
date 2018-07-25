package de.phash.manuel.mathetrainer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class EinfachesRechnen extends AppCompatActivity {

    ArrayList<Button> buttons = new ArrayList<>(4);
    Integer aufgabe = 0;
    Integer richtig = 0;
    Integer versuche = 0;
    private MatheAufgabe aktAufgabe;
    private String rechenart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einfaches_rechnen);
        rechenart = Objects.requireNonNull(getIntent().getExtras()).get(Konstanten.RECHENART).toString();
        buttons.add((Button) findViewById(R.id.loesung1));
        buttons.add((Button) findViewById(R.id.loesung2));
        buttons.add((Button) findViewById(R.id.loesung3));
        buttons.add((Button) findViewById(R.id.loesung4));

        updateViews();
    }

    public void loesungButtonClick(View view) {
        Button b = (Button) view;
        Integer gedrueckt = Integer.valueOf(b.getText().toString());
        Log.i("button", gedrueckt + "");

        if (aktAufgabe.getRichtigeLoesung().equals(gedrueckt)) {
            richtig++;
            aufgabe++;
            versuche++;
            Toast.makeText(this, "Richtig!", Toast.LENGTH_SHORT).show();
            updateViews();
        } else {
            versuche++;
            b.setBackgroundColor(Color.RED);
            updateVersuche();
            Toast.makeText(this, "leider falsch", Toast.LENGTH_SHORT).show();

        }
    }

    private void updateVersuche() {
        TextView versucheView = (TextView) findViewById(R.id.versucheView);
        versucheView.setText(versuche.toString());
    }

    private void updateViews() {
        updateVersuche();
        aktAufgabe = erzeugeMatheAufgabe();
        TextView aufgabeTextView = (TextView) findViewById(R.id.aktuelleAufgabe);
        aufgabeTextView.setText(aktAufgabe.getAufgabe());

        TextView geloesteAufgabenTextView = (TextView) findViewById(R.id.geloesteAufgaben);
        geloesteAufgabenTextView.setText(String.format(Locale.GERMAN, "%d", richtig));


        int i = 0;
        for (Integer ele : aktAufgabe.getLoesungen()) {
            buttons.get(i).setBackgroundColor(Color.LTGRAY);
            buttons.get(i).setText(Integer.toString(ele));
            i++;
        }
    }

    private MatheAufgabe erzeugeMatheAufgabe() {
        if (rechenart.equals(Konstanten.ADD))
            return MatheAufgaben.getAddition(4, 20);
        if (rechenart.equals(Konstanten.SUB))
            return MatheAufgaben.getSubstraktion(4, 20);
        return null;
    }

    public void hauptMenue(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

