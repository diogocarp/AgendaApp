package br.senai.sp.cotia.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.todolistapp.databinding.FragmentVisuBinding;


public class PrincipalFragment extends Fragment {

    private FragmentPrincipalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPrincipalBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}