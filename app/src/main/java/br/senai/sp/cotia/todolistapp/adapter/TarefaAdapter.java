package br.senai.sp.cotia.todolistapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {
    // lista de tarefas
    private List<Tarefa> tarefas;

    // variavel para o Context
    private Context context;

    // variavel do tipo OnTaefaClickListener

    private OnTarefaClickListener listenerClickTarefa;


    // comstrutor pra receber os valores
    public TarefaAdapter(List<Tarefa> lista, Context contexto, OnTarefaClickListener listener){
        this.tarefas = lista;
        this.context = contexto;
        this.listenerClickTarefa = listener;

    }


    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // infla o layout do adapter
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefas, parent, false);

        // retorna uma nova view holder com a view
        return new TarefaViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {

        // obtem a tarefa pela position
        Tarefa t = tarefas.get(position);

        // exibe o titulo da tarefa na textview
        holder.tvTitulo.setText(t.getTitulo());

        //se estiver concluida
        if(t.isConcluida()){
            holder.tvStatus.setText(R.string.concluida);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.green));


        }else{
            holder.tvStatus.setText(R.string.aberta);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.yellow));

            if(Calendar.getInstance().getTimeInMillis() > t.getDataPrevista()){
                holder.tvStatus.setText(R.string.finalizada);
                holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.red));

            }

        }

        // formata a data de long para string
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(formatador.format(t.getDataPrevista()));

        holder.itemView.setOnClickListener(v -> {

            // dispara o listener
            listenerClickTarefa.onClick(v, t);

        });


    }

    @Override

    // retorna a quantidade de elementos a serem exibidos
    public int getItemCount() {
        if(tarefas != null) {
            return tarefas.size();
        }
        return 0;
    }

    // classe ViewHolder para mapear os componentes do xml
    class TarefaViewHolder extends RecyclerView.ViewHolder{

        // variaveis para acessar os componemtes do xml
        TextView tvTitulo,tvData, tvStatus;



        public TarefaViewHolder(View view){

            // chama o construtor da superclasse
            super(view);

            // passar para as variaveis, os componemtes do XML
            tvTitulo = view.findViewById(R.id.item_titulo);
            tvData = view.findViewById(R.id.item_data);
            tvStatus = view.findViewById(R.id.item_status);


        }

    }

    // interface para o clique na tarefa
    public interface OnTarefaClickListener{
        void onClick(View view, Tarefa tarefa);



    }

}
