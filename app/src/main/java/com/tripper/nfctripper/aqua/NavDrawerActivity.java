package com.tripper.nfctripper.aqua;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tripper.nfctripper.aqua.adapter.ProductAdapter;
import com.tripper.nfctripper.aqua.apihelper.ProductService;
import com.tripper.nfctripper.aqua.apihelper.RetrofitClient;
import com.tripper.nfctripper.aqua.apihelper.TestService;
import com.tripper.nfctripper.aqua.model.productModel.Product;
import com.tripper.nfctripper.aqua.model.productModel.ResultProduct;
import com.tripper.nfctripper.aqua.model.testModel.ResultTest;
import com.tripper.nfctripper.aqua.model.testModel.Test;
import com.tripper.nfctripper.aqua.model.userModel.User;
import com.tripper.nfctripper.aqua.navigation_manager.FragmentNavigationManager;
import com.tripper.nfctripper.aqua.navigation_manager.NavigationManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView name;
    TextView email;




    User user;



    public NavigationManager mNavigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        user = ((MyApplication) this.getApplication()).getUser();
        View header = navigationView.getHeaderView(0);
        name = (TextView) header.findViewById(R.id.name);
        email = (TextView) header.findViewById(R.id.email);

        if(user!=null) {
            name.setText(user.getName());
            email.setText(user.getEmail());
        }
        else
        {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }



        mNavigationManager = FragmentNavigationManager.obtain(this);
        selectFirstItemAsDefault();














    }



    private void selectFirstItemAsDefault() {
        if (mNavigationManager != null) {
            setProductFragment();
        }
    }

    private void  setProductFragment()
    {
        String firstActionMovie = getResources().getString(R.string.app_name);
        mNavigationManager.showFragmentProduct(firstActionMovie);
        getSupportActionBar().setTitle(firstActionMovie);
    }







    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_product) {
            setProductFragment();
        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
