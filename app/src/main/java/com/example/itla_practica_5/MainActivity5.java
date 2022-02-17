package com.example.itla_practica_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity5 extends AppCompatActivity {
    String name, sex;
    TextView tvName, tvSex, tvMessage;
    ArrayList<String> maleListReflexion, femaleListReflexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        sex = extras.getString("sex");

        tvName = findViewById(R.id.tvName);
        tvSex = findViewById(R.id.tvSex);
        tvMessage = findViewById(R.id.tvMessage);
        tvName.setText(name);
        tvSex.setText(sex);



        findViewById(R.id.btnBack).setOnClickListener( view -> finish());
        findViewById(R.id.btnClose).setOnClickListener( view -> CloseApp());
        findViewById(R.id.btnShare).setOnClickListener(view -> ShareContent());

        if( sex.equals("Masculino") ){
            MaleMessage();
        }else{
            FemaleMessage();
        }
    }

    private  void  MaleMessage(){
        maleListReflexion = new ArrayList<String>();
        maleListReflexion.add("Dios te hizo un hombre fuerte y valiente, está en ti demostrar que eso es cierto ¡Nunca te rindas! tu puedes soportar y superar cualquier barrera.");
        maleListReflexion.add("Seamos sinceros, no puedes cambiar el pasado, pero si puedes impactar el presente para que mañana este día sea un pasado mas positivo que los otros.");
        maleListReflexion.add("Si eres inteligente ¡No actúes sin pensar! pues dejarte llevar por el enojo es tu mayor enemigo.");
        maleListReflexion.add("Si siempre haces lo mismo, prepárate para tener los mismos resultados e incluso peores.");
        maleListReflexion.add("No pierdas a tu amigo por el deseo a una mujer, esos deseos van vienen, pero un verdadero amigo es dificil de encontrar.");
        Collections.shuffle(maleListReflexion);
        tvMessage.setText(maleListReflexion.get(0));
    }

    private  void  FemaleMessage(){
        femaleListReflexion = new ArrayList<String>();
        femaleListReflexion.add("No te dejes agobiar por la tristeza de un mal momento, recuerda que toda mujer poderosa se hizo fuerte a causa de un pasado triste." );
        femaleListReflexion.add("Eres una gran mujer, no necesitas esperar por nada ni por nadie para empezar a disfrutar tu vida y alcanzar todas tus metas" );
        femaleListReflexion.add("No permitas que nadie defina lo que es o no el éxito para ti. Ese es tu trabajo, define tus metas, trabaja y vive según tus reglas y serás feliz.");
        femaleListReflexion.add("Define el éxito con tus propios términos, consíguelo con tus propias reglas, y construye una vida de la que estés orgulloso.");
        femaleListReflexion.add("La mayor fortaleza y protección de la mujer hoy en día es su valentía infinita para enfrentarse al mundo y ser ella misma.");
        Collections.shuffle(femaleListReflexion);
        tvMessage.setText(femaleListReflexion.get(0));
    }

    private  void  ShareContent(){
        String message = (String) tvMessage.getText();
        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        if(share.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(share, "Compartir"));
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