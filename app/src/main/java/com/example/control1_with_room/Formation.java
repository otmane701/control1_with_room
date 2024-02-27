package com.example.control1_with_room;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class Formation {
    @ColumnInfo()
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo()
    private String nom;
    @ColumnInfo()
    private int duree;
    @ColumnInfo()
    private String type;

    public Formation( String nom, int duree, String type) {
        this.nom = nom;
        this.duree = duree;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getDuree() {
        return duree;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getNom()+" "+this.getDuree()+" "+this.getType();
    }
}
