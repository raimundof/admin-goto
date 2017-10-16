package com.example.usuario.goutuadministra;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText txtcorreo, txtpassword;

    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setContentView(R.layout.activity_login);
//        firebase
        txtcorreo= (EditText) findViewById(R.id.txtUsuario);
        txtpassword=(EditText) findViewById(R.id.txtPassword);
        mAuth = FirebaseAuth.getInstance();
        boton =(Button)findViewById(R.id.btnIniciar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iniciar();
            }
        });

    }

    private void Iniciar(){
        String correo = txtcorreo.getText().toString();
        String paswd = txtpassword.getText().toString();
        mAuth.signInWithEmailAndPassword(correo, paswd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("miTag", "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w("mitagg", "signInWithEmail:failed", task.getException());
                            Toast.makeText(Login.this, "Error al iniciar session",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                        // ...
                    }
                });
    }
}
