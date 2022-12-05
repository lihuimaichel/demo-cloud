package org.example.service;

import org.example.domain.Video;

public interface VideoService {
    Video findById(Integer VideoId);
    Integer saveVideo(Video video);
}
