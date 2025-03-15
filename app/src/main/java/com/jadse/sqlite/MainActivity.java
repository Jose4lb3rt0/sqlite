package com.jadse.sqlite;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.firestore.FirebaseFirestore;
import com.jadse.sqlite.databinding.ActivityMainBinding;
import com.jadse.sqlite.model.Usuario;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static String URL_IMAGE = "https://oaemdl.es/images/optica/";
    public static String URL_API = "https://oaemdl.es/optica_sweb_php/";
    public static Usuario usuario = null;

    FirebaseFirestore fFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fFirestore = FirebaseFirestore.getInstance();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);


    }

}