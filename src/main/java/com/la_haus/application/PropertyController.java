package com.la_haus.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PropertyController {

    @GetMapping("/property")
    @ResponseBody
    public String hello(){
        return "Hello";
    }
}
