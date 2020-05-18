package com.zut.lab04androidnative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DodajWpis extends AppCompatActivity {

    public void wyslij (View view){
        EditText editText = findViewById(R.id.editText);
        String textField = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("wpis", textField);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);
    }
}
