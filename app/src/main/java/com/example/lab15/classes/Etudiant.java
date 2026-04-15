package com.example.lab15.classes;

public class Etudiant {

    private int essCode;
    private String essNomEtud;
    private String essPrenomEtud;

    public Etudiant(String essNomEtud, String essPrenomEtud) {
        this.essNomEtud = essNomEtud;
        this.essPrenomEtud = essPrenomEtud;
    }

    public Etudiant() {
    }

    public int getEssCode() {
        return essCode;
    }

    public void setEssCode(int essCode) {
        this.essCode = essCode;
    }

    public String getEssNomEtud() {
        return essNomEtud;
    }

    public void setEssNomEtud(String essNomEtud) {
        this.essNomEtud = essNomEtud;
    }

    public String getEssPrenomEtud() {
        return essPrenomEtud;
    }

    public void setEssPrenomEtud(String essPrenomEtud) {
        this.essPrenomEtud = essPrenomEtud;
    }
}