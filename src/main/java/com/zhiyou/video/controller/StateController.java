package com.zhiyou.video.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou.video.service.VideoService;

/** 
* @author JWL
* @Time 2017年8月20日 下午9:17:58  
*
*/
@Controller
@RequestMapping("/admin/state")
public class StateController {

	@Autowired
	private VideoService videoservice;
	
	@RequestMapping("/courseavg.action")
	public String courseAvg(Model model){
		
		List<Map<String,Object>> list = videoservice.getChartData();
		System.out.println("统计结果============="+list);
		
		StringBuffer xbuffer = new StringBuffer();
		StringBuffer ybuffer = new StringBuffer();
		
		List<String> xlist = new ArrayList<String>();
		List<Integer> ylist = new ArrayList<Integer>();
		if(list!=null){
			for(Map<String,Object>map:list){
				xlist.add(map.get("course_name").toString());
				ylist.add(Integer.parseInt(map.get("avgTimes").toString()));
			}
		}
		
		//x轴数据拼接
		xbuffer.append("[");
		for (int i = 0; i < xlist.size(); i++) {
			if(i==0){
				xbuffer.append("'"+xlist.get(i)+"'");
			}else{
				xbuffer.append(","+"'"+xlist.get(i)+"'");
			}
		}
		xbuffer.append("]");
		
		//y轴数据拼接
		ybuffer.append("[{name:'平均播放次数',");
		ybuffer.append(" data: [");
		for (int i = 0; i < ylist.size(); i++) {
			if(i==0){
				ybuffer.append(ylist.get(i));
			}else{
				ybuffer.append(","+ylist.get(i));
			}
		}
		ybuffer.append("]}]");
		
		model.addAttribute("xdata", xbuffer.toString());
		model.addAttribute("ydata", ybuffer.toString());
		
		 return "/admin/state/index";
		
	}
	
	@RequestMapping("/ajaxdata.action")
	@ResponseBody
	public Object Ajaxdata(){
		
		List<Map<String,Object>> list = videoservice.getChartData();
		List<String> xlist = new ArrayList<String>();
		List<Integer> ylist = new ArrayList<Integer>();
		if(list!=null){
			for(Map<String,Object>map:list){
				xlist.add(map.get("course_name").toString());
				ylist.add(Integer.parseInt(map.get("avgTimes").toString()));
				System.out.println("xlist=============="+xlist);
				System.out.println("ylist=============="+ylist);
			}
		}
		
		//封装所有结果的map
		HashMap resultMap = new HashMap();
		
		HashMap ymap = new HashMap();
		ymap.put("name", "平均播放次数");
		ymap.put("data", ylist);
		List yy = new ArrayList();
		yy.add(ymap);
		
		resultMap.put("xdata",xlist);
		resultMap.put("ydata",yy);
		
		return resultMap;
	}
	
	

}
