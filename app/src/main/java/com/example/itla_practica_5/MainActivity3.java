package com.example.itla_practica_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView tvName, tvSex;
    String name, sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        sex = extras.getString("sex");

        tvName = findViewById(R.id.tvName);
        tvSex = findViewById(R.id.tvSex);
        tvName.setText(name);
        tvSex.setText(sex);

        findViewById(R.id.btnImage).setOnClickListener(view -> OpenImageActivity());

        findViewById(R.id.btnReflexition).setOnClickListener(view -> OpenReflexionActivity());

        findViewById(R.id.btnBack).setOnClickListener( view -> finish());

        findViewById(R.id.btnClose).setOnClickListener( view -> CloseApp());
    }

    private void OpenImageActivity(){
        Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
        intent.putExtra("name", name);
        intent.putExtra("sex", sex);
        startActivity(intent);
    }

    private void OpenReflexionActivity(){
        Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
        intent.putExtra("name", name);
        intent.putExtra("sex", sex);
        startActivity(intent);
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