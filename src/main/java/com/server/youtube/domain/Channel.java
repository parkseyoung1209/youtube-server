package com.server.youtube.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Channel {
    @Id
    @Column(name="channel_code")
    private int channelCode;

    @Column(name="channel_img")
    private String channelImg;

    @Column(name="channel_name")
    private String channelName;

    @Column(name="id")
    private String id;
}
