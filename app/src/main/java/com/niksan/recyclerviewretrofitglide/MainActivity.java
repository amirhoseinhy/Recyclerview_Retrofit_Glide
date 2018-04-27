package com.niksan.recyclerviewretrofitglide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<Product> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        list = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.200")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface request = retrofit.create(ApiInterface.class);
        Call<List<Product>> call = request.getProductsDetails();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products = response.body();
                Product product = null;
                for(int i = 0 ; i<products.size(); i++){
                    product = new Product();

                    String name = products.get(i).getProductname();
                    String color = products.get(i).getColor();
                    String image = products.get(i).getImageurl();
                    String price = products.get(i).getPrice();

                    product.setProductname(name);
                    product.setColor(color);
                    product.setImageurl(image);
                    product.setPrice(price);
                    list.add(product);
                }
                recyclerAdapter = new RecyclerAdapter(list, MainActivity.this);
                LinearLayoutManager gridLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                //GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });


    }
}
