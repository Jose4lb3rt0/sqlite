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

import com.google.firebase.firestore.FirebaseFirestore;
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
    FirebaseFirestore firestore;

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
        firestore = FirebaseFirestore.getInstance();

        navController = Navigation.findNavController(view);
        usuarioDao = new UsuarioDao( context );

        binding.btnSignIn.setOnClickListener(v -> navController.navigate(R.id.nav_login));
        binding.btnRegistrar.setOnClickListener(v -> btnRegistrar_OnClick());

        /*binding.edtNombres.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { binding.tilNombres.setError(""); }
            @Override public void afterTextChanged(Editable s) { } });
        binding.edtApellidos.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { binding.tilApellidos.setError(""); }
            @Override public void afterTextChanged(Editable s) { } });
        binding.edtTelefono.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { binding.tilTelefono.setError(""); }
            @Override public void afterTextChanged(Editable s) { } });
        binding.edtCorreo.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { binding.tilCorreo.setError(""); }
            @Override public void afterTextChanged(Editable s) { } });
        binding.edtPasswordd.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { binding.tilPasswordd.setError(""); }
            @Override public void afterTextChanged(Editable s) { } });*/
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

        /*if ( sNombres.isEmpty() ) binding.tilNombres.setError( "Ingrese sus nombres");
        else if ( sApellidos.isEmpty() ) binding.tilApellidos.setError( "Ingrese sus apellidos");
        else if ( sTelefono.isEmpty() ) binding.tilTelefono.setError( "Ingrese un número de teléfono");
        else if ( sCorreo.isEmpty() ) binding.tilCorreo.setError( "Ingrese su correo");
        else if ( sPasswordd.isEmpty() ) binding.tilPasswordd.setError( "Ingrese una contraseña");
        else*/
        firestore.collection("usuarios")
                .add( usuario )
                .addOnSuccessListener( documentReference ->
                        new AlertDialog.Builder( context )
                                .setTitle("Registrar usuario")
                                .setMessage( "Usuario registrado con éxito")
                                .setPositiveButton( "Aceptar", (dialog, which) -> navController.navigateUp() )
                                .show() )
                .addOnFailureListener( e ->
                        new AlertDialog.Builder( context )
                                .setTitle("Registrar usuario")
                                .setMessage( "Usuario no pudo ser registrado, " + e.getMessage())
                                .setPositiveButton( "Aceptar", (dialog, which) -> { } )
                                .show() );
        /*UsuarioDao usuarioDao= new UsuarioDao(context);
        usuarioDao.Guardar( usuario );*/
    }
}