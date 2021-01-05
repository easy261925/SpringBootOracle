package com.easycc.project.service;

import com.easycc.project.common.utils.ResponseResult;
import com.easycc.project.entity.ProjectFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UploadService {

    ProjectFile saveFile(MultipartFile file, String path, HttpServletRequest request) throws UnsupportedEncodingException;

    boolean deleteFolder(String path);

    boolean updateEsRemoteExtDict(HttpServletRequest request, List<MultipartFile> attachments, String[] filesId);

    ResponseResult getEsRemoteDict();
}
