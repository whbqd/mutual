package com.dy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: ZT
 * Date: 2020/5/6 12:47
 * Version 1.0
 */
@RestController
public class MyController {
    @RequestMapping("/test1")
    public String test1() {
        return "test1";
    }
}
