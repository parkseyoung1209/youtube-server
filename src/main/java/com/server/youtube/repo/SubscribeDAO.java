package com.server.youtube.repo;

import com.server.youtube.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscribeDAO extends JpaRepository<Subscribe, Integer> {

    @Query(value = "SELECT count(*) FROM Subscribe WHERE channel_code=:channelCode", nativeQuery = true)
    int count(@Param("channelCode") int channelCode);

    @Query(value = "SELECT * FROM Subscribe WHERE channel_code=:channelCode AND id=:id", nativeQuery = true)
    Subscribe check(@Param("channelCode") int channelCode, @Param("id") String id);
}
