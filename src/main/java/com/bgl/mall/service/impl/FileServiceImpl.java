package com.bgl.mall.service.impl;

import com.google.common.collect.Lists;
import com.bgl.mall.service.IFileService;
import com.bgl.mall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by BGL on 2017/6/27.
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService{

    private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();
        //拿到上传文件的扩展名，重新生成文件名避免重复
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件,上传的文件名{},上传的文件路径{},新的文件名{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path, uploadFileName);
        try {
            //上传文件成功
            file.transferTo(targetFile);
            //上传文件到FTP服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //上传完成，删除upload下面的文件
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常", e);
        }

        return targetFile.getName();
    }
}
