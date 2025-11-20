package se.iths.asli.mongoDatabaseTest.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import se.iths.asli.mongoDatabaseTest.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesMovieDAO implements MovieDAO<Movie> {
    private final MongoCollection<Document> collection;

    public MoviesMovieDAO(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public void insert(String title, int year) {
        //samma implementation
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
    public void insert(Movie movie) {
        
    }

    @Override
    public List<Movie> findAll() {
        List<Document> movies = collection.find().into(new ArrayList<>());
        return movies.stream()
                .map(document -> Movie.fromDocument(document))
                .toList();
    }

    @Override
    public Movie findByTitle(String title) {
        Bson filter = Filters.eq("title", title);
        Document document = collection.find(filter).first();
        return Movie.fromDocument(document);
    }
}
