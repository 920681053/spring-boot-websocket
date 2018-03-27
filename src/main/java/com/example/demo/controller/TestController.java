package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YuanJian
 * @data 18-3-27下午3:55
 */
@Controller
public class TestController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

}
