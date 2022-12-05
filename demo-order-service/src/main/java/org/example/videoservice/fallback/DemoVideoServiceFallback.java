package org.example.videoservice.fallback;

import org.example.domain.Video;
import org.example.videoservice.DemoVideoService;
import org.springframework.stereotype.Service;

@Service
public class DemoVideoServiceFallback implements DemoVideoService {
    @Override
    public Video findById(Integer videoId) {
        // 返回默认值
        Video video = new Video();
        video.setTitle("限流了");
        video.setCoverImg("限流的图片");
        video.setId(0);
        return video;
    }

    @Override
    public Video saveVideo(Video video) {
        return null;
    }

}
