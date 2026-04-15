package com.example.lab15.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.lab15.classes.Etudiant;
import com.example.lab15.util.MySQLiteHelper;

public class EtudiantService {

    private static final String TABLE_NAME = "etudiant";

    private static final String KEY_CODE = "essCode";
    private static final String KEY_NOM = "essNomEtud";
    private static final String KEY_PRENOM = "essPrenomEtud";

    private static final String[] COLUMNS = {KEY_CODE, KEY_NOM, KEY_PRENOM};

    private final MySQLiteHelper helper;

    public EtudiantService(Context context) {
        this.helper = new MySQLiteHelper(context);
    }

    public void create(Etudiant e) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NOM, e.getEssNomEtud());
        values.put(KEY_PRENOM, e.getEssPrenomEtud());

        db.insert(TABLE_NAME, null, values);
        Log.d("insert", e.getEssNomEtud());

        db.close();
    }

    public void update(Etudiant e) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_CODE, e.getEssCode());
        values.put(KEY_NOM, e.getEssNomEtud());
        values.put(KEY_PRENOM, e.getEssPrenomEtud());

        db.update(TABLE_NAME, values, "essCode = ?", new String[]{e.getEssCode() + ""});
        db.close();
    }

    public Etudiant findById(int code) {
        Etudiant e = null;
        SQLiteDatabase db = this.helper.getReadableDatabase();

        Cursor c = db.query(
                TABLE_NAME,
                COLUMNS,
                "essCode = ?",
                new String[]{String.valueOf(code)},
                null,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            e = new Etudiant();
            e.setEssCode(c.getInt(0));
            e.setEssNomEtud(c.getString(1));
            e.setEssPrenomEtud(c.getString(2));
        }

        c.close();
        db.close();
        return e;
    }

    public void delete(Etudiant e) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        db.delete(TABLE_NAME, "essCode = ?", new String[]{String.valueOf(e.getEssCode())});
        db.close();
    }

    public List<Etudiant> findAll() {
        List<Etudiant> list = new ArrayList<>();
        String req = "select * from " + TABLE_NAME;

        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor c = db.rawQuery(req, null);

        if (c.moveToFirst()) {
            do {
                Etudiant e = new Etudiant();
                e.setEssCode(c.getInt(0));
                e.setEssNomEtud(c.getString(1));
                e.setEssPrenomEtud(c.getString(2));
                list.add(e);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return list;
    }
}