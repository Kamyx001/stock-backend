package me.kamyx.stock.service;

import me.kamyx.stock.model.Comment;
import me.kamyx.stock.repository.CommentsRepository;
import me.kamyx.stock.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public Comment createComment(String commentBody, String stockShortName) {
        Comment comment = commentsRepository.insert(new Comment(commentBody));

        mongoTemplate.update(Stock.class)
                .matching(Criteria.where("shortName").is(stockShortName))
                .apply(new Update().push("commentIds", comment.getId()))
                .first();

        return comment;
    }
}
