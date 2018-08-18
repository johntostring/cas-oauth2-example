package com.millinch.casclient.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This guy is busy, nothing left
 *
 * @author John Zhang
 */
@RestController
public class DemoApi {

    @GetMapping("/ping")
    public String ping() {
        return "pang";
    }

    @GetMapping("/foo")
    public String foo() {
        return "bar";
    }
}
