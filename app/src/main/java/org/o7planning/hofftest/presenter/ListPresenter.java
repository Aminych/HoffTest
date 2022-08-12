package org.o7planning.hofftest.presenter;

import org.o7planning.hofftest.activity.CatalogActivity;
import org.o7planning.hofftest.contract.ListContract;
import org.o7planning.hofftest.model.Models;
import org.o7planning.hofftest.service.ListModel;

import java.util.ArrayList;

public class ListPresenter implements ListContract.Presenter, ListContract.Model.onFinishedCall {

    private ListContract.View catalogListView;
    private ListContract.Model catalogListModel;

    public ListPresenter(CatalogActivity catalogListView) {
        this.catalogListView = (ListContract.View) catalogListView;
        catalogListModel = new ListModel();
    }

    @Override
    public void onDestroy() {
        this.catalogListView = null;
    }

    @Override
    public void getMoreData(int pageNo) {
        catalogListModel.getConstructorList(this, pageNo);
    }

    @Override
    public void requestDataFromServer() {
        catalogListModel.getConstructorList(this, 1);
    }

    @Override
    public void onResponse(ArrayList<Models> catalogArrayList) {
        catalogListView.setDataToRecycleView(catalogArrayList);
    }

    @Override
    public void onFailure(Throwable t) {
        catalogListView.onResponseFailure(t);
    }
}