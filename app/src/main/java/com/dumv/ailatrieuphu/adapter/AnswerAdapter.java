package com.dumv.ailatrieuphu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dumv.ailatrieuphu.R;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ItemHolder>{
    List<String> list;
    Context context;
    AnswerAdapter.OnItemClick clickListener;

    public AnswerAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setClickListener(OnItemClick clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.line_answer,null);
        AnswerAdapter.ItemHolder itemHolder=new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        String answer=list.get(position);
        holder.txvAnswer.setText(answer);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface OnItemClick {
        void onItemClick(int position,TextView view);
    }
    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView txvAnswer;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            txvAnswer=itemView.findViewById(R.id.txvAnswer);
            txvAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(getAdapterPosition(),txvAnswer);

                    }
                }
            });
        }
    }
}
