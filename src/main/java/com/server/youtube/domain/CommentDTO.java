package com.server.youtube.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CommentDTO {
    private int commentCode;
    private String commentText;
    private LocalDateTime commentDate;
    private String id;
    private int videoCode;
    private List<CommentDTO> replies = new ArrayList<>();
}
