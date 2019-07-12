package com.alanger.waitermobile;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
PedidosListosFragment.OnFragmentInteractionListener,
ListMesasFragment.OnFragmentInteractionListener{


    private static Fragment myFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myFragment = new ListMesasFragment(this);


        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, myFragment).commit();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFragment.onStart();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(View view){
        int id = view.getId();
        if(id==R.id.fab){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, myFragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.version) {
            try {
                Toast.makeText(getBaseContext(),"Versión "+BuildConfig.VERSION_NAME+" code."+BuildConfig.VERSION_CODE,Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        }
        if (id == R.id.logout) {

            //showPopupRecomendacion("¿Desea Cerrar Sesión?");



            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mesas) {

            myFragment = new ListMesasFragment(this);


            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, myFragment).commit();
        } else if (id == R.id.nav_pedidos) {

            myFragment = new PedidosListosFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, myFragment).commit();
        } else if (id == R.id.nav_money) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
