package com.example.uppersky_movil.test_rokk3r.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.uppersky_movil.test_rokk3r.R;
import com.example.uppersky_movil.test_rokk3r.interfaces.OnBuyListener;
import com.example.uppersky_movil.test_rokk3r.data.realm.models.Product;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by Dorian on 26/08/2016.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private RealmResults<Product> products;
    private OnBuyListener mListener;
    private Context context;

    public ProductAdapter(Context context,RealmResults<Product> products, OnBuyListener listener) {
        this.products = products;
        this.context = context;
        mListener = listener;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.btnBuy)
        Button btnBuy;
        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvPrice)
        TextView tvPrice;
        @Bind(R.id.tvAmount)
        TextView tvAmount;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(products.get(position).getName());
        holder.tvPrice.setText(context.getResources().getString(R.string.format_price,
                products.get(position).getPrice() ) );
        holder.tvAmount.setText(context.getResources().getString(R.string.format_avaiable,
                products.get(position).getStockamount() ) );

        holder.btnBuy.setTag(position);
        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onClickBuy((Integer) view.getTag());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setUpdateList(RealmResults<Product> updateList) {
        this.products = updateList;
    }
}
