package com.easycc.project.controller.ElasticSearch;

import com.easycc.project.common.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * @author cc
 * @date 2021-01-04-上午9:40
 */

@RestController
@RequestMapping("/elasticsearch")
@Api(tags = "ElasticSearch")
public class ElasticSearchController {

    @PostMapping("/updateEsRemoteExtDict")
    @ApiOperation("更新ES远程拓展字典")
    public ResponseResult updateEsRemoteExtDict(HttpRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> attachments = multipartRequest.getFiles("attachments");
        return ResponseResult.ok();
    }
}
