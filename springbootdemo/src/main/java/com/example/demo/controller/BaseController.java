package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller基类,共用函数
 * 
 * @ClassName: BaseController
 * @Description: TODO
 * @date 2015年2月4日 下午4:58:39
 */
public class BaseController {
	
	public Logger logger = LoggerFactory.getLogger(getClass());

	private static final String JSON_TYPE = "application/json;charset=utf-8";
	
	public static final String VCODE_KEY = "_vcode";

	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void writer(Object obj, String jsoncallback, HttpServletResponse response,HttpServletRequest request) throws Exception {
		if(request.getHeader("Accept-Encoding") != null  && request.getHeader("Accept-Encoding").toLowerCase().indexOf("gzip") > -1){
			if (StringUtils.isEmpty(jsoncallback)) {
				writeZIP(obj, response);
				return;
			}
			writeJSONPZIP(obj, jsoncallback, response);
		}else{
			if (StringUtils.isEmpty(jsoncallback)) {
				writeJSON(obj, response);
				return;
			}
			writeJSONP(obj, jsoncallback, response);
		}
	
	}

	public void writeJSONP(Object obj, String jsoncallback, HttpServletResponse response) throws Exception {
		writer(jsoncallback + "(" + objectMapper.writeValueAsString(obj) + ")", response, JSON_TYPE);
	}

	private void writer(String string, HttpServletResponse response, String type) throws IOException, Exception {
		response.setContentType(type);
		PrintWriter writer = response.getWriter();
		writer.print(string);
		writer.flush();
		writer.close();
	}
	
	public void writeJ(Object obj, String jsoncallback, HttpServletResponse response) throws Exception {
		writer(jsoncallback + "(" + objectMapper.writeValueAsString(obj) + ")", response, JSON_TYPE);
	}
	
	/***GZIP start***/
	public void writeJSONPZIP(Object obj, String jsoncallback, HttpServletResponse response) throws Exception {
		writerZip(jsoncallback + "(" + objectMapper.writeValueAsString(obj) + ")", response, JSON_TYPE);
	}
	
	public void writeZIP(Object obj, HttpServletResponse response) throws Exception {
		writerZip(objectMapper.writeValueAsString(obj), response, JSON_TYPE);
	}
	
	private void writerZip(String string, HttpServletResponse response, String type) throws IOException, Exception {
		response.setContentType(type);
//		response.setHeader("Content-encoding", "gzip, deflate");
		PrintWriter writer = response.getWriter();
		writer.print(string);
		writer.flush();
		writer.close();
	}
	/***GZIP end***/
	public void writer(byte[] data, HttpServletResponse response) throws IOException, Exception {
		response.setContentType("application/x-download");
		response.setContentLength(data.length);
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(data);
		outputStream.flush();
		outputStream.close();
	}

	public void writeJSON(Object obj, HttpServletResponse response) throws Exception {
		writer(objectMapper.writeValueAsString(obj), response, JSON_TYPE);
	}

	protected String obtainUserkeyParameter(HttpServletRequest request) {
		return request.getParameter("userKey");
	}

	protected String obtainClientActionParameter(HttpServletRequest request) {
		String action=request.getParameter("action");
		return action;
	}

	@ExceptionHandler
	public void exp(HttpServletRequest request, HttpServletResponse response, Exception e) {
		logger.error(request.getRequestURI() + " request error, params: " + JSON.toJSONString(request.getParameterMap()), e);
	}
	
	protected Long obtainClientPageIdParameter(HttpServletRequest request) {
		String pageId=request.getParameter("pageId");
		if(StringUtils.isNumeric(pageId)){
			return Long.valueOf(pageId);
		}
		return 0l;
	}
	
	protected int obtainClientPageNoParameter(HttpServletRequest request){
		String pageNo=request.getParameter("pageNo");
		if(StringUtils.isNumeric(pageNo)){
			return Integer.valueOf(pageNo);
		}
		return 0;
	}
}
