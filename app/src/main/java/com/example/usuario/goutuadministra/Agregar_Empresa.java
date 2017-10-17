package com.example.usuario.goutuadministra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Agregar_Empresa extends AppCompatActivity {
    private Spinner lista;
    private SpinnerAdapter adapter = null;
    private List<Categoria> arrayCategotia = null;


//  cropzzz
    Button elegir, subir;
    ImageView imagen;
    ProgressDialog loading;
    private final static int GalleryPick = 274;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_empresa);


        subir = (Button) findViewById(R.id.btnSubir);

        imagen = (ImageView) findViewById(R.id.imagen);

        loading = new ProgressDialog(this);
        final ProgressDialog progressDialog = new ProgressDialog(this);

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("image");

        ValueEventListener escuchador_imagen = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String url = dataSnapshot.getValue(String.class);

                Glide.with(getApplicationContext())

                        .load(url)
                        .error(R.drawable.cesta)
                        .into(imagen);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase.addValueEventListener(escuchador_imagen);


        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen "), GalleryPick);
            }
        });

        subir.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onClick(View view) {

                if (resultUri != null)
                {
                    progressDialog.setTitle("Subiendo");
                    progressDialog.show();
//                   loading.setMessage("Esta subiendo");
//                   loading.show();

                    StorageReference childRef = storageRef.child("image.jpg");
                    UploadTask uploadTask = childRef.putFile(resultUri);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Uri dowloadUrl = taskSnapshot.getDownloadUrl();
                            FirebaseDatabase.getInstance().getReference().child("image").setValue(dowloadUrl.toString());
                            Toast.makeText(Agregar_Empresa.this, "SUBIDA EXITOSA", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Agregar_Empresa.this, "Imagen no subida -> " + e, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Subiedo imagen... " + ((int) progress) + "%...");
                        }
                    });
                }
                else {
                    Toast.makeText(Agregar_Empresa.this, "No seleccion ningna imagen", Toast.LENGTH_SHORT).show();
                }

            }
        });

//        List<Categoria> lis= new ArrayList<>();
//
//        lis.add(new Categoria("hola "));
//        lis.add(new Categoria("hola dsgdeghsg"));
//        lis.add(new Categoria("gfgkkd"));
//
//        lista = (Spinner) findViewById(R.id.spinner);
//        lista.setAdapter(new SpinnerAdapter(getApplicationContext(),lis));
//        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }
//    private void getListaCategorias()
//    {
//        for (int i = 0; i<2; i++) {
//            arrayCategotia.add(new Categoria ("Categoria"));
//        }
//        adapter = new SpinnerAdapter(getApplicationContext(), arrayCategotia);
//        lista.setAdapter(adapter);
//    }
    Uri resultUri;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), resultUri);
                imagen.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            resultUri = data.getData();
            CropImage.activity(resultUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(3,2)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                imagen.setImageURI(resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}