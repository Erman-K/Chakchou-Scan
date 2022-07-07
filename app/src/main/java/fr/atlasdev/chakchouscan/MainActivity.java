package fr.atlasdev.chakchouscan;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import com.squareup.picasso.Picasso;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.appcompat.app.AlertDialog;


import android.content.DialogInterface;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLogOut;
    FirebaseAuth mAuth;
    Button scanBtn;
    Button btnFinish;
    Button btnAgain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.logout);
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.accueil:
                        startActivity(new Intent(getApplicationContext(),Accueil.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.logout:
                        return true;
                    case R.id.liste:
                        startActivity(new Intent(getApplicationContext(),Liste.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);

        /*setContentView(R.layout.activity_dialog);

        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this, MainActivity.class));

        });

        btnAgain = findViewById(R.id.btnAgain);
        btnAgain.setOnClickListener(this);*/

        btnLogOut = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();

        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, Login.class));
        });



    }
    /*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogOut = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();

        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, Login.class));
        });

    }*/

/* Scan Code*/
    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode(){

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){



        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);


                Ingredient rep = null;
                try {
                    rep = new Handler().execute(result.getContents()).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*setContentView(R.layout.activity_dialog);
                ImageView imageView = findViewById(R.id.dialog_imageview);
                String url = rep.getUrlAsString();
                Picasso.get().load(url).into(imageView);
                builder.setView(R.id.dialog_imageview);
                builder.setMessage(rep.toString());
                builder.setTitle("Scanning Result");*/
               /**/

                AlertDialog dialog= builder.create();
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View view = factory.inflate(R.layout.activity_dialog, null);
                dialog.setView(view);
                /*builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                    }
                });*/
                dialog.setTitle("Results:");
                dialog.setMessage(rep.toString());

                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Scan Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        scanCode();
                    } });

                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Finish", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }});


                dialog.show();

            }
            else{
                Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    /* Scan Code*/


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }
}

