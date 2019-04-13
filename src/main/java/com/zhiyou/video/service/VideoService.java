package com.zhiyou.video.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.common.PageInfo;
import com.zhiyou.video.model.QueryVideo;
import com.zhiyou.video.model.Speaker;
import com.zhiyou.video.model.Video;

/** 
* @author JWL
* @Time 2017年8月18日 下午7:15:30  
*
*/
public interface VideoService {
	
	public PageInfo<Video> getVideos(Map<String, Object> map);
	
	public void batchDelete(@Param("videos") List<Video> videos);
	
	List<Map<String, Object>> getChartData();
	
	public void addVideo(Video video);
	
	public Video getVideoById(@Param("id")Integer id);
	
	public void updateVideo(Video video);
	
	public boolean deleteVideo(@Param("id")Integer id);
}
