package com.wind.springsession.util;

import com.wind.springsession.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    public static String multipartFileUpload(MultipartFile file, String siteFileDir, String fileDir, long fileMaxSize) throws IOException, CustomException {

        long size = file.getSize();
        logger.info("size: {}", size);

        if(size > fileMaxSize){
            throw new CustomException("9999", "file too large");
        }

        File fileDirectory = new File(siteFileDir + fileDir);
        if(!fileDirectory.exists() && !fileDirectory.isDirectory()){
            fileDirectory.mkdirs();
        }
        logger.info("fileDirectory: {}", fileDirectory);

        String currentTime = String.valueOf(System.nanoTime());
        logger.info("currentTime: {}", currentTime);

        String originalFilename = file.getOriginalFilename();
        logger.info("originalFilename: {}", originalFilename);
        //logger.info(file.getName());

        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        logger.info("fileType: {}", fileType);

        String fileName = currentTime + fileType;

        String filePath = fileDirectory + File.separator + fileName;

        logger.info("filePath: {}", filePath);

        file.transferTo(new File(filePath));

        return ServerConfigUtil.protocol + ServerConfigUtil.protocolSeparator
                + ServerConfigUtil.ip + fileDir + File.separator + fileName;
    }

}
