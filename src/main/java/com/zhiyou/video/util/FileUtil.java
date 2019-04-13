package com.zhiyou.video.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou.video.service.FtpService;

/** 
* @author JWL
* @Time 2017年8月17日 上午10:49:48  
*
*/

public class FileUtil {
	
	@Autowired
    private static FtpService ftpService;
	
	public static String uploadImage(MultipartFile file){
		
		String picUrl = "ftp://192.168.221.1/data/";
		String picName =UUID.randomUUID().toString();
		String oriName = file.getOriginalFilename();
		String extndName = oriName.substring(oriName.lastIndexOf("."));
		try{
			file.transferTo(new File("D:/image/" + picName + extndName));
			//file.transferTo(new java.io.File("E://ftpsever/data" +oriName));

//			FileInputStream in=new FileInputStream(new File(logoRealPathDir));
//			ftpService.uploadFile(oriName, in);
		}catch(IllegalStateException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		String imagePath = picUrl + oriName;
		
		return imagePath;
		
		
		
		
		
	}
}
