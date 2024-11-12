package com.server.youtube.controller;

import com.server.youtube.domain.Subscribe;
import com.server.youtube.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/*")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class SubscribeController {
    @Autowired
    private SubscribeService service;

    @PostMapping("private/sub")
    public ResponseEntity create(@RequestBody Subscribe sub) {
        service.create(sub);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("private/sub/{subCode}")
    public ResponseEntity remove(@PathVariable(name="subCode") int subCode) {
        service.remove(subCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("private/sub/{channelCode}/count")
    public ResponseEntity count(@PathVariable(name="channelCode") int channelCode) {
        return ResponseEntity.status(HttpStatus.OK).body(service.count(channelCode));
    }

    @GetMapping("/private/sub/{channelCode}")
    public ResponseEntity check(@PathVariable(name = "channelCode") int channelCode) {
        return ResponseEntity.ok(service.check(channelCode));
    }
}
