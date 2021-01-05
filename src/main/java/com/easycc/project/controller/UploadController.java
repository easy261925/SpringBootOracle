package com.easycc.project.controller;

import com.easycc.project.common.utils.ResponseResult;
import com.easycc.project.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cc
 * @date 2021-01-05-下午1:10
 */
@RestController
@RequestMapping("/upload")
@Api(tags = "上传文件")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/connect")
    @ApiOperation("上传文件链接地址")
    public ResponseResult connect() {
        return ResponseResult.ok();
    }

    @PostMapping("/updateEsRemoteDict")
    @ApiOperation("更新ES远程拓展字典")
    public ResponseResult updateEsRemoteExtDict(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> attachments = multipartRequest.getFiles("attachments");
        String[] filesId = request.getParameterValues("attachments");
        uploadService.updateEsRemoteExtDict(request, attachments, filesId);
        return ResponseResult.ok();
    }

    @GetMapping("/getEsRemoteDict")
    @ApiOperation("获取ES远程拓展字典")
    public ResponseResult getEsRemoteDict(){
        return uploadService.getEsRemoteDict();
    }
}
