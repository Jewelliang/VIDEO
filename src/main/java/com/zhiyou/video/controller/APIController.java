package com.zhiyou.video.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou.video.mapper.SysUserMapper;
import com.zhiyou.video.model.JsonResult;
import com.zhiyou.video.model.SysUser;
import com.zhiyou.video.util.Global;
import com.zhiyou.video.util.JedisUtils;
import com.zhiyou.video.util.MD5Utils;
import com.zhiyou.video.util.ParamUtil;
import com.zhiyou.video.util.RedisTokenManager;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;



@Controller
@RequestMapping("/sign")
public class APIController {
	private SysUserMapper sysUserMapper;

	/*
	 * 一定要确保此接口安全，https RSA （改天写个更完整的把这个加上）
	 * RSA 有公钥 私钥 通过公钥加密，通过私钥解密
	 * 
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(String username, String password) {
		JsonResult jsonResult = new JsonResult();
		// 1 判断用户用户名 密码是否正确，这里默认用户能验证通过
		SysUser user = sysUserMapper.getUserByName(username);
		if (user == null) {
			jsonResult.setErrorCode("1001");
			jsonResult.setErrorMessage("用户名不存在");
		} else {
			
			String algorithmName = "md5";
			int hashIterations = Global.hashIterations;
			SimpleHash hash = new SimpleHash(algorithmName, password, username + user.getSalt(), hashIterations);
			String encodedPassword = hash.toHex();
			if (encodedPassword.equals(user.getPassword())) {
				// 用户登录成功，生成token ，并把username 和 token 存入redis 设置过期时间2h
				// 生成token 然后以token 为键，userId为值存入redis，并设置过期时间 
				String token = RedisTokenManager.createToken(user.getId());
				jsonResult.setErrorCode("0");
				jsonResult.setErrorMessage("登录成功");
				jsonResult.setContent(token);
				return jsonResult;
			} else {
				// 密码错误
				jsonResult.setErrorCode("1002");
				jsonResult.setErrorMessage("密码错误");
			}

		}
		return jsonResult;
	}
	
	
	// 通过URL 签名  签名验证
	// 如果再访问其他接口 app 端 会把token 传过来，然后我们根据token 把这个用户
	// userid查出来
	// 跨域 
	@RequestMapping("/getMoney")
	@ResponseBody
	public JsonResult getUserMoney(String appKey, String timestamp, String a, String b, String token, String sign) {
		// 1分钟
		// 1 如果没有 token sign timestamp 参数直接报错
		// 2判断服务器接到请求的时间和参数中的时间戳是否相差很长一段时间（时间自定义如半个小时），如果超过则说明该
		// url已经过期（如果url被盗，他改变了时间戳，但是会导致sign签名不相等）。
		// 3 没过期继续
		String secuty = JedisUtils.get(appKey);
		if (secuty == null) {
			// 从数据库中取,如果没去到appKey 非法，返回错误,这里默认能取到，直接赋值了
			secuty = "qwertyudsffg@#%&";
			// 放入redis 缓存
			JedisUtils.setSetAdd(appKey, secuty);
		}
		Map<String, String> map = new HashMap<>();
		map.put("appKey", appKey);
		map.put("timestamp", timestamp);
		map.put("a", a);
		map.put("b", b);
		map.put("token", token);
		String paramStr = ParamUtil.formatUrlMap(map, true, true);
		String firstSign = secuty + paramStr;
		String realSign = MD5Utils.getMd5Simple(firstSign);
		if (!realSign.equals(sign)) {
			// 验证失败
		} else {
			// 验证成功，根据token 获取userID
			Long userId = RedisTokenManager.getUserId(token);
			if (userId == null) {
				// token 过期 或者不错在 用户需要重新登录

			} else {
				// 继续
			}

		}
		return null;
	}

    @CrossOrigin
	@RequestMapping(value="/test.action")
	@ResponseBody
	public JsonResult getUserMoney() {
	JsonResult result=new JsonResult();
	
	
	System.out.println("hhhhhhh");
	
	return result;
	
     }
}
