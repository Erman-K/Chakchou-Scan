package fr.atlasdev.chakchouscan;

import static android.R.layout.simple_list_item_1;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Accueil extends AppCompatActivity implements View.OnClickListener {

    Button scanBtn;
    private ListView listView;
    private FirebaseListAdapter<ListeItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        listView = (ListView) findViewById(R.id.recettes);
        DatabaseReference recettesReference = FirebaseDatabase.getInstance("https://chakchouscan-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("recettes");
        Log.d("Connexion", "Connecté en théorie" + recettesReference);
//        recettesReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                    ListeItem listeItem = postSnapshot.getValue(ListeItem.class);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        FirebaseListOptions<ListeItem> options = new FirebaseListOptions.Builder<ListeItem>()
                .setLayout(R.layout.liste_item)
                .setQuery(recettesReference, ListeItem.class)
                .build();
        Log.d("Connexion", "Options créées en théorie " + options);

        adapter = new FirebaseListAdapter<ListeItem>(options) {

            @Override
            protected void populateView(@NonNull View v, @NonNull ListeItem model, int position) {
                Log.d("Connexion", "Lancement de population "+ model.getTitle() + " et view: " + v);
                TextView title = v.findViewById(R.id.liste_item);
                title.setText(model.getTitle());
                Log.d("Connexion", "Ajouté en théorie " + model.getTitle());
            }

        };
        listView.setAdapter(adapter);

        adapter.startListening();

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.accueil);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.accueil:
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
    }

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

                builder.setMessage(result.getContents());

                Ingredient rep = null;
                try {
                    rep = new Handler().execute(result.getContents()).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                builder.setMessage(rep.toString());

                builder.setTitle("Scanning Result");
                /**/

                AlertDialog dialog= builder.create();
                /*  AlertDialog.Builder alertadd = new AlertDialog.Builder(MessageDemo.this);*/
                LayoutInflater factory = LayoutInflater.from(Accueil.this);
                final View view = factory.inflate(R.layout.activity_dialog, null);
                dialog.setView(view);
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /* dialog.dismiss();*/
                        startActivity(new Intent(Accueil.this, Accueil.class));
                    }
                });

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
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}