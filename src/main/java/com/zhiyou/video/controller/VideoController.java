package com.zhiyou.video.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou.video.common.Myconstant;
import com.zhiyou.video.common.OperationResult;
import com.zhiyou.video.common.PageInfo;
import com.zhiyou.video.common.ValidGroup1;
import com.zhiyou.video.mapper.VideoMapper;
import com.zhiyou.video.model.Course;
import com.zhiyou.video.model.QueryVideo;
import com.zhiyou.video.model.Speaker;
import com.zhiyou.video.model.Subject;
import com.zhiyou.video.model.Video;
import com.zhiyou.video.service.CourseService;
import com.zhiyou.video.service.FtpService;
import com.zhiyou.video.service.SpeakerService;
import com.zhiyou.video.service.SubjectService;
import com.zhiyou.video.service.VideoService;
import com.zhiyou.video.util.FileUtil;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.RandomUtils;
/** 
* @author JWL
* @Time 2017年8月18日 上午9:59:40  
*/

@Controller
@RequestMapping("/admin/video") 
public class VideoController{
		
	@Autowired
	private CourseService courseservice;
	
	@Autowired
	private SpeakerService speakerservice;
	

	@Autowired
	private VideoService videoservice;

	@Autowired
    private FtpService ftpService;
	
	@Value("${ftp.Host}")
    private String ftpHost;

    @Value("${ftp.Port}")
    private String  ftpPort;

    @Value("${ftp.BasePath}")
    private String basePath;
	
    
    private DiskFileItemFactory factory = new DiskFileItemFactory();
    @PostConstruct
    public void init(){
        factory.setSizeThreshold(4096);
    }
    
	@RequestMapping("/index.action")
	public String VideoList(Model model,QueryVideo queryVideo,@RequestParam(required=false)Integer pageNum){
		
		if(pageNum == null || pageNum ==0){
			pageNum =1;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum",pageNum);
		map.put("pageSize", Myconstant.PAGESIZE);
		map.put("queryVideo", queryVideo);
		
		List<Speaker> speakers = speakerservice.getAllSpeakers();
		map.put("speakers", speakers);
		
		List<Course> courses = courseservice.getAllCourses();
		model.addAttribute("courses", courses);
		map.put("courses", courses);
		
		PageInfo<Video> pageInfo = videoservice.getVideos(map);
		model.addAttribute("pageInfo",pageInfo);
		
		return "/admin/video/index";
	
	}
	

	@RequestMapping("/batchDelete.action")
	@ResponseBody
	public OperationResult batchDelete(@RequestBody List<Video> videos){
		
		OperationResult result = new OperationResult();
		videoservice.batchDelete(videos);
		
		System.err.println(videos);
		result.setErrorCode("0");
		result.setErrorMessage("正常");
		result.setContent(videos);
		return result;
		
	}

	@RequestMapping(value="/add.action",method=RequestMethod.GET)
	public String videoAddIndex(Model model,QueryVideo queryVideo){
		
		List<Speaker> speakers = speakerservice.getAllSpeakers();
		model.addAttribute("speakers", speakers);
		
		List<Course> courses = courseservice.getAllCourses();
		model.addAttribute("courses", courses);
		
//		List<Subject> subjects = subjectservice.getAllSubjects();
//		model.addAttribute("subjects", subjects);
		
//		List<Video> videos = videoservice.getVideos(queryVideo);
//		model.addAttribute("results", videos);
		
		model.addAttribute("query", queryVideo);
		
		return "/admin/video/add";
		
		
	}

	@RequestMapping(value="/add.action",method=RequestMethod.POST)
	public String videoAdd(Model model,Video video,BindingResult result,HttpServletRequest request){
	
		//参数校验  ，两种方式，一个是继承BaseController，直接调用封装的方法，直接返回一个错误提示界面。
		//第二种方式，用注解，@Validated 注解对象，和BindingResult 的getAllErrers方法
		/*if(!beanValidator(model, video, ValidGroup1.class)){
			return "/front/message";
		}
		
		if(result.hasErrors()){
			StringBuilder sb = new StringBuilder();
			List<ObjectError> errors = result.getAllErrors();
			for (int i = 0; i < errors.size(); i++) {
				ObjectError er = errors.get(i);
				String errorMessage;
				try {
					errorMessage = new String(er.getDefaultMessage().getBytes("ISO-8859-1"),"UTF-8");
					sb.append(errorMessage).append(errorMessage.length()>1?"<br/>":"");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
			return "/front/message";
		}*/
		
		//文件上传 
		MultipartFile icon = video.getIcon();
		MultipartFile video1 = video.getVideo1();
		String iconname= icon.getOriginalFilename();
		String videoname= video1.getOriginalFilename();
		
		String Suffix1 = iconname.substring(iconname.lastIndexOf("."),iconname.length());
		String Suffix2 = videoname.substring(videoname.lastIndexOf("."),videoname.length());
		iconname = String.valueOf(System.currentTimeMillis())+String.valueOf(Math.random() * 10000)+Suffix1;
		videoname = String.valueOf(System.currentTimeMillis())+String.valueOf(Math.random() * 10000)+Suffix2;
		video.setVideoImageUrl(iconname);
		video.setVideoUrl(videoname);
		
		try {
			InputStream inputStream1 = icon.getInputStream();
			InputStream inputStream2 = video1.getInputStream();
			try {
				ftpService.uploadFile(iconname, inputStream1);
				ftpService.uploadFile(videoname, inputStream2);
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
		
		
		videoservice.addVideo(video);
		return "redirect:/admin/video/index.action";
	}
	
	

	@RequestMapping(value="/edit.action",method=RequestMethod.GET)
	public String videoUpdateIndex(Model model,Integer id){
		
		List<Speaker> speakers = speakerservice.getAllSpeakers();
		model.addAttribute("speakers", speakers);
		
		List<Course> courses = courseservice.getAllCourses();
		model.addAttribute("courses", courses);
		
		Video video = videoservice.getVideoById(id);
		model.addAttribute("video", video);
		return "/admin/video/edit";
		
	}
	
	@RequestMapping(value="/edit.action",method=RequestMethod.POST)
	public String videoUpdate(Video video){
		
		videoservice.updateVideo(video);
		return "redirect:/admin/video/index.action";
		
	}

	@RequiresPermissions(value={"delete"})
	@RequestMapping("/delete.action")
	@ResponseBody
	public  OperationResult videoDelete(Integer id){
		OperationResult result=new OperationResult();
		boolean b = videoservice.deleteVideo(id);	
        result.setSuccess(b);
		
		return result;
		
	}
	
	
	
}
















