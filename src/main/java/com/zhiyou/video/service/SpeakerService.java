package com.zhiyou.video.service;

import java.util.List;
import java.util.Map;

import com.zhiyou.video.common.PageInfo;
import com.zhiyou.video.model.Speaker;

/** 
* @author JWL
* @Time 2017年8月16日 下午3:06:40  
*
*/
public interface SpeakerService {
	
	PageInfo<Speaker> getPageInfo(Map<String,Object> map);
	
	public void addSpeaker(Speaker speaker);
	
	public Speaker getSpeakerById(Integer id);
	
	public void updateSpeaker(Speaker speaker);
	
	public void deleteSpeaker(Integer id);
	
	public List<Speaker> getAllSpeakers();
}
