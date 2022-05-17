package br.senai.sp.cotia.todolistapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.adapter.TarefaAdapter;
import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.todolistapp.databinding.FragmentVisuBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


public class PrincipalFragment extends Fragment {

    private FragmentPrincipalBinding binding;

    // variavel para a data base
    private AppDatabase database;

    // variavel para Adaptar
    private TarefaAdapter adapter;

    // variavel para a lista
    private List<Tarefa> tarefas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPrincipalBinding.inflate(inflater, container, false);

        //clique no botão de adcionar tareda
        binding.btnAddTarefa.setOnClickListener(v -> {
            NavHostFragment.findNavController(PrincipalFragment.this).
                    navigate(R.id.action_principalFragment_to_casdastroFragment);


        });

        // instancia a database
        database = AppDatabase.getDatabase(getActivity());


        // defina o layout managerdo recycler view
        binding.recyclerTarefas.setLayoutManager(new LinearLayoutManager(getContext()));

        // executa a async task
        new ReadTarefa().execute();

        // retorna a view raiz do binding
        return binding.getRoot();
    }

    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>> {

        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            //guarda na variável tarefas, as tarefas do banco de dados
            tarefas = database.getTarefaDao().getAll();

            // retorna a lista de tarefas
            return tarefas;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {

            // instancia o adapter
            adapter = new TarefaAdapter(tarefas, getActivity(), listenerTarefa);

            // aplica a adaptar no recyclerview
            binding.recyclerTarefas.setAdapter(adapter);


        }
    }

    // implementação da interface OnTarefaClickListener
    private TarefaAdapter.OnTarefaClickListener listenerTarefa = (view, tarefa) ->{

        // variavel para transportar a tarefa (pacote)
        Bundle bundle = new Bundle();

        // "pendurar a tarefa no pacote"
        bundle.putSerializable("tarefa", tarefa);

        // navega para proxima fragment enviando o bundle
        NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_visuFragment, bundle);

    };
}