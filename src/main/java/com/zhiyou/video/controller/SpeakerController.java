package com.zhiyou.video.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou.video.common.Myconstant;
import com.zhiyou.video.common.PageInfo;
import com.zhiyou.video.model.Speaker;
import com.zhiyou.video.service.FtpService;
import com.zhiyou.video.service.SpeakerService;
import com.zhiyou.video.util.FileUtil;

/** 
* @author JWL
* @Time 2017年8月16日 下午2:53:42  
*
*/
@Controller
@RequestMapping("/admin/speaker")
public class SpeakerController extends BaseController{

	
	@Value("${picurl}")
	private String picUrl;
	
	@Autowired
    private FtpService ftpService;
	
	@Autowired
	private SpeakerService speakerservice;
	
	@RequestMapping("/index.action")
	public String getSpeakers(Model model,
			@RequestParam(value="queryName",required=false)String queryName, 
			@RequestParam(required=false) String queryJob,
			@RequestParam(required=false)Integer pageNum){
		
		if(pageNum == null || pageNum ==0){
			pageNum =1;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum",pageNum);
		map.put("pageSize", Myconstant.PAGESIZE);
		map.put("queryName", queryName);
		map.put("queryJob", queryJob);
		PageInfo<Speaker> pageInfo = speakerservice.getPageInfo(map);
		model.addAttribute("pageInfo",pageInfo);
		
		return "/admin/speaker/index";
	}
	
	@RequiresPermissions(value={"speaker:add"})
	@RequestMapping(value="/add.action",method=RequestMethod.GET)
	public String addIndxe(){
		
		
		return "/admin/speaker/add";
		
	}
	
	@RequiresPermissions(value={"speaker:add"})
	@RequestMapping(value="/add.action",method=RequestMethod.POST)
	public String add(Model model,Speaker speaker){
		
		MultipartFile iconFile = speaker.getIcon();
		String speakerHeadUrl = FileUtil.uploadImage(iconFile);
		
		String Suffix1 = speakerHeadUrl.substring(speakerHeadUrl.lastIndexOf("."),speakerHeadUrl.length());
		speakerHeadUrl = String.valueOf(System.currentTimeMillis())+String.valueOf(Math.random() * 10000)+Suffix1;
		speaker.setSpeakerHeadUrl(speakerHeadUrl);
		//speaker.setInsertTime(new Date(System.currentTimeMillis()));
		//speaker.setUpdateTime(new Date(System.currentTimeMillis()));
		
		try {
			InputStream inputStream1 = iconFile.getInputStream();
			try {
				ftpService.uploadFile(speakerHeadUrl, inputStream1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		speakerservice.addSpeaker(speaker);
			
		return "redirect:/admin/speaker/index.action";
		
	}
	
	@RequiresPermissions(value={"speaker:update"})
	@RequestMapping(value="/edit.action",method=RequestMethod.GET)
	public String updateIndex(Model model,Integer id){
		Speaker speaker = speakerservice.getSpeakerById(id);
		model.addAttribute("speaker", speaker);
		return "/admin/speaker/edit";
		
	}
	
	@RequiresPermissions(value={"speaker:update"})
	@RequestMapping(value="/edit.action",method=RequestMethod.POST)
	public String update(Model model,Speaker speaker){
		
		//speaker.setUpdateTime(new Date(System.currentTimeMillis()));
		speakerservice.updateSpeaker(speaker);
		
		return "redirect:/admin/speaker/index.action";
		
	}
	
	@RequiresPermissions(value={"speaker:delete"})
	@RequestMapping("/delete.action")
	public String delete(Integer id){
		
		speakerservice.deleteSpeaker(id);
		return "redirect:/admin/speaker/index.action";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}










