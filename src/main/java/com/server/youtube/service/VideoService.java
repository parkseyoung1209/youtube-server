package com.server.youtube.service;

import com.server.youtube.domain.Video;
import com.server.youtube.repo.VideoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    private VideoDao dao;

    public List<Video> viewAll() {
        return dao.findAll();
    }
}
