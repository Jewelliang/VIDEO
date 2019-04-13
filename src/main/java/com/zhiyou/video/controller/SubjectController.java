package com.zhiyou.video.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou.video.model.Course;
import com.zhiyou.video.model.QueryVideo;
import com.zhiyou.video.model.Speaker;
import com.zhiyou.video.model.Subject;
import com.zhiyou.video.service.SubjectService;
import com.zhiyou.video.util.FileUtil;

@Controller
@RequestMapping("/admin/subject")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;

	@RequestMapping("/index.action")
	public String VideoList(Model model){
		List<Subject> subject = subjectService.getAllSubjects();
		model.addAttribute("subject", subject);
		return "/admin/subject/index";
	
	}

	@RequestMapping(value="/add.action",method=RequestMethod.GET)
	public String addIndxe(){
		return "/admin/subject/add";	
	}
	

	@RequestMapping(value="/add.action",method=RequestMethod.POST)
	public String add(Model model,Subject subject){
		
		subjectService.addSubject(subject);		
		return "redirect:/admin/subject/index.action";
		
	}
	
	/*@RequiresPermissions(value={"speaker:update"})
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
		
	}*/
	
}

