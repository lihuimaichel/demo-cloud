package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.domain.Video;

@Mapper
public interface VideoMapper {
    @Select("select * from video where id=#{videoId}")
    Video findById(@Param("videoId") Integer id);

    @Insert("insert into video(id, title, summary, cover_img, price, create_time) values(null, #{title}, #{summary}, #{coverImg}, #{price}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer saveVideo(Video video);
}
