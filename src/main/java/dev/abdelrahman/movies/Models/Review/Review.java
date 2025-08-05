package dev.abdelrahman.movies.Models.Review;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reviews")
public class Review {
    public Review(String body, boolean isActive) {
        this.body = body;
        this.isActive = isActive;
    }

    @Id
    private String id;
    private String body;
    private boolean isActive;
}
