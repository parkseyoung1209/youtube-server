package com.server.youtube.repo;

import com.server.youtube.domain.Video;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface VideoDAO extends JpaRepository<Video, Integer>, QuerydslPredicateExecutor<Video> {

    @Modifying
    @Transactional
    //update video set video_count = video_count + 1 where video_code = 1
    @Query(value="UPDATE video SET video_count = video_count + 1 WHERE video_code = :code", nativeQuery = true)
    void updateCount(@Param("code") int code);
}
