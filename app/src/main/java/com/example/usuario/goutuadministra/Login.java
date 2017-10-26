package com.example.usuario.goutuadministra;

import android.app.ProgressDialog;
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

    //    Login firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText txtcorreo, txtpassword;
    Button boton;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //        firebase
        txtcorreo= (EditText) findViewById(R.id.txtUsuario);
        txtpassword=(EditText) findViewById(R.id.txtPassword);

        txtcorreo.setText("royallr77@gmail.com");
        txtpassword.setText("123456789");

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

        loading = new ProgressDialog(this);
        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Cargando");
        progressDialog.show();

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
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Acceso correcto",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }
}
