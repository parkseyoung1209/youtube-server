package com.server.youtube.repo;

import com.server.youtube.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelDAO extends JpaRepository<Channel, Integer> {
}
