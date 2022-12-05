package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.example.domain.VideoOrder;
import org.example.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface VideoOrderMapper {
    @Select("select * from video_order where=#{orderId}")
    VideoOrder findById(Integer orderId);
    
    // TODO: 2022/12/1
    @Insert("insert into video_order (`id`, `out_trade_no`, `state`, `create_time`, `total_fee`, `video_id`, `video_title`, `video_img`, `user_id`) VALUES(null, #{outTradeNo}, #{state}, #{createTime}, #{totalFee}, #{videoId}, #{videoTitle}, #{videoImg}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer save(VideoOrder videoOrder);
}
