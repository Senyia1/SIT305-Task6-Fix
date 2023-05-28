package com.game.truck.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class MyAdapter<VB extends ViewBinding, Data> extends RecyclerView.Adapter<MyHolder<VB>> {
    private List<Data> data = new ArrayList<>();

    public List<Data> getData() {
        return data;
    }

    @NonNull
    @Override
    public MyHolder<VB> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder<>(createHolder(parent));
    }

    public abstract VB createHolder(ViewGroup parent);

    @Override
    public void onBindViewHolder(@NonNull MyHolder<VB> holder, int position) {
        Data d = data.get(position);
        bind(holder.getVb(), d, position);
    }

    public abstract void bind(VB vb, Data data, int position);

    @Override
    public int getItemCount() {
        return data.size();
    }
}
