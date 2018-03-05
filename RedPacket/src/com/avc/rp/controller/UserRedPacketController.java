package com.avc.rp.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avc.rp.service.UserRedPacketService;

@Controller
@RequestMapping("/userRedPacket")
public class UserRedPacketController {
	
	@Autowired
	private UserRedPacketService userRedPacketService=null;
	
	@ResponseBody
	@RequestMapping("/grapRedPacket.do")
	public Map<String, Object> grapRedPacket(Integer redPacketId, Integer userId){
		//抢红包
		int result= userRedPacketService.grapRedPacket(redPacketId, userId);
		Map<String,Object> retMap = new HashMap<>();
		boolean flag = result>0;
		retMap.put("success", flag);
		retMap.put("message", flag?"抢红包成功":"抢红包失败");
		
		return retMap;
	}
	
	/**
	 * 使用redis进行操作
	 * */
	@ResponseBody
	@RequestMapping("/grapRedPacketByRedis")
	public Map<String, Object> grapRedPacketByRedis(Integer redPacketId, Integer userId){
		//抢红包
		Long result= userRedPacketService.grapRedPacketByRedis(redPacketId, userId);
		Map<String,Object> retMap = new HashMap<>();
		boolean flag = result>0;
		retMap.put("success", flag);
		retMap.put("message", flag?"抢红包成功":"抢红包失败");
		
		return retMap;
	}
	
	@ResponseBody
	@RequestMapping("/grapRedPacketForVersion.do")
	public Map<String, Object> grapRedPacketForVersion(Integer redPacketId, Integer userId){
		//抢红包
		int result= userRedPacketService.grapRedPacketForVersion(redPacketId, userId);
		Map<String,Object> retMap = new HashMap<>();
		boolean flag = result>0;
		retMap.put("success", flag);
		retMap.put("message", flag?"抢红包成功":"抢红包失败");
		
		return retMap;
	}
}
