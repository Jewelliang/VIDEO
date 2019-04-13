package com.zhiyou.video.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import com.zhiyou.video.common.BeanValidators;
import com.zhiyou.video.common.DateUtil;
public class BaseController  {

	
	@Autowired
	protected Validator validator;
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
		/*
		 任何转换器都不配置  按格式输入(yyyy-MM-dd,yyyy-MM-dd HH:mm:ss)不会报错 否则400
		
		 转换器配置的两种方式掌握

		 如果配置了参数校验springmvc 参数绑定如果出现异常就不会报400错误了 但是不能正常赋值
		*/
		
		
		
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtil.stringToDateyy(text));

			}

		});

		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(new Timestamp(DateUtil.stringToDate(text).getTime()));

			}

		});

	}

	
	/**
	 * 异常处理
	 * @return
	 */
	@ExceptionHandler({BindException.class,IllegalStateException.class})
	public String bindException(){
		
		return "/error/400";
			
	}
	/*
	 * 权限异常
	 * 业务异常
	 * 都可以在这处理
	 */

	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try{
			
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(model, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}

	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}
	
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("message", sb.toString());
	}
	
	
	

}
