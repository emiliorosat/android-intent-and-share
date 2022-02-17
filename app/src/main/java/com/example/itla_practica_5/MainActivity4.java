package com.example.itla_practica_5;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity4 extends AppCompatActivity {
    String name, sex;
    TextView tvName, tvSex;
    ImageView ivImage;
    ArrayList<Integer> maleImage, femaleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        sex = extras.getString("sex");

        tvName = findViewById(R.id.tvName);
        tvSex = findViewById(R.id.tvSex);
        ivImage = findViewById(R.id.ivImage);
        ivImage.setDrawingCacheEnabled(true);
        tvName.setText(name);
        tvSex.setText(sex);

        findViewById(R.id.btnBack).setOnClickListener( view -> finish());
        findViewById(R.id.btnClose).setOnClickListener( view -> CloseApp());
        findViewById(R.id.btnShare).setOnClickListener(view -> ShareContent());

        if(sex.equals("Masculino")){
            setImageToMale();
        }else{
            setImageToFemale();
        }
    }

    private void setImageToFemale(){
        femaleImage = new ArrayList<Integer>();
        femaleImage.add(R.drawable.female1);
        femaleImage.add(R.drawable.female2);
        femaleImage.add(R.drawable.female3);
        femaleImage.add(R.drawable.female4);
        femaleImage.add(R.drawable.female5);
        femaleImage.add(R.drawable.female6);
        Collections.shuffle(femaleImage);
        ivImage.setImageResource(femaleImage.get(0));
    }

    private  void  setImageToMale(){
        maleImage = new ArrayList<Integer>();
        maleImage.add(R.drawable.male1);
        maleImage.add(R.drawable.male2);
        maleImage.add(R.drawable.male3);
        maleImage.add(R.drawable.male4);
        maleImage.add(R.drawable.male5);
        Collections.shuffle(maleImage);
        ivImage.setImageResource(maleImage.get(0));
    }

    private  void  ShareContent(){
        Bitmap bitImage = ivImage.getDrawingCache();

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitImage, "Image", null);
        Uri imageUri = Uri.parse(path);

        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_STREAM,  imageUri );
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