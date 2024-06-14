package com.myspringweb.utils;


import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOSSUtils {
    @Autowired
    AliyunOSSProperties aliyunOSSProperties;
    public String upload(MultipartFile file) throws Exception{

        String endpoint = aliyunOSSProperties.getEndpoint();
        String bucketName = aliyunOSSProperties.getBucketname();
        // 从环境变量中获取访问凭证。运行本代码前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        //上传文件输入流
        InputStream inputStream = file.getInputStream();

        //避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //创建ClientBuilderConfiguration实例，根据实际情况修改默认参数
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();

        //设置支持CNAME，用于将自定义域名绑定到目标bucket
        conf.setSupportCname(true);

        // 上传文件，创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider, conf);
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        //String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + endpoint.split("//")[1] + "/" + fileName;

        //关闭ossClient
        ossClient.shutdown();
        //返回上传文件的路径
        return url;

    }


}
