package com.example.aplicacion1;


import android.os.Parcel;
import android.os.Parcelable;

public class Usuario  implements Parcelable{
    private String nombre;
    private String clave;
    private float valor;
    private String email;
    private String rol;

    public Usuario(String nombre, String clave, float valor, String email, String rol) {
        this.nombre = nombre;
        this.clave = clave;
        this.valor = valor;
        this.email = email;
        this.rol = rol;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    protected Usuario(Parcel in) {
        nombre = in.readString();
        clave = in.readString();
        valor = in.readFloat();
        email = in.readString();
        rol = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(clave);
        dest.writeFloat(valor);
        dest.writeString(email);
        dest.writeString(rol);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public static int clave (int valor){
        String clave = "";
        int cont=0;
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
        return valor;
    }
}
