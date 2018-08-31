package com.example.demo.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.example.demo.enums.ErrorType;

/**
 * 数据返回模型

 */
public class BaseResult implements Serializable {

    private static final long serialVersionUID = -2157268086858735413L;

    /**此枚举主要是发生错误时候，输出使用，目前主要是ErrorType类型，也可以自己扩展定义**/
    private ErrorType errorType = ErrorType.SUCCESS;

    private Map<String, Object> resultMap = new HashMap<String, Object>();
    
    private String message;
    
    public BaseResult() {

    }

    public BaseResult(ErrorType errorType) {
        this.errorType = errorType;
        this.message=errorType.getName();
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        if (errorType == null) {
            this.errorType = ErrorType.UNKNOWN_ERROR;
        }
        this.errorType = errorType;
        this.message=errorType.getName();
    }
    
    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public boolean isSuccess() {
        if (errorType != null && errorType.getIndex() == ErrorType.SUCCESS.getIndex()) {
            return true;
        }else{
            return false;
        }
    }

    
    public int getStatus(){
    	return errorType.getIndex();
    }
    
    public String getMessage(){
    	return this.message == null ? errorType.getName() : this.message;
    }
    
    public void setMessage(String message) {
		this.message = message;
	}

	@SuppressWarnings("unchecked")
    public <SubClass extends BaseResult> SubClass addToMap(String key, Object value) {
        this.resultMap.put(key, value);
        return (SubClass) this;
    }

    @SuppressWarnings("unchecked")
    public <SubClass extends BaseResult> SubClass addToMap(Map<String, Object> map) {
        if (!CollectionUtils.isEmpty(map)) {
            for (Entry<String, Object> entry : map.entrySet()) {
                this.resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        return (SubClass) this;
    }

    @Override
    public String toString() {
        this.addToMap("status", errorType.getIndex()).addToMap("errorMsg", errorType.getName()).addToMap("message", this.getMessage());
        return JSON.toJSONString(this.getResultMap());
    }
    
    public static void main(String[] args) {
		BaseResult result = new BaseResult();
		System.out.println(result);
	}

}
