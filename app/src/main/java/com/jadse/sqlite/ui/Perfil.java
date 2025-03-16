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
import com.jadse.sqlite.databinding.FragmentPerfilBinding;
import com.jadse.sqlite.model.Usuario;

public class Perfil extends Fragment {
    FragmentPerfilBinding binding;
    View view;
    Context context;
    NavController navController;

    Usuario usuario;
    UsuarioDao usuarioDao;
    FirebaseFirestore firestore;

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);
        usuarioDao = new UsuarioDao( context );
        firestore = FirebaseFirestore.getInstance();

        binding.btnCambiar.setOnClickListener(v -> {
            String nPasswordd = binding.edtPassword1.getText().toString().trim();
            String cnPasswordd = binding.edtPassword2.getText().toString().trim();
            String usuarioId = MainActivity.usuario.getId(); //Id del documento seteado en la clase en Login.java

            if (nPasswordd.isEmpty() || cnPasswordd.isEmpty()){ Snackbar.make(view, "Por favor llena los campos.", Snackbar.LENGTH_LONG).show(); return; }
            if (!nPasswordd.equals(cnPasswordd)) { Snackbar.make(view, "Las contraseñas no coinciden.", Snackbar.LENGTH_LONG).show(); return; }
            if (MainActivity.usuario == null) { Snackbar.make(view, "Usuario no identificado.", Snackbar.LENGTH_LONG).show(); return; }

            firestore.collection("usuarios")
                    .document(usuarioId)
                    .update("passwordd", nPasswordd)
                    .addOnSuccessListener(r -> {
                        Snackbar.make(view, "Contraseña actualizada.", Snackbar.LENGTH_LONG).show();
                        navController.navigateUp();
                    })
                    .addOnFailureListener(e ->
                        Snackbar.make(view, "Error al actualizar contraseña.", Snackbar.LENGTH_LONG).show()
                    );
        });
    }
}