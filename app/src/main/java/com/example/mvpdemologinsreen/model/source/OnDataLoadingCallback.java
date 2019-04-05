package com.example.mvpdemologinsreen.model.source;

public interface OnDataLoadingCallback<T> {

    void onDataLoaded(T data);

    void onDataNotAvailable(Exception e);
}
