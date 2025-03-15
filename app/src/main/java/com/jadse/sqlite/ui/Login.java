package com.jadse.sqlite.ui;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.jadse.sqlite.MainActivity;
import com.jadse.sqlite.R;
import com.jadse.sqlite.controller.UsuarioDao;
import com.jadse.sqlite.databinding.FragmentLoginBinding;
import com.jadse.sqlite.model.Usuario;

public class Login extends Fragment {
    FragmentLoginBinding binding;
    View view;
    Context context;
    NavController navController;

    Usuario usuario;
    UsuarioDao usuarioDao;

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);
        usuarioDao = new UsuarioDao( context );


        binding.btnSignUp.setOnClickListener(v -> navController.navigate(R.id.nav_register));

        binding.edtCorreo.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {}
        });

        binding.btnIniciar.setOnClickListener(v -> btnIniciar_OnClick());

       /* if (MainActivity.usuario.getSession() != null)
            navController.navigate(R.id.nav_home);*/
    }

    private void btnIniciar_OnClick() {
        String sCorreo = binding.edtCorreo.getText().toString().trim();
        String sPasswordd = binding.edtPassword.getText().toString().trim();
        String sMensaje = "Usuario y/o passwordd inválidos";

        if (sCorreo.isEmpty() || sPasswordd.isEmpty()) {
            Snackbar.make(view, sMensaje, Snackbar.LENGTH_LONG).show();
        }

        usuarioDao.Login(sCorreo, sPasswordd);

        new AlertDialog.Builder(context)
                .setTitle("Iniciar sesión")
                .setMessage("Usuario iniciado con éxito")
                .setPositiveButton("Aceptar", (dialog, which) -> navController.navigate(R.id.nav_home))
                .show();
    }
}