package com.myspringweb.controller;

import com.myspringweb.pojo.Result;
import com.myspringweb.utils.AliOSSUtils;
import com.myspringweb.utils.AliyunOSSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("文件上传，文件名：{}", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        return Result.success(url);
    }
}
