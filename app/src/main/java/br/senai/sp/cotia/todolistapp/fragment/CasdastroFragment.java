package br.senai.sp.cotia.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;


import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCasdastroBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;



public class CasdastroFragment extends Fragment {

    private FragmentCasdastroBinding binding;

    // variavel para o DatePicker
    DatePickerDialog datePicker;

    Calendar dataAtual;
    int year, month, day;
    String dataEscolhida = "";

    AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // instanciar a database
        database = AppDatabase.getDatabase(getActivity());

        binding = FragmentCasdastroBinding.inflate(inflater, container, false);

        dataAtual = Calendar.getInstance();

        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);

        // instanciar o datePicker
        datePicker = new DatePickerDialog(getContext(), (view, ano, mes, dia) -> {
            // cai aqui toda vez que clica no OK do DatePicker
            // passa para as variaveus globais

            year = ano;
            month = mes;
            day = dia;

            //formata a String da daraEscolhida
            dataEscolhida = String.format("%02d/%02d/%04d", day, month + 1, year);

            // jogar a String botao
            binding.botaoData.setText(dataEscolhida);


        },year, month, day);

       //  listener do botao de data
        binding.botaoData.setOnClickListener(v -> {

           datePicker.show();
        });

        // listener do botao salvar
        binding.botaoSalvar.setOnClickListener(v -> {


            // validar os campos
            if(binding.view1.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Campo título vazio", Toast.LENGTH_SHORT).show();

            }else if(binding.view2.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Campo descrição vazio", Toast.LENGTH_SHORT).show();

            }else if(dataEscolhida.isEmpty()){
                Toast.makeText(getContext(), "Campo data vazio", Toast.LENGTH_SHORT).show();

            }else{
                // criar um objeto tarefa
                Tarefa tarefa = new Tarefa();



                // popular a tarefa
                tarefa.setTitulo(binding.view1.getText().toString());
                tarefa.setDescricao(binding.view2.getText().toString());

                // cria um Calendar e popula com a data que foi selecionada
                Calendar dataRealizacao = Calendar.getInstance();
                dataRealizacao.set(year, month, day);

                // passar para a tarefa os milissegundos da data
                tarefa.setDataPrevista(dataRealizacao.getTimeInMillis());

                // criar um Calendar para a data atual
                Calendar dataAtual = Calendar.getInstance();
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());

                // salvar a tarefa n0 BD
                 new InsertTarefa().execute(tarefa);

            }


        });

        return binding.getRoot();
    }

    // classe para a task de iserir tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String > {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.w("PASSOU", "no OnPreExecute");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.w("PASSOU", "no OnProgressUpdate");
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            Log.w("PASSOU", "no doInBackground");
            // extrair a Tarefa do vetor
            Tarefa t = tarefas[0];
            try{
                database.getTarefaDao().insert(t);
                return "OK, tudo certo!";
            }catch (Exception e){
                e.printStackTrace();
                // retorna a mensagem de erro caso tenha dado erro
                return e.getMessage();

            }

        }

        @Override
        protected void onPostExecute(String resposta) {
            if(resposta.equals("OK, tudo certo!")){
                Log.w("RESULTADO", "OK TUDO CERTO");
                getActivity().onBackPressed();

            }else{
                Log.w("RESULTADO", resposta);
                Toast.makeText(getContext(),"DEU RUIM RAPAZ"+resposta, Toast.LENGTH_SHORT).show();
            }

        }
    }


}