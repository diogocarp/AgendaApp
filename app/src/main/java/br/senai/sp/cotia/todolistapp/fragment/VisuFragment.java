package br.senai.sp.cotia.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentVisuBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


public class VisuFragment extends Fragment {
    // variavel para a tarefa a ser detalhada
    private Tarefa tarefa;



    private FragmentVisuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // instancia o binding
        binding = FragmentVisuBinding.inflate(inflater, container, false);

        // verifica se existe algo sendo passado no bundle
        if(getArguments() != null){

            // recupera a terefa
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");

            // popula os campos com infomações das tarefas
            binding.itemTitulo.setText(tarefa.getTitulo());
            binding.descricao.setText(tarefa.getDescricao());
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            binding.itemData.setText(formatador.format(tarefa.getDataPrevista()));
        }

        binding.btnAdicionarTarefa.setOnClickListener(v->{
            Bundle bundle = new Bundle();
            bundle.putSerializable("tarefa", tarefa);
            NavHostFragment.findNavController(VisuFragment.this).navigate(R.id.action_visuFragment_to_itemFragment,bundle);

        });

        // retorna a view raiz do binding
        return binding.getRoot();


    }


}