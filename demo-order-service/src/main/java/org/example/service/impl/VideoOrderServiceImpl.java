package org.example.service.impl;

import org.example.domain.VideoOrder;
import org.example.mapper.VideoOrderMapper;
import org.example.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {
    @Autowired
    VideoOrderMapper videoOrderMapper;
    @Override
    public VideoOrder findById(Integer orderId) {
        return videoOrderMapper.findById(orderId);
    }

    @Override
    public Integer save(VideoOrder videoOrder) {
        return videoOrderMapper.save(videoOrder);
    }
}
