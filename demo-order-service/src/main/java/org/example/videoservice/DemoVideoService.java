package org.example.videoservice;

import org.example.domain.Video;
import org.example.videoservice.fallback.DemoVideoServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="demo-video-service", fallback = DemoVideoServiceFallback.class)
public interface DemoVideoService {
    @GetMapping(value="/get")
    Video findById(@RequestParam("videoId") Integer videoId);

    @PostMapping(value="/save")
    Video saveVideo(@RequestBody Video video);
}
