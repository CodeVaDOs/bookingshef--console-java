package dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    boolean delete(T t);

    T save(T t);

    boolean saveDataToFile();

    void loadDataFromFile();
}
