package com.zhiyou.video.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou.video.common.OperationResult;
import com.zhiyou.video.mapper.UserMapper;
import com.zhiyou.video.model.User;
import com.zhiyou.video.service.FtpService;
import com.zhiyou.video.service.UserService;
import com.zhiyou.video.util.FileUtil;
import com.zhiyou.video.util.MD5Util;

/** 
* @author JWL
* @Time 2017年8月30日 上午10:53:25  
*
*/

@Controller
@RequestMapping("/front/user")
public class UserController extends BaseController{

	@Autowired
	private UserService userservice;
	@Autowired
	private UserMapper usermapper;
	@Autowired
    private FtpService ftpService;
	
	@RequestMapping("/index.action")
	public String UserIndex(HttpSession session){
		
		
		String email = (String) session.getAttribute("email");
		User user = userservice.getUserByEmail(email);
		
		session.setAttribute("user", user);
		return "/front/user/index";
	}
	
	
	@RequestMapping(value="/regist.action",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult regist(User user,Model model){
			
		OperationResult result = new OperationResult();
		
		String md502 = MD5Util.getMd5Simple(user.getPassWord());
		user.setPassWord(md502);
//		user.setInsertTime(new Data(System.currentTimeMillis()));
		user.setInsertTime(Timestamp.valueOf(LocalDateTime.now()));
		
		/*if(user.getPassWord().length()<6){
			result.setErrorMessage("密码不能小于六位！");
		}else */
		if(userservice.registUser(user)){
			
			result.setSuccess(true);
			System.out.println("=========="+userservice.registUser(user));
		}else {
			result.setErrorMessage("注册失败！");
		}
		System.out.println("result============"+result);
		System.out.println("user============"+user);
		return result;
	}
	
	@RequestMapping("/avatar.action")
	public String avatarUpdateIndex(){

		return "/front/user/avatar";
	}
	
	@RequestMapping(value="/avatar.action",method=RequestMethod.POST)
	public String avatarUpdate(MultipartFile icon,User user,HttpSession session){
		
		String email = (String) session.getAttribute("email");
		user.setEmail(email);
		
		//String headUrl = FileUtil.uploadImage(icon);
		String iconname= icon.getOriginalFilename();
		String Suffix1 = iconname.substring(iconname.lastIndexOf("."),iconname.length());
		iconname = String.valueOf(System.currentTimeMillis())+String.valueOf(Math.random() * 10000)+Suffix1;
		
		user.setHeadUrl(iconname);
		
		try {
			InputStream inputStream1 = icon.getInputStream();
			try {
				ftpService.uploadFile(iconname, inputStream1);
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
		
		if(userservice.updateUser(user)){
			return "redirect:/front/user/index.action";
		}
		return "/front/user/avatar";
	}
	
	@ResponseBody
	@RequestMapping(value="/headurl.action" , method =RequestMethod.GET)
	public void userheadurl(String headUrl, HttpServletResponse response){
  
		//Video video = videoservice.getVideoById(videoId);
		
        response.reset();
        response.setContentType("bin");
        

        response.setContentType("charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.addHeader("Content-Disposition","inline;filename=" + headUrl);
        

        
        OutputStream fos = null;                                    
        try {
            fos=response.getOutputStream();
            ftpService.downloadFtpFile(headUrl,fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
        }
       

	}
	
	@RequestMapping("/profile.action")
	public String profileIndex(){

		return "/front/user/profile";
	}
	
	@RequestMapping(value="/profile.action",method=RequestMethod.POST)
	public String profile(User user,HttpSession session){
		
/*		System.out.println("post-user=============="+user);
		
		user.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
		if(userservice.updateUser(user)){
			
			session.setAttribute("user",user);
			return "redirect:/front/user/index.action";
		}
		return "/front/user/profile";
*/					
		
		String email = (String) session.getAttribute("email");
		Map<String,Object> map = new HashMap<>();
		System.out.println("post-user=============="+user);
		
		user.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
		map.put("email", email);
		System.err.println("post-email=============="+email);
		map.put("nick_name", user.getNickName());
		map.put("sex", user.getSex());
		map.put("birthday", user.getBirthday());
		//map.put("nick_name", user.getNickName());
		//map.put("nick_name", user.getNickName());
		if(usermapper.updateUser(map)){
			
			session.setAttribute("user",map);
			return "redirect:/front/user/index.action";
		}
		return "/front/user/profile";
		
		
	}
	
	@RequestMapping("/password.action")
	public String rePasswordIndex(){

		return "/front/user/password";
	}
	
	
	@RequestMapping(value="/password.action",method=RequestMethod.POST)
	public String rePassword(Model model,HttpSession session,User user){
		
		User user1 = (User) session.getAttribute("user");
		String email = user1.getEmail();
		user.setEmail(email);
		user.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
		if(MD5Util.getMd5Simple(user.getPassWord()).equals(user1.getPassWord())){
			if(user.getNewPassWord()!=null && !user.getNewPassWord().equals("")){
				
				if(user.getNewPassWord().length()>3 && user.getNewPassWord().length()<30){
					user.setNewPassWord(MD5Util.getMd5Simple(user.getNewPassWord()));
					user.setNewPassWordAgain(MD5Util.getMd5Simple(user.getNewPassWordAgain()));
					if(user.getNewPassWord().equals(user.getNewPassWordAgain())){
						if(userservice.updateUser(user)){
							session.setAttribute("user", user);
							return "redirect:/front/user/index.action";
						}else{
							model.addAttribute("message", "密码修改失败");
							model.addAttribute("user", user1);
						}
					}else{
						model.addAttribute("message","两次密码不一致");
						model.addAttribute("user", user1);
					}
				}else{
					model.addAttribute("message", "新密码长度必须在3-30位之间");
					model.addAttribute("user", user1);
				}
			}else{
				model.addAttribute("message","新密码不能为空");
				model.addAttribute("user", user1);
			}
		}else{
			model.addAttribute("message","原密码输入错误！");
			model.addAttribute("user", user1);
		}
		return "/front/user/password";
		
	}
}
