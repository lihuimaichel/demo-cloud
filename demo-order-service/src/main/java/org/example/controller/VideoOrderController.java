package org.example.controller;

import org.example.domain.Video;
import org.example.domain.VideoOrder;
import org.example.mapper.VideoOrderMapper;
import org.example.service.VideoOrderService;
import org.example.videoservice.DemoVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class VideoOrderController {
    @Autowired
    private VideoOrderService videoOrderService;

    @Autowired
    private RestTemplate restTemplate;
    // 一、使用RestTemplate
    @PostMapping("save")
    public Integer save(Integer videoId) {
        // 自动获取Video
        Video video = restTemplate.getForObject("http://localhost:8003/get?videoId=" + videoId, Video.class);
        if(video == null) {
            return 0;
        }
        return saveVideoOrder(videoId, video);
    }

    // 二、使用服务发现nacos
    @Autowired
    private DiscoveryClient discoveryClient;
    @PostMapping("save2")
    public Integer save2(Integer videoId) {
        List<ServiceInstance> list = discoveryClient.getInstances("demo-video-service");
        if(list.size() == 0) {
            return 0;
        }
        ServiceInstance serviceInstance = list.get(0);
        String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/get?videoId="+videoId;
        Video video = restTemplate.getForObject(url, Video.class);
        if(video == null) {
            return 0;
        }
        return saveVideoOrder(videoId, video);
    }

    // 三、使用Ribbon

    @PostMapping("save3")
    public Object save3(Integer videoId) {

        String url = "http://demo-video-service/get?videoId="+videoId;
        Video video = restTemplate.getForObject(url, Video.class);
        if(video == null) {
            return null;
        }
        saveVideoOrder(videoId, video);
        return video;
    }

    // 四、使用feign
    @Autowired
    DemoVideoService videoService;
    @PostMapping("save4")
    public Object save4(Integer videoId) {
        Video video = videoService.findById(videoId);
        if(video == null) {
            return null;
        }
        saveVideoOrder(videoId, video);
        return video;
    }


    // 远程传递对象
    @PostMapping("save_video")
    public Object saveVideo() {
        Video video = new Video();
        video.setSummary("AAAAA");
        video.setTitle("aaaaaaa");
        video.setCreateTime(new Date());
        video.setCoverImg("https://img2.baidu.com/it/u=1835843610,1575206394&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=999");
        video.setPrice(5890);
        return videoService.saveVideo(video);
    }

    @GetMapping("list")
    public String list() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("list");
        return "list";
    }

    @GetMapping("list2")
    public Object list2(Integer videoId) {
        return videoService.findById(videoId);
    }
    private Integer saveVideoOrder(Integer videoId, Video video) {
        VideoOrder videoOrder = new VideoOrder();

        videoOrder.setOutTradeNo(UUID.randomUUID().toString());
        videoOrder.setState(1);
        videoOrder.setUserId(1);
        videoOrder.setCreateTime(new Date());

        // 组装video
//        videoOrder.setVideoId(videoId);
//        videoOrder.setVideoImg("https://img2.baidu.com/it/u=1835843610,1575206394&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=999");
//        videoOrder.setVideoTitle("Nosql 课程");
//        videoOrder.setTotalFee(5000);

        videoOrder.setVideoId(videoId);
        videoOrder.setVideoImg(video.getCoverImg());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setTotalFee(video.getPrice());
        return videoOrderService.save(videoOrder);
    }

}
