package com.example.lab15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab15.classes.Etudiant;
import com.example.lab15.service.EtudiantService;

public class MainActivity extends AppCompatActivity {

    private EditText essNom;
    private EditText essPrenom;
    private Button essBtnAdd;

    private EditText essCode;
    private Button essBtnSearch;
    private Button essBtnDelete;
    private TextView essResult;

    // vider les champs
    void clear() {
        essNom.setText("");
        essPrenom.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EtudiantService es = new EtudiantService(this);

        // liaison XML
        essNom = findViewById(R.id.essNom);
        essPrenom = findViewById(R.id.essPrenom);
        essBtnAdd = findViewById(R.id.essBtnAdd);

        essCode = findViewById(R.id.essCode);
        essBtnSearch = findViewById(R.id.essBtnSearch);
        essBtnDelete = findViewById(R.id.essBtnDelete);
        essResult = findViewById(R.id.essResult);

        // AJOUT
        essBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = essNom.getText().toString().trim();
                String prenom = essPrenom.getText().toString().trim();

                if (nom.isEmpty() || prenom.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                es.create(new Etudiant(nom, prenom));
                clear();

                for (Etudiant e : es.findAll()) {
                    Log.d(e.getEssCode() + "", e.getEssNomEtud() + " " + e.getEssPrenomEtud());
                }

                Toast.makeText(MainActivity.this, "Étudiant ajouté", Toast.LENGTH_SHORT).show();
            }
        });

        // CHERCHER
        essBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt = essCode.getText().toString().trim();

                if (txt.isEmpty()) {
                    essResult.setText("");
                    Toast.makeText(MainActivity.this, "Saisir un code", Toast.LENGTH_SHORT).show();
                    return;
                }

                Etudiant e = es.findById(Integer.parseInt(txt));

                if (e == null) {
                    essResult.setText("");
                    Toast.makeText(MainActivity.this, "Étudiant introuvable", Toast.LENGTH_SHORT).show();
                    return;
                }

                essResult.setText(e.getEssNomEtud() + " " + e.getEssPrenomEtud());
            }
        });

        // SUPPRIMER
        essBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt = essCode.getText().toString().trim();

                if (txt.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Saisir un code", Toast.LENGTH_SHORT).show();
                    return;
                }

                Etudiant e = es.findById(Integer.parseInt(txt));

                if (e == null) {
                    Toast.makeText(MainActivity.this, "Aucun étudiant à supprimer", Toast.LENGTH_SHORT).show();
                    return;
                }

                es.delete(e);
                essResult.setText("");

                Toast.makeText(MainActivity.this, "Étudiant supprimé", Toast.LENGTH_SHORT).show();
            }
        });
    }
}