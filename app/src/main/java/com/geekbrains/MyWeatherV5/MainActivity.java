package com.geekbrains.MyWeatherV5;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.geekbrains.MyWeatherV5.ui.gallery.GalleryFragment;
import com.geekbrains.MyWeatherV5.ui.weather.WeatherFragment;
import com.geekbrains.MyWeatherV5.ui.slideshow.SlideshowFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setHomeFragment();
        setOnClickForSideMenuItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 1) {
            navigationView.setCheckedItem(R.id.nav_home);
        }
        super.onBackPressed();
    }

    private void setOnClickForSideMenuItems() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home: {
                        setHomeFragment();
                        drawer.close();
                        break;
                    }
                    case R.id.nav_gallery: {
                        setGalleryFragment();
                        drawer.close();
                        break;
                    }
                    case R.id.nav_slideshow: {
                        setSlideshowFragment();
                        drawer.close();
                        break;
                    }
                }
                return true;
            }
        });
    }

    private void setHomeFragment() {
        WeatherFragment fragment = new WeatherFragment();
        setFragment(fragment);
    }

    private void setGalleryFragment() {
        setFragment(new GalleryFragment());
    }

    private void setSlideshowFragment() {
        setFragment(new SlideshowFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}