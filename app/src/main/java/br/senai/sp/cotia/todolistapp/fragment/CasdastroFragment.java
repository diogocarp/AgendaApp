package br.senai.sp.cotia.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCasdastroBinding;


public class CasdastroFragment extends Fragment {

    private FragmentCasdastroBinding binding;

    // variavel para o DatePicker
    DatePickerDialog datePicker;

    Calendar dataAtual;
    int year, month, day;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCasdastroBinding.inflate(inflater, container, false);

        dataAtual = Calendar.getInstance();

        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);

        // instanciar o datePicker
        datePicker = new DatePickerDialog(getContext(), (view, ano, mes, dia) -> {


        },year, month, day);

       //  listener do botao de data
        binding.botaoData.setOnClickListener(v -> {

           datePicker.show();
        });

        return binding.getRoot();
    }

}