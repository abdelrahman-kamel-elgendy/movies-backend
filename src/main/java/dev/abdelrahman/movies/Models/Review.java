package dev.abdelrahman.movies.Models;

import lombok.Data;
import org.bson.types.ObjectId;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reviews")
public class Review {
    public Review(String body) {
        this.body = body;
    }

    @Id
    private ObjectId id;
    private String body;


    public ObjectId getId() {
        return id;
    }

    public String getBody() {
        return body;
    }
}
