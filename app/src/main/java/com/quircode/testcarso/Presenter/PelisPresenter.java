package com.quircode.testcarso.Presenter;

import com.quircode.testcarso.Contract.MovieListContract;
import com.quircode.testcarso.Model.Pelis;
import com.quircode.testcarso.Service.PelisListModel;

import java.util.List;

public class PelisPresenter implements MovieListContract.Presenter, MovieListContract.Model.OnFinishListener {

    private MovieListContract.View pelisListView;
    private MovieListContract.Model pelisListModel;

    public PelisPresenter(MovieListContract.View pelisListView) {
        this.pelisListView = pelisListView;
        pelisListModel = new PelisListModel();
    }

    @Override
    public void onDestroy() {
        this.pelisListView = null;
    }

    @Override
    public void getMoreData(int PageNo) {
        if (pelisListView!=null){
            pelisListView.showProgressBar();
        }
        pelisListModel.getPelisList(this,1);
    }

    @Override
    public void requestFromServer() {
        if (pelisListView!=null){
            pelisListView.showProgressBar();
        }
        pelisListModel.getPelisList(this,1);
    }

    @Override
    public void onFinish(List<Pelis> pelisArrayList) {
        pelisListView.setDataToRecyclerView(pelisArrayList);
        if (pelisListView!=null){
            pelisListView.hideProgressBar();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        pelisListView.onResponseFailure(t);
        if (pelisListView!=null){
            pelisListView.hideProgressBar();
        }
    }
}
