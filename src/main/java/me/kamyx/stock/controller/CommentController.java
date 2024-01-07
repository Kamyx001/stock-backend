package me.kamyx.stock.controller;

import me.kamyx.stock.model.Comment;
import me.kamyx.stock.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Comment>(commentService.createComment(payload.get("commentBody"), payload.get("shortName")), HttpStatus.CREATED);
    }

}