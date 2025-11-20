package se.iths.asli.mongoDatabaseTest;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import se.iths.asli.mongoDatabaseTest.DAO.MovieDAOMongo;

public class Main {
    public static void main(String[] args) {
        String uri = "mongodb+srv://AMT94-star:CX1ZtpjP9VWKgxnM@clustercreationtest.wqamzf4.mongodb.net/?appName=ClusterCreationTest";

        try (MongoClient client = MongoClients.create(uri)) {
            MongoDatabase database = client.getDatabase("clustercreationtest");
            MongoCollection<Document> collection = database.getCollection("Movies");
            MovieDAOMongo movieDAO = new MovieDAOMongo(collection);

            movieDAO.insert("Pulp Fiction", 1994);
            movieDAO.insert("Super Mario Galaxy", 2026);
            movieDAO.insert("Harry Potter and the Sorcerer's Stone", 2001);
            movieDAO.insert("In Your Dreams", 2025);

            System.out.println("\nAlla filmer:");
            movieDAO.findAll().forEach(movies -> System.out.println(movies));

            Document found = movieDAO.findByTitle("Super Mario Galaxy");
            if (found != null) {
                System.out.println("Hittat titel:");
                System.out.println(found.toJson());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
