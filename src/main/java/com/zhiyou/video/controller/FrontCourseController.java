package com.zhiyou.video.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou.video.common.OperationResult;
import com.zhiyou.video.mapper.UserMapper;
import com.zhiyou.video.mapper.VideoMapper;
import com.zhiyou.video.model.Course;
import com.zhiyou.video.model.Speaker;
import com.zhiyou.video.model.Subject;
import com.zhiyou.video.model.User;
import com.zhiyou.video.model.Video;
import com.zhiyou.video.service.CourseService;
import com.zhiyou.video.service.FtpService;
import com.zhiyou.video.service.SpeakerService;
import com.zhiyou.video.service.UserService;
import com.zhiyou.video.service.VideoService;

/** 
* @author JWL
* @Time 2017年8月30日 上午11:08:13  
*
*/
@Controller
@RequestMapping("/front")
public class FrontCourseController {
	
	@Autowired
	private UserService userservice;
	@Autowired
	private VideoService videoservice;
	@Autowired
	private SpeakerService speakerservice;
	@Autowired
	private CourseService courseservice;
	
	@Autowired
	private UserMapper usermapper;
	
	@Autowired
    private FtpService ftpService;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(FrontCourseController.class);
    
	@RequestMapping("/course/index.action")
	public String courseIndex(Model model,Integer subjectId){	
	
		
		List<Course> course = userservice.getCourseById(subjectId);
		Subject subject = userservice.getSubjectById(subjectId);
		
		model.addAttribute("courses", course);
		model.addAttribute("subject", subject);
		model.addAttribute("subjectId", subjectId);
		return "/front/course/index";
	}
	
	@RequestMapping(value="/video/index.action",method=RequestMethod.POST)
	public OperationResult videoindex(HttpSession session){
		OperationResult result = new OperationResult();
		Object user = session.getAttribute("user");
		if(user ==null){
			result.setErrorMessage("请先登录！");
		}else{
			result.setSuccess(true);
		}
		return result;
	}
	
	@RequestMapping("/video/index.action")
	public String videoIndex(Model model,Integer videoId,Integer subjectId,HttpServletRequest request){
		
		
		Subject subject = userservice.getSubjectById(subjectId);
		Video video = videoservice.getVideoById(videoId);
		//Video video = usermapper.getVideoById(videoId);
		int sid = video.getSpeakerId();
		Speaker speaker = speakerservice.getSpeakerById(sid);
		
		Course course = courseservice.getCourseById(video.getCourseId());
		System.err.println("course1=========="+course);
		
		
		List<Video> video1 = usermapper.getAllVideo(video.getCourseId());
		model.addAttribute("videoList", video1);
		
		model.addAttribute("course", course);
		model.addAttribute("video",video);
		model.addAttribute("speaker", speaker);
		model.addAttribute("subject", subject);
		
	/*	Cookie[] cookies = reques.getCookies();
		
		for(Cookie cookie : cookies){
			System.out.println("find cookie : ==================="+cookie.getName() + ":" + cookie.getValue()) ;
		}*/

		OperationResult result = new OperationResult();
		
		System.err.println("video========="+video);
		System.err.println("subject========="+subject);
		
		
		return "/front/video/index";
		
        
	}

	@ResponseBody
	@RequestMapping(value="/video/video.action" , method =RequestMethod.GET)
	public void video(String videoUrl, HttpServletResponse response){
  
		//Video video = videoservice.getVideoById(videoId);
		
        response.reset();
        response.setContentType("bin");
        

        response.setContentType("charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.addHeader("Content-Disposition","inline;filename=" + videoUrl);
        

        
        OutputStream fos = null;                                    
        try {
            fos=response.getOutputStream();
            ftpService.downloadFtpFile(videoUrl,fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
        }
       

	}
	
	@ResponseBody
	@RequestMapping(value="/video/speaker.action" , method =RequestMethod.GET)
	public void speaker(String speakerHeadUrl, HttpServletResponse response){
  
		//Video video = videoservice.getVideoById(videoId);
		
        response.reset();
        response.setContentType("bin");
        

        response.setContentType("charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.addHeader("Content-Disposition","inline;filename=" + speakerHeadUrl);
        

        
        OutputStream fos = null;                                    
        try {
            fos=response.getOutputStream();
            ftpService.downloadFtpFile(speakerHeadUrl,fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
        }
       

	}
	
	
}
