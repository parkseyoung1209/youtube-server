package com.server.youtube.controller;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.server.youtube.domain.Channel;
import com.server.youtube.domain.QVideo;
import com.server.youtube.domain.Video;
import com.server.youtube.domain.VideoDTO;
import com.server.youtube.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class VideoController {

    @Autowired
    private VideoService service;

    // 업로드 경로
    private String path="\\\\192.168.10.51\\youtube\\";

    @GetMapping("/video")
    public ResponseEntity viewAll(@RequestParam(name="page", defaultValue = "1") int page, @RequestParam(name="keyword", required = false) String keyword) {

        log.info(keyword);
        // booleanbuilder : where문에 들어가는 조건들을 넣어주는 컨테이너
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // 동적 처리를 하려면 Q 도메인 클래스 가져오기
        // Q도메인 클래스를 이용하면 entity 클래스에 선언된 필드들을 변수로 활용할 수 있음
        QVideo qVideo = QVideo.video;

        // 만약에 WHERE video_code = 1

        // WHERE video_title LIKE CONCAT('%', keyword, '%')

        if(keyword != null && !keyword.equals("")) {
            // 원하는 조건은 필드값과 같이 결합해서 생성
            BooleanExpression expression = qVideo.videoTitle.like('%'+keyword + '%');

            // 만들어진 조건은 where문에 and나 or 같은 키워드와 결합해서 추가
            booleanBuilder.and(expression);
        }
        // 정렬 조건 필요시 Sort 객체
        Sort sort = Sort.by("videoDate").descending();

        Pageable pageable = PageRequest.of(page-1, 20, sort);
        Page<Video> list = service.viewAll(booleanBuilder, pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list.getContent());
    }
    @GetMapping("/video/{videoCode}")
    public ResponseEntity viewOne(@PathVariable(name = "videoCode") int videoCode) {
        return ResponseEntity.ok(service.viewOne(videoCode));
    }

    // 파일 업로드
    @PostMapping("/video")
    public ResponseEntity create(VideoDTO dto) throws IOException {
        // 파일 업로드
        String uuid = UUID.randomUUID().toString();

        String videoName = uuid + "_" + dto.getVideoFile().getOriginalFilename();
        String imageName = uuid + "_" + dto.getImageFile().getOriginalFilename();

        // http://192.168.10.51:8082/video/파일
        File videoFile = new File(path + "video" + File.separator + videoName);
        // http://192.168.10.51:8082:thumbnail/파일
        File imageFile = new File(path + "thumbnail" + File.separator + imageName);
        // 실제 업로드
        dto.getVideoFile().transferTo(videoFile);
        dto.getImageFile().transferTo(imageFile);

        log.info(dto.toString());

        Video video = service.create(Video.builder()
                .videoTitle(dto.getVideoTitle())
                .channel(service.viewChannel(dto.getChannelCode()))
                .videoDesc(dto.getVideoDesc())
                .videoDate(LocalDateTime.now())
                .videoUrl("http://192.168.10.51:8082/video" + File.separator + videoName)
                .videoImg("http://192.168.10.51:8082/thumbnail" + File.separator + imageName)
                .build());
        return ResponseEntity.ok(video);
    }
}
