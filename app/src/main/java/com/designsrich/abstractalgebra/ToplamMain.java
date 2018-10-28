package com.designsrich.abstractalgebra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ToplamMain extends AppCompatActivity {


    Button toplamButton;
    Button altGrupButton;
    Button kosetButton;
    Button islemleriTemizleBtn;
    EditText nDegeriniGir;

    TextView toplamUretecText;
    TextView altGrupTextView;
    TextView kosetTextView;

    int eleman_sayisi;
    int sayac = 0;


    ArrayList<Integer> pbst = new ArrayList<>();
    ArrayList<Integer> kaset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_toplam_main );

        toplamButton = findViewById( R.id.toplamUretecBulBtn );
        altGrupButton = findViewById( R.id.toplamAltGrupButton );
        kosetButton = findViewById( R.id.toplamKosetButton );
        islemleriTemizleBtn = findViewById( R.id.islemlerTemizleBtn );
        nDegeriniGir = findViewById( R.id.toplamNDegeriniGirEdit );
        toplamUretecText = findViewById( R.id.toplamUreteclerText );
        kosetTextView = findViewById( R.id.toplamKosetlerTextView );
        altGrupTextView = findViewById( R.id.toplamAltGruplarTextView);


        toplamButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toplamUretec();
            }
        } );



        altGrupButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toplamAltGrup();
            }
        } );

        kosetButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toplamKoset();
            }
        } );



        islemleriTemizleBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islemleriTemizleBtn.setVisibility( View.GONE );
                toplamButton.setVisibility( View.VISIBLE );
                altGrupButton.setVisibility( View.GONE );
                kosetButton.setVisibility( View.GONE );
                toplamUretecText.setText( "" );
                altGrupTextView.setText( "" );
                kosetTextView.setText( "" );
            }
        } );
    }


    private void toplamKoset() {

        kosetTextView.setText( "" );
        kosetTextView.append( "Kosetler:\n" );

        for (int i = 0; i < (pbst.size()); i++) {
            int s = (eleman_sayisi / pbst.get( i ));
            int b = s;
            kosetTextView.append( "\n{" );
            while (s <= eleman_sayisi) {
                int a = (1 * s) % eleman_sayisi;
                kosetTextView.append( "" + a );
                s += b;
                if (s <= eleman_sayisi)
                    kosetTextView.append( "," );
                kaset.add( a );
            }
            kosetTextView.append( "}\n\n" );
            for (int e = 0; e < eleman_sayisi; e++) {
                kosetTextView.append( "Koset =>" + e + "+H={" );
                int a = 0;
                for (int z : kaset) {
                    a++;
                    kosetTextView.append( "" + (z + e) % eleman_sayisi );
                    if (a < kaset.size())
                        kosetTextView.append( "," );

                }
                kosetTextView.append( "}\n" );


            }
            kaset.clear();
        }
    }

    private void toplamUretec() {
        if (!TextUtils.isEmpty( nDegeriniGir.getText().toString() )) {
            toplamButton.setVisibility( View.GONE );
            islemleriTemizleBtn.setVisibility( View.VISIBLE );
            altGrupButton.setVisibility( View.VISIBLE );
            eleman_sayisi = Integer.parseInt( nDegeriniGir.getText().toString() );

            int[][] sayi = new int[eleman_sayisi][eleman_sayisi];
            int[] elemanlari = new int[eleman_sayisi];

            if (eleman_sayisi >= 1) {
                // Toast.makeText( MainActivity.this, "Üreteç : 1", Toast.LENGTH_LONG).show();
                toplamUretecText.setText( "Üreteç : 1" );
            } else
                //Toast.makeText( MainActivity.this, "Üreteç Yok", Toast.LENGTH_LONG).show();
                toplamUretecText.setText( "Üreteç Yok" );

            for (int i = 0; i < eleman_sayisi; i++) {
                elemanlari[i] = i;
            }
            for (int u = 2; u < eleman_sayisi; u++) {
                for (int e = 0; e < eleman_sayisi; e++) {
                    sayi[u][e] = (elemanlari[u] * elemanlari[e]) % eleman_sayisi;
                }
            }

            for (int u = 2; u < eleman_sayisi; u++) {
                for (int i = 0; i < eleman_sayisi; i++) {
                    for (int e = 0; e < eleman_sayisi; e++) {
                        if (i == sayi[u][e]) {
                            sayac++;
                        }
                    }
                }
                if (sayac == eleman_sayisi) {

                    ArrayList<Integer> listemyeni = new ArrayList<>();
                    listemyeni.add( u );
                    for (int bey = 0; bey < listemyeni.size(); bey++) {
                        toplamUretecText.append( "\nÜreteç : " + listemyeni.get( bey ) );
                        //Toast.makeText( MainActivity.this, "Üretecler :" + listemyeni.get( bey ), Toast.LENGTH_SHORT ).show();
                    }

                    Log.e( "SAKARYA", listemyeni.toString() );
                    /*islemlerTextView.setText("\n Üreteç : "+listemyeni.toString());*/
                    System.out.println( u );
                }
                sayac = 0;
            }
            for (int i = 1; i <= eleman_sayisi; i++) {
                if ((eleman_sayisi % i) == 0) {
                    pbst.add( i );
                }
            }
        } else {
            Toast.makeText( ToplamMain.this, "Lütfen N Değerini Giriniz!", Toast.LENGTH_SHORT ).show();
        }
    }


    public void toplamAltGrup() {
        kosetButton.setVisibility( View.VISIBLE );
        altGrupTextView.setText( "" );
        altGrupTextView.append( "Alt Grupları (" + pbst.size() + "):\n" );
        altGrupTextView.append( "\n {0} \n" );
        for (int i = 1; i < (pbst.size() - 1); i++) {
            int s = (eleman_sayisi / pbst.get( i ));
            int b = s;
            altGrupTextView.append( "{" );
            while (s <= eleman_sayisi) {
                int a = (1 * s) % eleman_sayisi;
                altGrupTextView.append( "" + a );
                // Toast.makeText( MainActivity.this, "Alt Grup :" + a, Toast.LENGTH_SHORT ).show();

                s += b;
                if (s <= eleman_sayisi)
                    altGrupTextView.append( "," );

            }
            altGrupTextView.append( "}\n" );

        }
        altGrupTextView.append( "{Z " + eleman_sayisi + " , +}" );

    }
}
