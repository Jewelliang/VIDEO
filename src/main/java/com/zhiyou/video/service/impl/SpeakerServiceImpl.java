package com.zhiyou.video.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou.video.common.PageInfo;
import com.zhiyou.video.mapper.SpeakerMapper;
import com.zhiyou.video.model.Speaker;
import com.zhiyou.video.service.SpeakerService;

/** 
* @author JWL
* @Time 2017年8月16日 下午3:06:55  
*
*/
@Service
public class SpeakerServiceImpl implements SpeakerService{

	@Autowired  
	private SpeakerMapper speakermapper;
	
	@Override
	public PageInfo<Speaker> getPageInfo(Map<String, Object> map) {

		int pageSize = (int) map.get("pageSize");
		int count = speakermapper.speakerTotalCount(map);
		int pageNums = 0;
		if(count%pageSize == 0){
			pageNums = count/pageSize;
		}else{
			pageNums = count/pageSize+1;
		}
		int pageNum = (int) map.get("pageNum");
		int start = (pageNum-1)*pageSize;
		map.put("start", start);
		List<Speaker> results = speakermapper.getSpeakers(map);
		PageInfo<Speaker> pageInfo = new PageInfo<>();
		pageInfo.setAllNum(count);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageNums(pageNums);
		pageInfo.setPageSize(pageSize);
		pageInfo.setResults(results);
		
		return pageInfo;
	}

	@Override
	public void addSpeaker(Speaker speaker) {
		// TODO Auto-generated method stub
		speakermapper.addSpeaker(speaker);
	}

	@Override
	public Speaker getSpeakerById(Integer id) {
		// TODO Auto-generated method stub
		return speakermapper.getSpeakerById(id);
	}

	@Override
	public void updateSpeaker(Speaker speaker) {
		// TODO Auto-generated method stub
		speakermapper.updateSpeaker(speaker);
	}

	@Override
	public void deleteSpeaker(Integer id) {
		// TODO Auto-generated method stub
		speakermapper.deleteSpeaker(id);
	}

	@Override
	public List<Speaker> getAllSpeakers() {
		// TODO Auto-generated method stub
		return speakermapper.getAllSpeakers();
	}

	
}
