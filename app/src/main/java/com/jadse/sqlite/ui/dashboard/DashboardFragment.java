package com.jadse.sqlite.ui.dashboard;

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

import com.jadse.sqlite.controller.DemoController;
import com.jadse.sqlite.databinding.FragmentDashboardBinding;
import com.jadse.sqlite.databinding.FragmentHomeBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    Context context;
    NavController navController;
    View view;

    DemoController controller;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);
        controller = new DemoController(context);

//        binding.btnIniciar
    }
}