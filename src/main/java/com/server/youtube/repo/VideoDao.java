package com.server.youtube.repo;

import com.server.youtube.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface VideoDAO extends JpaRepository<Video, Integer>, QuerydslPredicateExecutor<Video> {

}
