package com.ulster.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ulster.project.models.Video;
import com.ulster.project.services.VideoService;

@RestController
@RequestMapping("/api/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('CREATOR')")
    public ResponseEntity<Video> uploadVideo(@RequestBody Video video) {
        return ResponseEntity.ok(videoService.uploadVideo(video));
    }
   
    @GetMapping("/search")
    public ResponseEntity<List<Video>> search(@RequestParam String query) {
        return ResponseEntity.ok(videoService.searchVideos(query));
    }

    @GetMapping("/latest")
    public ResponseEntity<List<Video>> getLatestVideos() {
        return ResponseEntity.ok(videoService.searchVideos("")); // Return all as default
    }
}