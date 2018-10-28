package com.designsrich.abstractalgebra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button carpimButton;
    private Button toplamButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        carpimButton = findViewById( R.id.carpimBtn);
        toplamButton = findViewById(R.id.toplamBtn);

        toplamButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toplam = new Intent( MainActivity.this,ToplamMain.class);
                startActivity( toplam);
            }
        } );
        carpimButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent carpim = new Intent( MainActivity.this,CarpimMain.class);
                startActivity( carpim);
            }
        } );
    }

}
