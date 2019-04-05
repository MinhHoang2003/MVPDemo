package com.example.mvpdemologinsreen.model.source.local;

import java.sql.SQLException;

public interface GetDataHandler<T> {
    T getData() throws SQLException;
}
