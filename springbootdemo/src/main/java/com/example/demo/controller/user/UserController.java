package com.example.demo.controller.user;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.redis.RedisOperator;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RedisOperator redisOpt;
	
	@RequestMapping("getOne")
	//@RequiresPermissions(value="search")
	public User getUser() {
		return userService.findUserById(1);
	}
	
	@RequestMapping("add2Rds")
	public boolean add2Rds() {
		return redisOpt.set("user",userService.findUserById(1),10000L);
	}
	
	@RequestMapping("/getFromRds")
	@ResponseBody
	public User getFromRds() {
		User u = (User) redisOpt.get("user");
		System.out.println("Email==="+u.getEmail());
		return (User) redisOpt.get("user");
	}
	
	@RequestMapping("/putAllUser")
	public boolean putMoreUser2Rds() {
		User u1 = userService.findUserById(1);
		User u2 = userService.findUserById(11);
		User u3 = userService.findUserById(12);
		Map<String, User> map = new LinkedHashMap<>();
		map.put("u1", u1);
		map.put("u2", u2);
		map.put("u3", u3);
		redisOpt.hashPutAll("users", map);
		return true;
	}
}
