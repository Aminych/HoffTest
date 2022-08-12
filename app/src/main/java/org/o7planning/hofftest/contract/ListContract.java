package org.o7planning.hofftest.contract;

import org.o7planning.hofftest.model.Models;

import java.util.ArrayList;
import java.util.List;


public interface ListContract {

    interface Model {

        interface onFinishedCall {

            void onResponse(ArrayList<Models> catalogArrayList);

            void onFailure(Throwable t);
        }

        void getConstructorList(onFinishedCall onFinishedCall, int pageNo);

    }

    interface View {

        void setDataToRecycleView(List<Models> catalogArrayList);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {

        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();
    }
}
