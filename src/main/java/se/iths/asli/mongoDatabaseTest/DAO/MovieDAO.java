package se.iths.asli.mongoDatabaseTest.DAO;

import java.util.List;

public interface MovieDAO<T> {
    void insert(String title, int year);

    void insert(T movie);

    List<T> findAll();

    T findByTitle(String title);
}
