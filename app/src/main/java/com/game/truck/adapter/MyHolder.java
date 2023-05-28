package com.game.truck.adapter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class MyHolder<VB extends ViewBinding> extends RecyclerView.ViewHolder {
    private VB vb;
    public MyHolder(VB vb) {
        super(vb.getRoot());
        this.vb = vb;
    }

    public VB getVb() {
        return vb;
    }
}
