package com.example.demo.exception;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.BaseResult;
import com.example.demo.enums.ErrorType;

@ControllerAdvice
public class MyExceptionHandle {
	//声明要捕获的异常
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BaseResult defultExcepitonHandler(Exception ex) {
		BaseResult baseResult = new BaseResult();
		if (ex instanceof UnauthenticatedException) {
			baseResult.setErrorType(ErrorType.UN_LOGIN);
		} else if (ex instanceof UnauthorizedException) {
			baseResult.setErrorType(ErrorType.UNAUTHORIZED);
		}else if(ex instanceof AccountException) {
			baseResult.setErrorType(ErrorType.NOT_ACCOUNT);
		}else if(ex instanceof DisabledAccountException) {
			baseResult.setErrorType(ErrorType.DISABLED);
		}else if(ex instanceof RepateLoginException){
			baseResult.setErrorType(ErrorType.REAPET_LOGIN);
		}else {
			baseResult.setErrorType(ErrorType.UNKNOWN_ERROR);
		}
		return baseResult;
	}

}
