package id.my.nyuciin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Home extends AppCompatActivity {

    //deklarasi button CTA
    AppCompatImageView cta_scan, cta_topup, cta_transfer, cta_cashout;

    //deklarasi FAB
    FloatingActionButton pick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //assign CTA Button
        cta_scan = findViewById(R.id.ctaScan);
        cta_topup = findViewById(R.id.ctaTopup);
        cta_transfer = findViewById(R.id.ctaTransfer);
        cta_cashout = findViewById(R.id.ctaCashout);

        //assign bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.market:
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), History.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });

        //assign fab
        pick = findViewById(R.id.pick);

        cta_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(Home.this);
                intentIntegrator.setPrompt("gunakan tombol volume atas untuk flash");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });

        cta_topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Topup", Toast.LENGTH_SHORT).show();
            }
        });

        cta_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Transfer", Toast.LENGTH_SHORT).show();
            }
        });

        cta_cashout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Cashout", Toast.LENGTH_SHORT).show();
            }
        });


        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Maps.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //inisialis intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data
        );
        //check conditions
        if (intentResult.getContents() != null){
            //ketika result content tidak kosong
            //inisialis alertdialog
            AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
            //menambahkan judul pada dialog
            builder.setTitle("Hasil");
            //menampilkan hasil scann
            builder.setMessage(intentResult.getContents());
            //set positive button
            builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            //show alert dialog
            builder.show();
        }else{
            Toast.makeText(this, "kamu tidak scan apapun", Toast.LENGTH_SHORT).show();
        }
    }
}