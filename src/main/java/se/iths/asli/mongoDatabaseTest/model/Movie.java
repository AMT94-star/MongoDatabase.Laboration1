package se.iths.asli.mongoDatabaseTest.model;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private ObjectId id;
    private String title;
    private int year;
    private List<Genre> genres;

    public Movie(String title, int year, List<Genre> genres) {
        this.title = title;
        this.year = year;
        this.genres = genres;
    }

    public Movie(ObjectId id, String title, int year, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Document toDocument() {
        List<Document> genreDocs = new ArrayList<>();
        if (this.genres != null) {
            //Streams API
            this.genres.stream()
                    .map(Genre::toDocument)
                    .toList();
        }
        Document doc = new Document()
                .append("title", this.title)
                .append("year", this.year)
                .append("genres", genreDocs);
        if (this.id != null) {
            doc.append("_id", this.id);
        }
        return doc;
    }
}
