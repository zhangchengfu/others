package com.avc.shiro.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GloableController {

    @RequestMapping("/oauth2Failure")
    public String fail(){
        return "oauth2Failure";
    }
}
