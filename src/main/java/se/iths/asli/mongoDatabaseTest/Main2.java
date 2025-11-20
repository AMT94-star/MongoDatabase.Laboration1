package se.iths.asli.mongoDatabaseTest;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import se.iths.asli.mongoDatabaseTest.DAO.MoviesMovieDAO;

public class Main2 {
    public static void main(String[] args) {
        String uri = "mongodb+srv://AMT94-star:CX1ZtpjP9VWKgxnM@clustercreationtest.wqamzf4.mongodb.net/?appName=ClusterCreationTest";
        String databaseName = "clustercreationtest";
        String collectionName = "Movies";

        try (MongoClient client = MongoClients.create(uri)) {
            //härinne har vi en anslutning till atlas klustret
            MongoDatabase database = client.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            MoviesMovieDAO dao = new MoviesMovieDAO(collection);
            dao.insert("Gökboet", 1982);
        }
    }
}
