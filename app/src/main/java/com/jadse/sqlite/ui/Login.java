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

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jadse.sqlite.MainActivity;
import com.jadse.sqlite.R;
import com.jadse.sqlite.controller.UsuarioDao;
import com.jadse.sqlite.databinding.FragmentLoginBinding;
import com.jadse.sqlite.model.Usuario;
import com.jadse.sqlite.model.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class Login extends Fragment {
    FragmentLoginBinding binding;
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
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);
        usuarioDao = new UsuarioDao( context );
        firestore = FirebaseFirestore.getInstance();

        binding.btnSignUp.setOnClickListener(v -> navController.navigate(R.id.nav_register));

        binding.edtCorreo.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {}
        });

        binding.btnIniciar.setOnClickListener(v -> btnIniciar_OnClick());

        /*if (MainActivity.usuario.getSession() != null)
            navController.navigate(R.id.nav_home);*/
    }

    private void btnIniciar_OnClick() {
        String sCorreo = binding.edtCorreo.getText().toString().trim();
        String sPasswordd = binding.edtPassword.getText().toString().trim();

        /*if (sCorreo.isEmpty()) binding.tilCorreo.setError("Ingrese su correo");
        else if (sPasswordd.isEmpty()) binding.tilPasswordd.setError("Ingrese una contraseña");*/

        if (sCorreo.isEmpty() || sPasswordd.isEmpty()) {
            Snackbar.make(view, "Usuario y/o passwordd inválidos", Snackbar.LENGTH_LONG).show();
        }

        mLogin();

        /*firestore.collection("usuarios")
                .whereEqualTo("correo", sCorreo)
                .whereEqualTo("passwordd", sPasswordd)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if ( queryDocumentSnapshots.isEmpty() )
                        new AlertDialog.Builder(context)
                                .setTitle("Iniciar sesión")
                                .setMessage("Correo y/o contraseña inválidos")
                                .setCancelable(false)
                                .setPositiveButton("Aceptar", (dialog, which) -> { } )
                                .show();
                    else {
                        DocumentSnapshot snapshot = queryDocumentSnapshots.getDocuments().get(0);
                        String usuarioId = snapshot.getId();

                        //usuario = new Usuario();
                        MainActivity.usuario = new Usuario();
                        MainActivity.usuario.setId(usuarioId);
                        MainActivity.usuario.setNombres(snapshot.getString("nombres"));
                        MainActivity.usuario.setApellidos(snapshot.getString("apellidos"));
                        MainActivity.usuario.setTelefono(snapshot.getString("telefono"));
                        MainActivity.usuario.setCorreo(snapshot.getString("correo"));
                        MainActivity.usuario.setSession("true");

                        firestore.collection("usuarios")
                                .document( snapshot.getId() ) //su id
                                .update("session", "true")
                                .addOnSuccessListener(v -> {
                                    navController.navigate(R.id.nav_home);
                                })
                                .addOnFailureListener(v -> {
                                    Snackbar.make(view, "Error al actualizar session", Snackbar.LENGTH_LONG).show();
                                });
                    } } )
                .addOnFailureListener( e ->
                        new AlertDialog.Builder( context )
                                .setTitle("No se pudo iniciar sesión")
                                .setMessage( "No se pudo comprobar credenciales, reintentar")
                                .setCancelable(false)
                                .setPositiveButton( "Aceptar", (dialog, which) -> { } )
                                .show() );*/
        // SQLITE
        //  usuarioDao.Login(sCorreo, sPasswordd);
        /*  new AlertDialog.Builder(context)
                .setTitle("Iniciar sesión")
                .setMessage("Usuario iniciado con éxito")
                .setPositiveButton("Aceptar", (dialog, which) -> navController.navigate(R.id.nav_home))
                .show();*/
    }

    private void mLogin() {
        String sDni = binding.edtCorreo.getText().toString().trim();
        String sPasswordd = binding.edtPassword.getText().toString().trim();

        String URL = MainActivity.URL_API + "login";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, null, response -> {
            int i = 0;
        }, error -> {

        } ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("dni", sDni);
                params.put("passwordd", sPasswordd);
                return params;
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }
}