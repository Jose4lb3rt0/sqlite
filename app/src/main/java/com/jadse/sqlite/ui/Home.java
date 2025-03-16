package com.jadse.sqlite.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jadse.sqlite.MainActivity;
import com.jadse.sqlite.R;
import com.jadse.sqlite.controller.UsuarioDao;
import com.jadse.sqlite.databinding.FragmentHomeBinding;
import com.jadse.sqlite.db.Db;
import com.jadse.sqlite.model.Usuario;

public class Home extends Fragment {
    FragmentHomeBinding binding;
    NavController navController;
    Context context;
    View view;

    Db db;
    Usuario usuario;
    UsuarioDao usuarioDao;
    FirebaseFirestore firestore;

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);
        usuarioDao = new UsuarioDao(context);
        firestore = FirebaseFirestore.getInstance();

        binding.btnCerrarSesion.setOnClickListener(v -> btnCerrarSesion_OnClick());
    }

    private void btnCerrarSesion_OnClick() {
        if (MainActivity.usuario != null && MainActivity.usuario.getCorreo() != null)
        {
            String usuarioId = MainActivity.usuario.getId();

            //session a null
            firestore.collection("usuarios")
                    .document(usuarioId)
                    .update("session", null)
                    .addOnSuccessListener( v -> {
                        Snackbar.make(view, "Sesión cerrada", Snackbar.LENGTH_LONG).show();
                        MainActivity.usuario = null;
                        navController.navigate(R.id.nav_login);
                    })
                    .addOnFailureListener( v ->
                        Snackbar.make(view, "Error al cerrar sesión", Snackbar.LENGTH_LONG).show()
                    );
            //db.Sentencia("update Usuario set Session=NULL where Correo='" + usuario.getCorreo() + "'");
        } else {
            Snackbar.make(view, "No hay usuario autenticado", Snackbar.LENGTH_LONG).show();
        }
    }
}