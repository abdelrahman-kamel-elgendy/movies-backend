package dev.abdelrahman.movies.Models.Review;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import dev.abdelrahman.movies.Models.User.User;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String body;
    private boolean isActive;

    @DocumentReference
    private User user;

    public Review(String body, boolean isActive, User user) {
        this.body = body;
        this.isActive = isActive;
        this.user = user;
    }
}
