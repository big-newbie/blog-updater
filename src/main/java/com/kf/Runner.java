package com.kf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by keefe
 * 2018-12-11
 */
@Controller
@RequestMapping("blog/")
public class Runner {

    private final Logger logger = LoggerFactory.getLogger(Runner.class);
    private final ConcurrentMap<LocalDate, AtomicInteger> counter = new ConcurrentHashMap<>();

    @PostMapping("update")
    @ResponseBody
    public String update() {
        AtomicInteger atomicInteger = counter.computeIfAbsent(LocalDate.now(), k -> new AtomicInteger());
        logger.info(String.valueOf(atomicInteger.get()));
        if (atomicInteger.incrementAndGet() > 20) {
            return "???";
        }
        logger.info("start update blog content.");
        try {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "cd", "/root/blog", "git pull");
            Process process = pb.start();
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s).append('\n');
            }
            logger.info(sb.toString());
        } catch (Exception e) {
            logger.error("", e);
        }
        logger.info("end update blog content.");
        return "OK";
    }
}