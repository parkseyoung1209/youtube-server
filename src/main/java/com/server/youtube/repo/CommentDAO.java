package com.server.youtube.repo;

import com.server.youtube.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment, Integer> {

}
