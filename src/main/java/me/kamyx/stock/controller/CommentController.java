package me.kamyx.stock.controller;

import me.kamyx.stock.model.Comment;
import me.kamyx.stock.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Comment>(commentService.createComment(payload.get("commentBody"), payload.get("shortName")), HttpStatus.CREATED);
    }
//    @GetMapping("/comments/{shortName}")
//    public ResponseEntity<Comment> getComment(@PathVariable("shortName") String shortName) {
//        return new ResponseEntity<Comment>(commentService.getCommentIds(shortName), HttpStatus.OK);
//    }

}
