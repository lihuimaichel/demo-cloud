package org.example.service.impl;

import org.example.domain.Video;
import org.example.mapper.VideoMapper;
import org.example.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;
    @Override
    public Video findById(Integer videoId) {
        return videoMapper.findById(videoId);
    }

    @Override
    public Integer saveVideo(Video video) {
        return videoMapper.saveVideo(video);
    }
}
