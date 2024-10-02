package com.server.youtube.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder @Data @NoArgsConstructor @AllArgsConstructor
public class VideoDTO {

    private MultipartFile videoFile;
    private MultipartFile imageFile;
    private String videoTitle;
    private String videoDesc;
    private int channelCode;
}
