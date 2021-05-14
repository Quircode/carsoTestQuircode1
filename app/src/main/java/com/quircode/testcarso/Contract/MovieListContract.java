package com.quircode.testcarso.Contract;

import com.quircode.testcarso.Model.Pelis;

import java.util.List;

public interface MovieListContract {
    public interface Model {
        interface OnFinishListener {
            void onFinish(List<Pelis> pelisArrayList);

            void onFailure(Throwable t);
        }

        void getPelisList(OnFinishListener onFinishListener, int pageNo);

    }
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void setDataToRecyclerView(List<Pelis> movieListArray);
        void onResponseFailure(Throwable t);
    }

    public interface Presenter {
        void onDestroy();
        void getMoreData(int PageNo);
        void requestFromServer();
    }

}
