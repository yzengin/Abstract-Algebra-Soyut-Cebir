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

public class CarpimMain extends AppCompatActivity {


    Button carpimAltGrupButton;
    Button carpimKosetButton;
    Button carpimUretecButton;
    Button islemleriTemizleBtn;
    EditText nDegeriniGir;
    TextView carpimUreteclerText;
    TextView altGrupTextView;
    TextView kosetTextView;

    int elemanSayisi;

    int kontrol = 0;
    int uretec = 0;
    int mertebe = 0;
    boolean asalmi = false;
    boolean yok = false;
    boolean uretecKontrol = true;

    ArrayList<Integer> pbs = new ArrayList<>();
    ArrayList<Integer> koset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_carpim_main );

        nDegeriniGir = findViewById( R.id.carpimNDegeriniGirEdit );
        carpimUretecButton = findViewById( R.id.carpimUretecBtn );
        carpimKosetButton = findViewById( R.id.carpimKosetBtn );
        carpimAltGrupButton = findViewById( R.id.carpimAltGrupBtn );
        islemleriTemizleBtn = findViewById( R.id.islemlerTemizleBtn );


        carpimUreteclerText = findViewById( R.id.carpimUretecText );
        kosetTextView = findViewById( R.id.carpimKosetlerTextView );
        altGrupTextView = findViewById( R.id.carpimAltGruplarTextView );


        carpimUretecButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty( nDegeriniGir.getText().toString() )) {
                    carpimAltGrupButton.setVisibility( View.VISIBLE );
                    carpimUretecButton.setVisibility( View.GONE );
                    islemleriTemizleBtn.setVisibility( View.VISIBLE );
                    elemanSayisi = Integer.parseInt( nDegeriniGir.getText().toString() );
                    for (int i = 2; i < elemanSayisi; i++) {
                        if ((elemanSayisi % i) == 0) {
                            kontrol++;
                            break;
                        }
                    }
                    if (kontrol == 1) {
                        asalmi = false;
                    } else
                        asalmi = true;
                    carpimUretec();
                }else{
                    Toast.makeText( CarpimMain.this, "Lütfen N Değerini Giriniz!", Toast.LENGTH_SHORT ).show();
                }
                
            }
        } );

        carpimAltGrupButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carpimAltGrup();
            }
        } );

        carpimKosetButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carpimKoset();
            }
        } );

        islemleriTemizleBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islemleriTemizleBtn.setVisibility( View.GONE );
                carpimUretecButton.setVisibility( View.VISIBLE );
                carpimAltGrupButton.setVisibility( View.GONE );
                carpimKosetButton.setVisibility( View.GONE );
                carpimUreteclerText.setText( "" );
                altGrupTextView.setText( "" );
                kosetTextView.setText( "" );
            }
        } );
    }

    private void carpimKoset() {
        if (yok) {
            carpimKosetButton.setVisibility( View.GONE );
            carpimAltGrupButton.setVisibility( View.GONE );
            kosetTextView.setText( "" );
            kosetTextView.append( "Kosetler:\n" );
            for (int i = 0; i < (pbs.size()); i++) {
                int s = (mertebe / pbs.get( i ));
                int b = s;
                kosetTextView.append( "\n{" );

                while (s <= mertebe) {
                    int a = (int) (Math.pow( uretec, s ) % elemanSayisi);
                    kosetTextView.append( "" + a );
                    s += b;
                    if (s <= mertebe)
                        kosetTextView.append( " ," );
                    koset.add( a );
                }
                kosetTextView.append( "}\n\n" );
                for (int e = 1; e < elemanSayisi; e++) {
                    kosetTextView.append( "Koset=> " + e + " * H = {" );
                    int a = 1;
                    for (int z : koset) {
                        kosetTextView.append( "" + (z * e) % elemanSayisi );
                        if (a < koset.size())
                            kosetTextView.append( "," );
                        a++;
                    }
                    kosetTextView.append( "}\n" );
                }
                koset.clear();
            }
        }

    }

    private void carpimAltGrup() {
        altGrupTextView.setText( "" );
        carpimKosetButton.setVisibility( View.VISIBLE );
        if (yok) {
            altGrupTextView.append( "Alt Grupları (" + pbs.size() + "):\n" );
            altGrupTextView.append( "{1}\n" );
            for (int i = 1; i < (pbs.size() - 1); i++) {
                int s = (mertebe / pbs.get( i ));
                int b = s;
                altGrupTextView.append( "{" );

                while (s <= mertebe) {
                    int a = (int) (Math.pow( uretec, s ) % elemanSayisi);
                    altGrupTextView.append( "" + a );

                    s += b;
                    if (s <= mertebe)
                        altGrupTextView.append( ", " );

                }
                altGrupTextView.append( "}\n" );
            }
            altGrupTextView.append( "{Z * " + elemanSayisi + " , .}" );
        }
    }

    private void carpimUretec() {
        if (!TextUtils.isEmpty( nDegeriniGir.getText().toString() )) {

            carpimUreteclerText.setText( "" );

            if (asalmi) {
                int sayacC = 0;

                int[][] sayiC = new int[elemanSayisi][elemanSayisi];
                int[] elemanlariC = new int[elemanSayisi];

                for (int i = 1; i < elemanSayisi; i++) {
                    elemanlariC[i] = i;
                }
                for (int u = 2; u < elemanSayisi; u++) {
                    for (int e = 1; e < elemanSayisi; e++) {
                        sayiC[u][e] = (int) (Math.pow( elemanlariC[u], elemanlariC[e] ) % elemanSayisi);
                    }
                }

                for (int u = 2; u < elemanSayisi; u++) {
                    for (int i = 1; i < elemanSayisi; i++) {
                        for (int e = 1; e < elemanSayisi; e++) {
                            if (i == sayiC[u][e]) {
                                sayacC++;
                                break;
                            }
                        }
                    }
                    if (sayacC == (elemanSayisi - 1)) {
                        carpimUreteclerText.append( "Üreteç : " + u + "\n" );
                        yok = true;
                        if (uretecKontrol) {
                            uretecKontrol = false;
                            uretec = u;
                        }
                    }
                    sayacC = 0;
                }

                mertebe = elemanSayisi - 1;
                for (int i = 1; i <= mertebe; i++) {
                    if ((mertebe % i) == 0) {
                        pbs.add( i );
                    }
                }

            }
            if(asalmi == false)
            {
                Log.e( "SAKARYAEROR",String.valueOf( asalmi) );
                ArrayList <Integer> list = new ArrayList <Integer> ();
                ArrayList <Integer> list2 = new ArrayList <Integer> ();
                ArrayList <Integer> list3 = new ArrayList <Integer> ();
                int deger = 0;

                for(int i = 2 ; i <= elemanSayisi ; i++)
                {
                    if((elemanSayisi % i) == 0)
                    {
                        list.add(i);
                    }
                }

                for(int e = 2 ; e < elemanSayisi ; e++)
                {
                    for(int s = 2 ; s <= e ; s++)
                    {
                        if((e % s) == 0)
                        {
                            list2.add(s);
                        }
                    }
                    for(int i = 0 ; i < list.size() ; i++)
                    {
                        for(int s = 0 ; s < list2.size() ; s++)
                        {
                            if(list.get(i) == list2.get(s))
                            {
                                deger++;
                            }
                        }
                    }
                    if(deger < 1)
                    {
                        list3.add(e);
                    }
                    list2.clear();
                    deger = 0;
                }
                list3.add(1);
                Collections.sort(list3);
                int[][] sayiC2 = new int[list3.size()][list3.size()];
                int sayacC2 = 0 ;

                for(int u = 0 ; u < list3.size() ; u++)
                {
                    for(int e = 0 ; e < list3.size() ; e++)
                    {
                        sayiC2[u][e] = (int) (Math.pow(list3.get(u) , e)%elemanSayisi);
                    }
                }

                for(int u = 0 ; u < list3.size() ; u++)
                {
                    for(int i = 0 ; i < list3.size() ; i++)
                    {
                        for(int e = 0 ; e < list3.size() ; e++)
                        {
                            if(list3.get(i) == sayiC2[u][e])
                            {
                                sayacC2++;
                                break;
                            }
                        }
                    }
                    if(sayacC2 == list3.size())
                    {
                        carpimUreteclerText.append("Üretec: " + list3.get(u)+"\n");
                        yok = true;
                        if(uretecKontrol)
                        {
                            uretecKontrol = false;
                            uretec = list3.get(u);
                        }
                    }
                    sayacC2 = 0;
                }
                mertebe = list3.size();
                for(int i = 1 ; i <= mertebe ; i++)
                {
                    if((mertebe % i) == 0)
                    {
                        pbs.add(i);
                    }
                }
                carpimAltGrupButton.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        carpimAltGrup();
                    }
                } );
                carpimKosetButton.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        carpimKoset();
                    }
                } );

            }
            if(yok == false) {
                Toast.makeText( this, "Devirli Grup Olmadığından Üreteci Yoktur!", Toast.LENGTH_LONG ).show();
                carpimUreteclerText.setText( "Devirli Grup Olmadığından Üreteci Yoktur!" );
                carpimAltGrupButton.setVisibility( View.GONE );
                nDegeriniGir.setText( "" );
            }

        } else {
            Toast.makeText( CarpimMain.this, "Lütfen N Değerini Giriniz!", Toast.LENGTH_SHORT ).show();
        }
    }

}
