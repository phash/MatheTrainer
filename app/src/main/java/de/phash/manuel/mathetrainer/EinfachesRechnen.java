package de.phash.manuel.mathetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.RandomUtils;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class EinfachesRechnen extends AppCompatActivity {

    ArrayList<Button> buttons = new ArrayList<>(4);
    Integer erwartet;
    Integer aufgabe =0;
    Integer richtig =0;
    Integer versuche = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einfaches_rechnen);
        buttons.add((Button) findViewById(R.id.loesung1));
        buttons.add((Button) findViewById(R.id.loesung2));
        buttons.add((Button) findViewById(R.id.loesung3));
        buttons.add((Button) findViewById(R.id.loesung4));

       updateViews();
    }

    public void loesungButtonClick(View view) {
        Button b = (Button) view;
        Integer gedrueckt=Integer.valueOf(b.getText().toString());
        Log.i("button",gedrueckt+"");

        if (gedrueckt==erwartet){
            richtig++;
            aufgabe++;
            versuche++;
            Toast.makeText(this, "Richtig!", Toast.LENGTH_SHORT).show();
            updateViews();
        } else {
            versuche++;
            updateVersuche();
            Toast.makeText(this, "leider falsch", Toast.LENGTH_SHORT).show();

        }
    }

    private void updateVersuche() {
        TextView versucheView = findViewById(R.id.versucheView);
        versucheView.setText(versuche.toString());
    }

    private void updateViews() {
        updateVersuche();
        MatheAufgabe aktAufgabe= MatheAufgaben.getAddition(4, 20);
        TextView aufgabeTextView = findViewById(R.id.aktuelleAufgabe);
        aufgabeTextView.setText(aktAufgabe.getAufgabe());

        TextView geloesteAufgabenTextView = findViewById(R.id.geloesteAufgaben);
        geloesteAufgabenTextView.setText(String.format("%d", richtig));

        erwartet=aktAufgabe.getRichtigeLoesung();
        int i =0;
        for(Integer ele : aktAufgabe.getLoesungen()){
            buttons.get(i).setText(Integer.toString(ele));
                    i++;
        }
    }

    public void hauptMenue(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

