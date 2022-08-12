package org.o7planning.hofftest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import org.o7planning.hofftest.R;
import org.o7planning.hofftest.contract.ListContract;
import org.o7planning.hofftest.model.Models;
import org.o7planning.hofftest.presenter.ListPresenter;
import org.o7planning.hofftest.view.ConstructorListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity implements ListContract.View{

    private ListPresenter listPresenter;
    private RecyclerView rvCatalog;
    private ArrayList<Models> constructorList;

    ImageView backLayout;
    Intent intentBackLayout;

    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        rvCatalog = findViewById(R.id.rvAdapter);

        layoutManager = new GridLayoutManager(this, 2);
        rvCatalog.setLayoutManager(layoutManager);
        rvCatalog.setHasFixedSize(true);

        constructorList = new ArrayList<Models>();

        listPresenter = new ListPresenter(this);
        listPresenter.requestDataFromServer();
    }

    @Override
    public void setDataToRecycleView(List<Models> catalogArrayList) {
        constructorList.addAll(catalogArrayList);
        ConstructorListAdapter modelListAdapter = new ConstructorListAdapter(constructorList, CatalogActivity.this);
        rvCatalog.setAdapter(modelListAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
    }
}