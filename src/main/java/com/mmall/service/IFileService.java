package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by BGL on 2017/6/27.
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
