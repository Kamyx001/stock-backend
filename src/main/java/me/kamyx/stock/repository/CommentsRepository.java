package me.kamyx.stock.repository;

import me.kamyx.stock.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentsRepository extends MongoRepository<Comment, String> {
    //Optional<Comment> findByShortName(String shortName);

    Optional<Comment> getCommentById(String id);


}
