package dao;

import entity.AbstractEntity;
import exception.DataNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractDao<T extends AbstractEntity> implements Dao<T> {
    private List<T> dataCollection = new ArrayList<>();
    String fileName;

    public AbstractDao(String fileName) {
        this.fileName = fileName;
        try {
            this.loadDataFromFile();
        } catch (DataNotFoundException e) {
            System.out.println("Данных нет.");
        }
    }

    @Override
    public List<T> getAll() {
        return Collections.unmodifiableList(this.dataCollection);
    }

    @Override
    public boolean delete(T t) {
        return this.dataCollection.remove(t);
    }

    @Override
    public T save(T t) {
        if (this.dataCollection.contains(t)) {
            int indexOfElement = this.dataCollection.indexOf(t);
            this.dataCollection.set(indexOfElement, t);
        } else {
            this.dataCollection.add(t);
        }
        return t;
    }

    @Override
    public boolean saveDataToFile() {
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(fileName))) {
            this.dataCollection.forEach((u) -> {
                try {
                    ous.writeObject(u);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void loadDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    this.save((T) ois.readObject());
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new DataNotFoundException();
        }
    }
}
