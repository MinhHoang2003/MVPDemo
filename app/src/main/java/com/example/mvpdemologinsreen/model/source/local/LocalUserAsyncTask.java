package com.example.mvpdemologinsreen.model.source.local;

import android.os.AsyncTask;

import com.example.mvpdemologinsreen.model.source.OnDataLoadingCallback;

import java.sql.SQLException;

public class LocalUserAsyncTask<T> extends AsyncTask<Void, Void, T> {

    private GetDataHandler<T> mGetDataHandler;
    private Exception mException;
    private OnDataLoadingCallback<T> mOnDataLoadingCallback;

    public LocalUserAsyncTask(GetDataHandler<T> getDataHandler, OnDataLoadingCallback<T> callback) {
        mGetDataHandler = getDataHandler;
        mOnDataLoadingCallback = callback;
    }

    @Override
    protected T doInBackground(Void... voids) {
        T data = null;
        try {
            data = mGetDataHandler.getData();
        } catch (SQLException e) {
            mException = e;
        }
        return data;
    }

    @Override
    protected void onPostExecute(T t) {
        if (mException == null) {
            if (t != null) mOnDataLoadingCallback.onDataLoaded(t);
            else mOnDataLoadingCallback.onDataNotAvailable(null);
        }
        mOnDataLoadingCallback.onDataNotAvailable(mException);

    }
}
