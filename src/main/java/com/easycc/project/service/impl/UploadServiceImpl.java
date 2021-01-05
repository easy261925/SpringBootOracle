package com.easycc.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easycc.project.common.Exception.CustomException;
import com.easycc.project.common.utils.ResponseResult;
import com.easycc.project.common.utils.ResultCode;
import com.easycc.project.entity.ProjectFile;
import com.easycc.project.entity.SysUser;
import com.easycc.project.service.AuthService;
import com.easycc.project.service.ProjectFileService;
import com.easycc.project.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author cc
 * @date 2021-01-05-下午1:09
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private Environment env;

    @Autowired
    private AuthService authService;

    @Autowired
    private ProjectFileService projectFileService;

    @Override
    //上传文件保存到本地
    public ProjectFile saveFile(MultipartFile file, String dirName, HttpServletRequest request) throws UnsupportedEncodingException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取本地文件路径
        String filePath = env.getProperty("local.uploadPath");
        // 获取文件路径
        String urlName = URLEncoder.encode(fileName, "utf-8");
        // 生成指定文件夹
        String dirUrl = filePath + dirName;
        try {
            // 创建文件夹
            File f = new File(dirUrl);
            f.mkdirs();
            //将文件保存到static文件夹里
            file.transferTo(new File(dirUrl + "/" + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProjectFile projectFile = new ProjectFile();
        projectFile.setUrl("http://" + env.getProperty("local.ip") + ":" + env.getProperty("server.port") + "/static/files/" + dirName + urlName);
        projectFile.setFilePath(dirUrl + fileName);
        projectFile.setFilename(URLDecoder.decode(fileName, "utf-8"));
        return projectFile;
    }

    @Override
    public boolean deleteFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                if (!f.delete()) {
                    System.out.println(f.getAbsolutePath() + " delete error!");
                    return false;
                }
            } else {
                if (!this.deleteFolder(f.getAbsolutePath())) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    @Override
    public boolean updateEsRemoteExtDict(HttpServletRequest request, List<MultipartFile> attachments, String[] filesId) {
        SysUser userInfo = authService.getUserInfo(request);

        if (null != userInfo) {
            String filePath = env.getProperty("local.uploadPath") + "ESRemote";
            if (null != filesId && filesId.length > 0) {
                // 删除本地文件
                QueryWrapper<ProjectFile> query = new QueryWrapper<>();
                query.notIn("ID", filesId);
                List<ProjectFile> list = projectFileService.list(query);
                if (list.size() > 0) {
                    list.forEach(item -> {
                        this.deleteFolder(filePath + "/" + item.getFilename());
                    });
                }
                // 删除数据库中数据
                QueryWrapper<ProjectFile> projectFileQueryWrapper = new QueryWrapper<>();
                projectFileQueryWrapper.notIn("ID", filesId);
                projectFileService.remove(projectFileQueryWrapper);
            }
            // 添加附件
            if (attachments.size() > 0) {
                attachments.forEach(item -> {
                    // 获取指定文件夹
                    String dirUrl = "ESRemote/";
                    if ("remoteExtDic.txt".equals(item.getOriginalFilename()) || "remoteBanDic.txt".equals(item.getOriginalFilename())) {
                        try {
                            ProjectFile projectFile = this.saveFile(item, dirUrl, request);
                            projectFile.setType("ES");
                            projectFile.setHandlerId(userInfo.getId());
                            QueryWrapper<ProjectFile> projectFileQueryWrapper = new QueryWrapper<>();
                            projectFileQueryWrapper.eq("FILENAME", item.getOriginalFilename());
                            projectFileService.remove(projectFileQueryWrapper);
                            projectFileService.save(projectFile);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            throw new CustomException(ResultCode.FAILED, "上传文件出错");
                        }
                    }
                });

            }
            // 全部删除
            if (null == filesId && attachments.size() == 0) {
                QueryWrapper<ProjectFile> projectFileQueryWrapper = new QueryWrapper<>();
                projectFileQueryWrapper.eq("DELETED", 0);
                projectFileService.remove(projectFileQueryWrapper);

                this.deleteFolder(filePath);
            }
            return true;
        }
        throw new CustomException(ResultCode.FAILED, "上传失败");
    }

    @Override
    public ResponseResult getEsRemoteDict() {
        QueryWrapper<ProjectFile> projectFileQueryWrapper = new QueryWrapper<>();
        projectFileQueryWrapper.eq("FILENAME", "remoteExtDic.txt")
                .or()
                .eq("FILENAME", "remoteBanDic.txt");
        List<ProjectFile> list = projectFileService.list(projectFileQueryWrapper);
        return ResponseResult.ok().data("data", list);
    }
}
