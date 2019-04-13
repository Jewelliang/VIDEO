package com.zhiyou.video.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou.video.common.PageInfo;
import com.zhiyou.video.mapper.VideoMapper;
import com.zhiyou.video.model.QueryVideo;
import com.zhiyou.video.model.Speaker;
import com.zhiyou.video.model.Video;
import com.zhiyou.video.service.VideoService;

/** 
* @author JWL
* @Time 2017年8月18日 下午7:15:44  
*
*/
@Service
public class VideoServiceImpl implements VideoService{

	@Autowired
	private VideoMapper videomapper;
	
	

	@Override
	public PageInfo<Video> getVideos(Map<String, Object> map) {

		int pageSize = (int) map.get("pageSize");
		int count = videomapper.videoCount(map);
		int pageNums = 0;
		if(count%pageSize == 0){
			pageNums = count/pageSize;
		}else{
			pageNums = count/pageSize+1;
		}
		int pageNum = (int) map.get("pageNum");
		int start = (pageNum-1)*pageSize;
		map.put("start", start);
		List<Video> results = videomapper.getVideos(map);
		PageInfo<Video> pageInfo = new PageInfo<>();
		pageInfo.setAllNum(count);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageNums(pageNums);
		pageInfo.setPageSize(pageSize);
		pageInfo.setResults(results);
		
		return pageInfo;
	}
	
	@Override
	public void batchDelete(List<Video> videos) {
		// TODO Auto-generated method stub
		videomapper.batchDelete(videos);
	}

	@Override
	public List<Map<String, Object>> getChartData() {
		// TODO Auto-generated method stub
		return videomapper.getChartData();
	}

	@Override
	public void addVideo(Video video) {
		// TODO Auto-generated method stub
		videomapper.addVideo(video);
	}

	@Override
	public Video getVideoById(Integer id) {
		// TODO Auto-generated method stub
		return videomapper.getVideoById(id);
	}

	@Override
	public void updateVideo(Video video) {
		// TODO Auto-generated method stub
		videomapper.updateVideo(video);
	}

	@Override
	public boolean deleteVideo(Integer id) {
		// TODO Auto-generated method stub
		return videomapper.deleteVideo(id);
	}

	

}
