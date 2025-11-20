package se.iths.asli.mongoDatabaseTest.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class MovieDAOMongo implements MovieDAO<Document> {
    private final MongoCollection<Document> collection;

    public MovieDAOMongo(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public void insert(String title, int year) {
        Document existing = collection.find(Filters.eq("title", title)).first();
        if (existing != null) {
            System.out.println("Filmen " + title + " finns redan, det blir ingen insättning");
        } else {
            Document doc = new Document()
                    .append("title", title)
                    .append("year", year);
            collection.insertOne(doc);
            System.out.println("Film insatt " + title);
        }
    }

    @Override
    public void insert(Document movie) {

    }

    @Override
    public List<Document> findAll() {
        List<Document> movies = new ArrayList<>();
        for (Document doc : collection.find()) {
            movies.add(doc);
        }
        return movies;
    }

    @Override
    public Document findByTitle(String title) {
        return collection.find(Filters.eq("title", title)).first();
    }

    public void update(String title, int year) {
        Bson filterTitle = Filters.eq("title", title);
        Bson updateYear = Updates.set("year", year);
        UpdateResult result = collection.updateOne(filterTitle, updateYear);
        if (result.wasAcknowledged()) {
            System.out.println("Antal uppdaterade filmer: " + result.getModifiedCount());
        } else {
            System.out.println("Uppdatering bekräftades inte av servern");
        }
    }

    public void delete(String title) {
        Bson filterTitle = Filters.eq("title", title);
        DeleteResult result = collection.deleteOne(filterTitle);
        if (result.wasAcknowledged()) {
            System.out.println("Antal raderade dokument: " + result.getDeletedCount());
        } else {
            System.out.println("Radering bekräftades ej av servern");
        }
    }

    public void deleteMultiple(int year) {
        Bson filterYear = Filters.eq("year", year);
        DeleteResult result = collection.deleteMany(filterYear);
        if (result.wasAcknowledged()) {
            System.out.println("Antal raderade dokument: " + result.getDeletedCount());
        } else {
            System.out.println("Radering bekräftades ej av servern");
        }
    }
}
