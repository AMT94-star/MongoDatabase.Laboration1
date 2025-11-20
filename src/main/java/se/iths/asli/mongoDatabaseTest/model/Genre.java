package se.iths.asli.mongoDatabaseTest.model;

import org.bson.Document;

public class Genre {
    private String name;
    private String description;

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Genre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Genre{" + "name=" + name + ", description=" + description + '}';
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("name", name);
        document.append("description", description);
        return document;
    }

    public static Genre fromDocument(Document document) {
        Genre genre = new Genre();
        genre.setName(document.getString("description"));
        genre.setDescription(document.getString("name"));
        return genre;
    }
}
