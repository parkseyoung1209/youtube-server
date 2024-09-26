package com.server.youtube.controller;

import com.server.youtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/*")
public class VideoController {

    @Autowired
    private VideoService service;

    @GetMapping("/video")
    public ResponseEntity viewAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.viewAll());
    }
}
