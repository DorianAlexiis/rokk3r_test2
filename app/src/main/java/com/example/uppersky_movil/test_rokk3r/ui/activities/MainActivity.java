package com.example.uppersky_movil.test_rokk3r.ui.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uppersky_movil.test_rokk3r.R;
import com.example.uppersky_movil.test_rokk3r.data.realm.controller.ProductManager;
import com.example.uppersky_movil.test_rokk3r.interfaces.MainActivityInterface;
import com.example.uppersky_movil.test_rokk3r.interfaces.OnBuyListener;
import com.example.uppersky_movil.test_rokk3r.data.realm.models.Product;
import com.example.uppersky_movil.test_rokk3r.ui.adapters.ProductAdapter;
import com.example.uppersky_movil.test_rokk3r.ui.dialogues.CarDialogue;
import com.example.uppersky_movil.test_rokk3r.utils.Seed;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;

/**
 * Created by Dorian on 26/08/2016.
 */

public class MainActivity extends AppCompatActivity implements OnBuyListener,
                                                                MainActivityInterface{

    private AsynTaskSeed asynTaskSeed;
    private CarDialogue carDialogue;
    private RealmResults<Product> products;
    private RealmResults<Product> car;
    private ProductManager productManager;
    private ProductAdapter mAdapter;


    @Bind(R.id.rvProducts)
    RecyclerView rvProducts;
    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;
    @Bind(R.id.tvCar)
    TextView tvCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        productManager = new ProductManager();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        car = productManager.getAllInCar();
        tvCar.setText(String.valueOf(car.size()));

        if(productManager.isEmpty()){
            asynTaskSeed = new AsynTaskSeed();
            asynTaskSeed.execute();
        }else{
            products = productManager.getInStock();
            newAdapter();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(asynTaskSeed != null){
            asynTaskSeed.cancel(true);
        }
    }

    private void newAdapter() {
        if(rvProducts == null){
            return;
        }
        pbLoading.setVisibility(View.GONE);
        mAdapter = new ProductAdapter(MainActivity.this, products,this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvProducts.setLayoutManager(layoutManager);
        rvProducts.setAdapter(mAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(carDialogue != null){
            carDialogue.dismiss();
        }
    }

    //LISTENERS

    @Override
    public void onClickBuy(int position) {
        if(products.get(position).getStockamount() > 0 ){
            productManager.remoteItemProduct(products.get(position));
        }

        productManager.addItemStock(products.get(position).getId(), true);

        car = productManager.getAllInCar();
        tvCar.setText(String.valueOf(car.size()));
        products = productManager.getInStock();
        newAdapter();
    }

    @OnClick({R.id.ivCar,R.id.tvCar})
    public void showCar(View v){
        Bundle bundle = new Bundle();
        carDialogue
                = CarDialogue.newInstance(bundle);
        carDialogue.show(getSupportFragmentManager(), CarDialogue.TAG);

    }

    @Override
    public void changeCar() {
        if(tvCar != null){
            car = productManager.getAllInCar();
            tvCar.setText(String.valueOf(car.size()));
        }
        products = productManager.getInStock();
        newAdapter();
    }

    /*
    *
    *
    * */
    private class AsynTaskSeed extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected  Boolean doInBackground(Void... params) {
            try{
                Seed.generate();
            }catch (Exception e){
                return false;
            }
           return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            pbLoading.setVisibility(View.GONE);
            if(success){
                products = productManager.getInStock();
                newAdapter();
            }else{
                Toast.makeText(MainActivity.this, R.string.error_seed, Toast.LENGTH_SHORT).show();
            }
        }
    }


}
