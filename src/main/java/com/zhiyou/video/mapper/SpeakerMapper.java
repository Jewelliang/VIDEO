package com.zhiyou.video.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.model.Speaker;

/** 
* @author JWL
* @Time 2017年8月16日 下午3:13:40  
*
*/
public interface SpeakerMapper {

	public int speakerTotalCount(Map<String,Object> map);
	
	public List<Speaker> getSpeakers(Map<String,Object> map);

	public void addSpeaker(Speaker speaker);
	
	public Speaker getSpeakerById(@Param("speakerId")Integer id);
	
	public void updateSpeaker(Speaker speaker);
	
	public void deleteSpeaker(@Param("speakerId")Integer id);
	
	public List<Speaker> getAllSpeakers();
}
