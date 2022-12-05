package org.example.service;

import org.example.domain.VideoOrder;

public interface VideoOrderService {
    VideoOrder findById(Integer orderId);
    Integer save(VideoOrder videoOrder);
}
