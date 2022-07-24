package com.example.aplicacion1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacion1.databinding.ActivityDetalle2Binding;
import com.example.aplicacion1.databinding.ActivityMainBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Bitmap bitmap;
    ActivityResultLauncher<Intent> activityResultLauncher;
    static float valor = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRegistrar.setOnClickListener(v -> {
            String clave = binding.txtClave.getText().toString();
            String clave2= binding.txtClave2.getText().toString();
            String nombre = binding.txtNombre.getText().toString();
            String email= binding.txtEmail.getText().toString();
            String rol=binding.txtRol.getText().toString();
            String email1=binding.txtEma.getText().toString();

            int cont=0;

            Context context = MainActivity.this;
            if (nombre.isEmpty()){
                Toast.makeText(MainActivity.this,"Campo nombre vacio, llenar por favor",Toast.LENGTH_SHORT).show();
            }else if (clave.isEmpty()){
                Toast.makeText(MainActivity.this,"Campo clave vacio, llenar por favor",Toast.LENGTH_SHORT).show();
            }else if (clave2.isEmpty()){
                Toast.makeText(MainActivity.this,"Campo repetir clave vacio, llenar por favor",Toast.LENGTH_SHORT).show();
            }else if (clave.length()<5){
                Toast.makeText(MainActivity.this,"La clave debe tener minimo 5 caracteres",Toast.LENGTH_SHORT).show();
            }else
            if (clave.equals(clave2)){
                if (email.equals(email1)){
                    if (rol.equalsIgnoreCase("Admin") || rol.equalsIgnoreCase("Invitado")){
                        int cont1 = clave.length();
                        char[] karen = clave.toCharArray();

                        for(int i=0; i<karen.length; i++){
                            if( (karen[i] >=33 && karen[i]<=47) || (karen[i] >=58 && karen[i]<=64) || (karen[i] >=91 && karen[i]<=96) || (karen[i] >=123 && karen[i]<=126) ){
                                cont++;
                            }
                        }
                        if (cont>=4 && cont1>=12){
                            valor=5;
                        }else if(cont>=2 && cont1>=10){
                            valor=4;
                        }else if(cont>=1 && cont1>=8) {
                            valor = 3;
                        }else if(cont1>=8) {
                            valor = 2;
                        }else{
                            valor=1;
                        }
                        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                        Matcher mather = pattern.matcher(email);

                        if (mather.find() == true) {
                            Toast.makeText(MainActivity.this,"El email ingresado es válido.",Toast.LENGTH_SHORT).show();
                            abrir(nombre, clave,valor,email,rol);
                        } else {
                            Toast.makeText(MainActivity.this,"El email ingresado es invalido.",Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Toast.makeText(MainActivity.this,"Este rol no existe, Solo puede ser admin o invitado",Toast.LENGTH_SHORT).show();
                        binding.txtRol.setText("");
                    }

                }else{
                    Toast.makeText(MainActivity.this,"Los emails no coinciden",Toast.LENGTH_SHORT).show();
                    binding.txtEma.setText("");
                }

            }else{
                Toast.makeText(MainActivity.this,"Las claves no coinciden",Toast.LENGTH_SHORT).show();
                binding.txtClave2.setText("");
            }
        });
        activityLaucher();
        binding.imgUsuario.setOnClickListener(v -> {
            abrirCamara();

        });
    }

    public void abrirCamara(){
        Intent camar= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activityResultLauncher.launch((camar));
    }

    public void activityLaucher(){
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode()==RESULT_OK){
                            if(result.getData() != null){
                                bitmap =result.getData().getExtras().getParcelable("data");
                                binding.imgUsuario.setImageBitmap(bitmap);
                            }
                        }
                    }
                });
    }
    private void abrir(String usua, String cla,float val,String ema,String rol) {
        Intent intent = new Intent(this, Detalle.class);
        Usuario usuario = new Usuario(usua,cla,val,ema,rol);
        intent.putExtra(Detalle.USUARIO_KEY,usuario);
        intent.putExtra(Detalle.BITMAP_KEY,bitmap);
        startActivity(intent);
    }
}