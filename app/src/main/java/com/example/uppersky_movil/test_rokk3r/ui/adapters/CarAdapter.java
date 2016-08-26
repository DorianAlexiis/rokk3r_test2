package com.example.uppersky_movil.test_rokk3r.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uppersky_movil.test_rokk3r.R;
import com.example.uppersky_movil.test_rokk3r.interfaces.OnDeleteListener;
import com.example.uppersky_movil.test_rokk3r.data.realm.models.Product;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by Dorian on 26/08/2016.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private RealmResults<Product> products ;
    private OnDeleteListener mListener;

    public CarAdapter(RealmResults<Product>  products, OnDeleteListener listener) {
        this.products = products;
        mListener = listener;
    }

    public void setList(RealmResults<Product>  list) {
        this.products = list;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvAmount)
        TextView tvAmount;
        @Bind(R.id.ivDelete)
        ImageView ivDelete;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(products.get(position).getName());
        holder.tvAmount.setText(String.valueOf(products.get(position).getStockamount()));
        holder.ivDelete.setTag(position);
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.OnDeleteItem((Integer) view.getTag());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
