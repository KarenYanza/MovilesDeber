package com.example.aplicacion1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.aplicacion1.databinding.ActivityDetalle2Binding;

public class Detalle extends AppCompatActivity {

    public static final String USUARIO_KEY= "usuario";
    public static final String BITMAP_KEY="bitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetalle2Binding binding=ActivityDetalle2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView txt_resultado =findViewById(R.id.txt_resul);
        String msg_resultado="";
        float val= MainActivity.valor;

        Bundle extras=getIntent().getExtras();
        Usuario usua=extras.getParcelable(USUARIO_KEY);
        binding.setUsua(usua);
        Bitmap bitmap=extras.getParcelable(BITMAP_KEY);
        if (bitmap != null){
            binding.imageUsua.setImageBitmap(bitmap);
        }

        if(val==1){
            msg_resultado= "La clave de seguridad se considera Insegura";
            txt_resultado.setText(msg_resultado);
        }else if(val==2){
            msg_resultado= "La clave de seguridad se considera Baja";
            txt_resultado.setText(msg_resultado);
        }else if(val==3){
            msg_resultado= "La clave de seguridad se considera Media";
            txt_resultado.setText(msg_resultado);
        }else if(val==4){
            msg_resultado= "La clave de seguridad se considera Media Alta";
            txt_resultado.setText(msg_resultado);
        }else{
            msg_resultado= "La clave de seguridad se considera Alta";
            txt_resultado .setText(msg_resultado);
        }

    }
}