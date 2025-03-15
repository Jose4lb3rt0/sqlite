package com.jadse.sqlite.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jadse.sqlite.R;
import com.jadse.sqlite.controller.UsuarioDao;
import com.jadse.sqlite.databinding.FragmentRegisterBinding;
import com.jadse.sqlite.model.Usuario;

public class Register extends Fragment {
    FragmentRegisterBinding binding;
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
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);

        navController = Navigation.findNavController(view);
        usuarioDao = new UsuarioDao( context );

        binding.btnSignIn.setOnClickListener(v -> navController.navigate(R.id.nav_login));
        binding.btnRegistrar.setOnClickListener(v -> btnRegistrar_OnClick());
    }

    private void btnRegistrar_OnClick() {
        String sNombres = binding.edtNombres.getText().toString().trim();
        String sApellidos = binding.edtApellidos.getText().toString().trim();
        String sTelefono = binding.edtTelefono.getText().toString().trim();
        String sCorreo = binding.edtCorreo.getText().toString().trim();
        String sPasswordd = binding.edtPassword.getText().toString().trim();

        Usuario usuario = new Usuario();
        usuario.setNombres(sNombres);
        usuario.setApellidos(sApellidos);
        usuario.setTelefono(sTelefono);
        usuario.setCorreo(sCorreo);
        usuario.setPasswordd(sPasswordd);

        //if (sNombres.isEmpty()) binding.tilNombres.setError("Ingrese sus nombres");
        UsuarioDao usuarioDao= new UsuarioDao(context);
        usuarioDao.Guardar( usuario );

        new AlertDialog.Builder(context)
                .setTitle("Registrar usuario")
                .setMessage("Usuario regisrado con éxito")
                .setPositiveButton("Aceptar", (dialog, which) -> navController.navigate(R.id.nav_login))
                .show();
    }
}