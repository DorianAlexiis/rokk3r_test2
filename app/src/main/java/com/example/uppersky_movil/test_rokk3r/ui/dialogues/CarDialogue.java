package com.example.uppersky_movil.test_rokk3r.ui.dialogues;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.uppersky_movil.test_rokk3r.R;
import com.example.uppersky_movil.test_rokk3r.data.realm.controller.ProductManager;
import com.example.uppersky_movil.test_rokk3r.ui.adapters.CarAdapter;
import com.example.uppersky_movil.test_rokk3r.interfaces.MainActivityInterface;
import com.example.uppersky_movil.test_rokk3r.interfaces.OnDeleteListener;
import com.example.uppersky_movil.test_rokk3r.data.realm.models.Product;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by Dorian on 26/08/2016.
 */
public class CarDialogue extends DialogFragment implements OnDeleteListener {
    public static final String TAG = CarDialogue.class.getSimpleName();

    private MainActivityInterface mListener;
    private RealmResults<Product> car;
    private CarAdapter mAdapter;
    private ProductManager productManager;

    @Bind(R.id.rvCar)
    RecyclerView rvCar;
    @Bind(R.id.tvEmpty)
    TextView tvEmpty;

    public static CarDialogue newInstance(Bundle args) {
        CarDialogue fragment = new CarDialogue();
        fragment.setArguments(args);
        return fragment;
    }

    public CarDialogue() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productManager = new ProductManager();

        car = productManager.getAllInCar();
        //products = Car.getProducts();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialogo = new Dialog(getActivity());
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialogo.setContentView(R.layout.dialogue_car);
        ButterKnife.bind(this, dialogo);

        newAdapter();

        return dialogo;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (MainActivityInterface) activity;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }


    //-------------METODOS PROPIOS-------------------------------------------
    private void newAdapter() {
        mAdapter = new CarAdapter(car, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCar.setLayoutManager(layoutManager);
        rvCar.setAdapter(mAdapter);

        if (car.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            tvEmpty.setVisibility(View.GONE);
        }
    }
    //-------------LISTENERS-----------------------------------------------------


    @Override
    public void OnDeleteItem(int position) {
        productManager.remoteItemProduct(car.get(position));

        productManager.addItemStock(car.get(position).getId(), false);

        car = productManager.getAllInCar();

        if (car.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            tvEmpty.setVisibility(View.GONE);
        }

        mAdapter.notifyDataSetChanged();
        if (mListener != null) {
            mListener.changeCar();
        }
    }

}
