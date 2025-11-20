package se.iths.asli.mongoDatabaseTest.DAO;

import org.bson.Document;

import java.util.List;

public interface MovieDAO {
    void insert(String title, int year);

    List<Document> findAll();

    Document findByTitle(String title);
}
