package com.example.control1_with_room;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@SuppressLint("StaticFieldLeak")

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ednom,edduree;
    Button btnajouter,btnafficher;
    Spinner sp;
    ListView lv;
    DB_Formation dbFormation;
    DAO_Formation daoFormation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ednom=findViewById(R.id.ednom);
        edduree=findViewById(R.id.edduree);
        btnajouter=findViewById(R.id.btnajouter);
        btnafficher=findViewById(R.id.btnafficher);
        sp=findViewById(R.id.spinner);
        lv=findViewById(R.id.lv);

        dbFormation=DB_Formation.getInstance(this);
        daoFormation=dbFormation.getDao();

//        Formation f1=new Formation("java",1,"distantielle");
//        Formation f2=new Formation("kotlin",2,"presentielle");
//        Formation f3=new Formation("Room",3,"hypride");
//        ajouterFormation(f1,f2,f3);

        new AsyncTask<Void, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(Void... voids) {
                return  daoFormation.getTypes();
            }
            @Override
            protected void onPostExecute(List<String> result) {
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>((Context) MainActivity.this, android.R.layout.simple_spinner_item, result);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp.setAdapter(spinnerAdapter);
            }
        }.execute();
btnajouter.setOnClickListener(this);
btnafficher.setOnClickListener(this);

    }
    void ajouterFormation(Formation... fr) {
        new AsyncTask<Formation, Void, Void>() {
            @Override
            protected Void doInBackground(Formation... f) {
                daoFormation.ajouterFormation(f);
                return null;
            }
        }.execute(fr);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnajouter){
           String nom=ednom.getText().toString();
           String type=sp.getSelectedItem().toString();
           int duree=Integer.parseInt(edduree.getText().toString());
           Formation f=new Formation(nom,duree,type);
           ajouterFormation(f);
        }
        else {
           new AsyncTask<Void,Void,List<Formation>>(){
               @Override
               protected List<Formation> doInBackground(Void... voids) {
                   return daoFormation.getFormations();
               }
               @Override
               protected void onPostExecute(List<Formation> result){
//
                   ArrayAdapter<Formation> arrayAdapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,result);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                   lv.setAdapter(arrayAdapter);
               }
           }.execute();
        }
    }
}