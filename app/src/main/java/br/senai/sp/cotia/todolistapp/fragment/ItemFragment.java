package br.senai.sp.cotia.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentItemBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


public class ItemFragment extends Fragment {

    private FragmentItemBinding binding;
    private Tarefa tarefa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItemBinding.inflate(inflater,container,false);
        if(getArguments() != null){
            //recupero a tarefa
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");
            binding.tituloSub.setText(tarefa.getTitulo());
        }
        return binding.getRoot();


    }


}