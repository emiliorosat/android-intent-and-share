package com.example.itla_practica_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.btn).setOnClickListener(view -> getIn());
        findViewById(R.id.btnBack).setOnClickListener( view -> finish());
        findViewById(R.id.btnClose).setOnClickListener( view -> CloseApp());
    }

    private void getIn(){
        String sex, name;
        RadioButton rbMale = findViewById(R.id.rbMale);
        EditText etName = (EditText) findViewById(R.id.etName);
        name = String.valueOf(etName.getText());

        if(name.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Escriba su nombre", Toast.LENGTH_LONG);
            toast.show();


        }else {

            if (rbMale.isChecked()) {
                sex = "Masculino";
            } else {
                sex = "Femenino";
            }

            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            intent.putExtra("name", name);
            intent.putExtra("sex", sex);
            startActivity(intent);
        }
    }

    private void CloseApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Desea Cerrar la App?");

        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }
}