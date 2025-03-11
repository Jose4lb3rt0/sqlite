package com.jadse.sqlite.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.jadse.sqlite.controller.DemoController;
import com.jadse.sqlite.databinding.FragmentHomeBinding;
import com.jadse.sqlite.model.Usuario;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Context context;
    View view;
    NavController navController;

    DemoController controller;
    Usuario usuario;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return  view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);
        controller = new DemoController(context);

        binding.btnIniciar.setOnClickListener(v -> btnIniciar_OnClick());
    }

    private void btnIniciar_OnClick() {
        String sDni = binding.edtDni.getText().toString();
        String sPassword = binding.edtPassword.getText().toString();

        usuario = new Usuario();
        usuario.setDni(sDni);
        usuario.setPasswordd(sPassword);
        String sMensaje = "Usuario y/o password inválido";
        if (controller.Login(usuario)) sMensaje = "Hola " + usuario.getNombres();

        Snackbar.make(view, sMensaje, Snackbar.LENGTH_SHORT).show();
    }
}