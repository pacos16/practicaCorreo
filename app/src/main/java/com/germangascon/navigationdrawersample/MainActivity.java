package com.germangascon.navigationdrawersample;

import android.content.Intent;
import android.os.Bundle;

import com.germangascon.navigationdrawersample.modelo.Account;
import com.germangascon.navigationdrawersample.modelo.Mail;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Account myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Parser parser=new Parser(this);
        parser.parse();

        myAccount=parser.getMyAccount();
        Collections.sort(myAccount.getMails());



    }

    @Override
    public void onBackPressed() {
        /**
         * Si el usuario pulsa el botón atrás mientras está mostrándose el menú del NavigationView,
         * hacemos que se cierre dicho menú, ya que el comportamiento por defecto es cerrar la
         * Activity.
         */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflamos el menú de la ActionBar
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Se ha hecho click en algún item del menú de la ActionBar
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment f;
        FragmentCorreos fragmentCorreos =new FragmentCorreos();
        fragmentCorreos.setContactos(myAccount.getContactos());
        // Se ha hecho click en algún item del NavigationView
        int id = item.getItemId();
        boolean enviar=false;
        String title= "Enviar";

        ArrayList<Mail> filtredMails=new ArrayList<>();
        if (id == R.id.nav_recibidos) {
            for (Mail m:myAccount.getMails()
                 ) {
                if(!m.isDeleted() && !m.isSpam() && !m.getFrom().equals(myAccount.getEmail())){
                    filtredMails.add(m);
                }
            }
            title="Recibidos";
        } else if (id == R.id.nav_enviados) {
            for (Mail m: myAccount.getMails()
                 ) {
                if(m.getFrom().equals(myAccount.getEmail())){
                    filtredMails.add(m);
                }
            }
           title="Enviados";
        } else if (id == R.id.nav_no_leidos) {
            for (Mail m: myAccount.getMails()
                 ) {
                if(m.getTo().equals(myAccount.getEmail()) && !m.isReaded() && !m.isDeleted()){
                    filtredMails.add(m);
                }
            }
           title="No leídos";
        } else if (id == R.id.nav_enviar) {
           enviar=true;
        } else if (id == R.id.nav_spam) {
            filtredMails=new ArrayList<>();
            for (Mail m:myAccount.getMails()
                 ) {
                if(m.isSpam()){
                    filtredMails.add(m);
                }
            }
            title="Spam";
        } else if (id == R.id.nav_papelera) {
            title="Papelera";

            for (Mail m: myAccount.getMails()
                 ) {
                if(m.isDeleted()){
                    filtredMails.add(m);
                }
            }
            title="Papelera";
        }
        if(!enviar) {
            Collections.sort(filtredMails);
            fragmentCorreos.setMails(filtredMails);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentCorreos).commit();
            setTitle(title);
        }else{
            //Intent i=new Intent();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
