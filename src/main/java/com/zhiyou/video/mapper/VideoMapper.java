package com.zhiyou.video.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.common.PageInfo;
import com.zhiyou.video.model.QueryVideo;
import com.zhiyou.video.model.Video;

/** 
* @author JWL
* @Time 2017年8月18日 下午7:20:39  
*
*/
public interface VideoMapper {
	
	public int videoCount(Map<String,Object> map);
	
	public List<Video> getVideos(Map<String,Object> map);
	
	public void batchDelete(@Param("videos") List<Video> videos);
	
	List<Map<String, Object>> getChartData();
	
	public void addVideo(Video video);
	
	public Video getVideoById(Integer id);
	
	public void updateVideo(Video video);
	
	public boolean deleteVideo(Integer id);
	
	
}
