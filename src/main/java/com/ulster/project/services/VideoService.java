package com.ulster.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ulster.project.models.Video;
import com.ulster.project.repository.VideoRepository;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public Video uploadVideo(Video video) {
        return videoRepository.save(video);
    }

    public List<Video> searchVideos(String query) {
        return videoRepository.findByTitleContainingOrHashtagsContaining(query, query);
    }
}
