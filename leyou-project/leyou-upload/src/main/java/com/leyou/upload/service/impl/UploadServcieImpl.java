package com.leyou.upload.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author guo
 */
@Slf4j
//@EnableConfigurationProperties(UploadProperties.class)
@Service("uploadServcie")
public class UploadServcieImpl implements UploadService {

    private static final List<String> ALLOW_TYPES = Arrays.asList("image/jpeg","image/png","image/bmp");
    private static final Logger LOGGER= LoggerFactory.getLogger(UploadServcieImpl.class);
    @Autowired
    private FastFileStorageClient storageClient;
    @Override
    public String uploadImage(MultipartFile file)  {
        //校验文件类型  是否是图片  定义白名单
            //后缀  原始文件名后缀  最后一个点
        String originalFilename = file.getOriginalFilename();
//        StringUtils.substringAfterLast()
        //不同的文件类型 请求中不同的content类型
        String contentType = file.getContentType();
        if(!ALLOW_TYPES.contains(contentType)){
            LOGGER.info("文件类型不合法：{}",originalFilename);
          return null;
        }
        //校验文件内容
        //校验文件类型
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image==null){
                LOGGER.info("文件内容不合法：{}",originalFilename);
                return null;
            }
            //保存到文件的服务器
//            file.transferTo(new File("D:\\project\\images\\"+originalFilename));
            //上传并保存图片，参数：1-上传的文件流 2-文件的大小 3-文件的后缀 4-可以不管他
            String ext = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            //返回url
            //return "http://image.leyou.com/"+originalFilename;
            return "http://image.leyou.com/"+storePath.getFullPath();
        } catch (IOException e) {
            e.printStackTrace();
           LOGGER.info("服务器内部错误：{}"+originalFilename);
        }
      /*//域名加文件名 访问图片
        return "http://image.leyou.com/"+originalFilename;*/
        return null;
    }
}
