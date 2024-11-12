package com.server.youtube.controller;

import com.server.youtube.domain.Comment;
import com.server.youtube.domain.CommentDTO;
import com.server.youtube.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/*")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping("/private/comment")
    public ResponseEntity add(@RequestBody Comment vo) {
        return ResponseEntity.status(HttpStatus.OK).body( service.create(vo));
    }

    @PutMapping("/private/comment")
    public ResponseEntity update(@RequestBody Comment vo) {
        service.update(vo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/private/comment/{commentCode}")
    public ResponseEntity remove(@PathVariable(name = "commentCode") int commentCode) {
        service.remove(commentCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/video/{videoCode}/comment")
    public ResponseEntity comments(@PathVariable(name = "videoCode") int videoCode) {
        List<Comment> comments = service.getTopComments(videoCode);
        List<CommentDTO> response = commentList(comments);
        return ResponseEntity.ok(response);
    }

    public List<CommentDTO> commentList(List<Comment> comments) {
        List<CommentDTO> response = new ArrayList<>();
        for(Comment comment : comments) {
            List<Comment> replies = service.getReComments(comment.getCommentCode());
            List<CommentDTO> repliesDTO = commentList(replies);
            CommentDTO dto = commentDetail(comment);
            dto.setReplies(repliesDTO);
            response.add(dto);
        }
        return response;
    }

    public CommentDTO commentDetail(Comment comment) {
        return CommentDTO.builder()
                .commentCode(comment.getCommentCode())
                .commentText(comment.getCommentText())
                .commentDate(comment.getCommentDate())
                .id(comment.getId())
                .videoCode(comment.getVideoCode())
                .build();
    }
}
