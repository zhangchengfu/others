package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.DisabledAccountException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResult;
import com.example.demo.enums.ErrorType;
import com.example.demo.exception.RepateLoginException;
import com.example.demo.model.User;
import com.example.demo.shiro.TokenManager;
import com.example.demo.shiro.vcode.Captcha;
import com.example.demo.shiro.vcode.GifCaptcha;

@RestController  //表示该Controller的所有方法的值均以Json格式返回
@RequestMapping("/g")
public class GloabController extends BaseController{
	
	@RequestMapping("/login")
	public BaseResult login(HttpServletRequest request, HttpServletResponse response, String name,String password,Boolean rememberMe){
	//public BaseResult login(User user, Boolean rememberMe) {
		HttpSession session = request.getSession();  
		System.out.println("从Session获取的验证码：："+session.getAttribute(VCODE_KEY));
		BaseResult baseResult = new BaseResult();
		User user = new User();
		user.setEmail(name);
		user.setPswd(password);
		try {
			user = TokenManager.login(user, rememberMe);
		} catch (DisabledAccountException e) {
			baseResult.setErrorType(ErrorType.DISABLED);
		}catch(RepateLoginException e) {
			baseResult.setErrorType(ErrorType.REAPET_LOGIN);
		}catch (Exception e) {
			baseResult.setErrorType(ErrorType.NOT_ACCOUNT);
		}
		session.removeAttribute(VCODE_KEY);
		return baseResult;
	}
	@RequestMapping("/logout")
	public BaseResult logout() {
		BaseResult result = new BaseResult();
		TokenManager.logout();
		result.setErrorType(ErrorType.SUCCESS);
		result.setMessage("退出成功");
		return result;
	}
	
	/**
	 * 获取验证码（Gif版本）
	 * @param response
	 */
	@RequestMapping(value="/getGifCode",method=RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,HttpServletRequest request){
		try {
			//在Response输出响应之前创建Session，否则会报异常
			HttpSession session = request.getSession(true);  
			response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        response.setContentType("image/gif");  
	        /**
	         * gif格式动画验证码
	         * 宽，高，位数。
	         */
	        Captcha captcha = new GifCaptcha(146,33,4);
	        //输出
	        captcha.out(response.getOutputStream());
	        //存入Session
	        session.setAttribute("_code",captcha.text().toLowerCase());  
		} catch (Exception e) {
			System.out.println("获取验证码异常："+e.getMessage());
		}
	}

	@RequestMapping("/unauthorized")
	public BaseResult unauthorized() {
		BaseResult result = new BaseResult(ErrorType.UNAUTHORIZED);
		return result;
	}
	
	@RequestMapping("/unLogin")
	public String unLogin(){
		return "用户未登录";
	}
}
