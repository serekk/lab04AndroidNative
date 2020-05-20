package com.zut.lab04androidnative;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> target;
    private SimpleCursorAdapter adapter;
    private MySQLite db = new MySQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] values = new String[] {"Pies", "Kot", "Koń", "Gołąb", "Kruk", "Dzik", "Karp", "Osioł", "Chomik",
        "Mysz", "Jeż", "Myszojeleń"};

        this.target = new ArrayList<String>();
        this.target.addAll(Arrays.asList(values));
        this.adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, db.list(),
                new String[] {"_id", "gatunek"},
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        ListView listView = findViewById(R.id.animalListView);
        listView.setAdapter(this.adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = view.findViewById(android.R.id.text1);
                Animal animal = db.getAnimal(Integer.parseInt(name.getText().toString()));

                Intent intent = new Intent(getApplicationContext(), DodajWpis.class);
                intent.putExtra("element", animal);
                startActivityForResult(intent,2);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = view.findViewById(android.R.id.text1);
                Animal animal = db.getAnimal(Integer.parseInt(name.getText().toString()));
                db.removeAnimal(String.valueOf(animal.getId()));
                adapter.changeCursor(db.list());
                adapter.notifyDataSetChanged();
                Snackbar.make(view, "usunięto " + animal.getGatunek(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
//            String nowy = (String) extras.get("wpis");
//            target.add(nowy);
            Animal newAnimal = (Animal) extras.getSerializable("newAnimal");
            this.db.addAnimal(newAnimal);
            adapter.changeCursor(db.list());
            adapter.notifyDataSetChanged();
        }

        if (requestCode == 2 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Animal newAnimal = (Animal) extras.getSerializable("newAnimal");
            this.db.updateAnimal(newAnimal);
            adapter.changeCursor(db.list());
            adapter.notifyDataSetChanged();
        }

    }

    public void nowyWpis(MenuItem menuItem){
        Intent intent = new Intent(this, DodajWpis.class);
        startActivityForResult(intent, 1);
    }


}
