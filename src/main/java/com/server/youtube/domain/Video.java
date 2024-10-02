package com.server.youtube.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity @DynamicInsert
public class Video {
    @Id
    @Column(name="video_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videoCode;

    @Column(name="video_url")
    private String videoUrl;

    @Column(name="video_img")
    private String videoImg;

    @Column(name="video_title")
    private String videoTitle;

    @Column(name="video_count")
    private int videoCount;

    @Column(name="video_date")
    private LocalDateTime videoDate;

    @Column(name="video_desc")
    private String videoDesc;

    @ManyToOne
    @JoinColumn(name="channel_code")
    private Channel channel;
}
