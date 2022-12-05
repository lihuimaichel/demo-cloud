package org.example.controller;

import org.example.domain.Video;
import org.example.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class VideoController {
    @Autowired
    VideoService videoService;
    @GetMapping("get")
    public Object getVideo(Integer videoId, HttpServletRequest request) {
        Video video = videoService.findById(videoId);
        video.setSummary(request.getServerName()+":"+request.getServerPort());
        return video;
    }

    @PostMapping("save")
    public Object saveVideo(@RequestBody Video video) {
        videoService.saveVideo(video);
        return video;
    }
}
