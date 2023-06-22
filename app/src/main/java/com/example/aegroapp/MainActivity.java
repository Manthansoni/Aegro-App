package com.example.aegroapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        this.setSupportActionBar(toolbar);
//                if (getSupportActionBar() != null) {
//            this.getSupportActionBar().hide();
//        }




        bottomNavigationView = findViewById(R.id.bottomNav);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, new HomeFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        openFragment(HomeFragment.newInstance());
                        return true;
                    case R.id.liveStock:
                        openFragment(com.example.aegroapp.LivestockFragment.newInstance());
                        return true;
                    case R.id.profile:
                        openFragment(com.example.aegroapp.ProfileFragment.newInstance());
                        return true;
                }
                return false;
            }
        });
    }

    private void openFragment(Fragment fragment) {
//        Log.d(TAG, "openFragment: ");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContainer, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

}