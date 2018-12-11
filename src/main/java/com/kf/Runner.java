package com.kf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by keefe
 * 2018-12-11
 */
@Controller
@RequestMapping("blog/")
public class Runner {

    private final Logger logger = LoggerFactory.getLogger(Runner.class);

    @GetMapping("update")
    @ResponseBody
    public String update() {
        logger.info("start update blog content.");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Runner.class.getClassLoader().getResourceAsStream("update.sh")))) {
//            StringBuilder cmd = new StringBuilder();
//            String s;
//            while ((s = reader.readLine()) != null) {
//                cmd.append(s).append(';');
//            }
//            Runtime.getRuntime().exec(cmd.toString());
            ProcessBuilder pb = new ProcessBuilder("bin/bash", "cd", "/root/blog", "git pull");
            pb.start();
//            Runtime.getRuntime().exec("#!/bin/bash;cd /root/blog;git pull");
        } catch (Exception e) {
            logger.error("", e);
        }
        logger.info("end update blog content.");
        return "OK";
    }
}