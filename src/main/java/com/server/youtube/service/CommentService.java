package com.server.youtube.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.youtube.domain.Comment;
import com.server.youtube.domain.CommentDTO;
import com.server.youtube.domain.QComment;
import com.server.youtube.repo.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDAO dao;

    public Comment create(Comment vo) {
        return dao.save(vo);
    }

    @Autowired
    private JPAQueryFactory queryFactory;

    private final QComment qComment = QComment.comment;
    // SELECT * FROM comment WHERE video_code=:videoCode AND parent_code = 0 ORDER BY comment_date DESC

    public List<Comment> getTopComments(int videoCode) {
        return queryFactory
                .selectFrom(qComment)
                .where(qComment.videoCode.eq(videoCode))
                .where(qComment.parentCode.eq(0))
                .orderBy(qComment.commentDate.desc())
                .fetch();
    }

    // SELECT * FROM comment WHERE video_code:videoCode AND parent_code = parentCode ORDER BY comment_date asc
    public List<Comment> getReComments(int parentCode) {
        return queryFactory.selectFrom(qComment)
                .where(qComment.parentCode.eq(parentCode))
                .orderBy(qComment.commentDate.asc())
                .fetch();
    }
    public void update(Comment vo) {
        Comment comment = dao.findById(vo.getCommentCode()).get();
        comment.setCommentText(vo.getCommentText());
        dao.save(comment);
    }
    public void remove(int commentCode) {
        dao.deleteById(commentCode);
    }
}
