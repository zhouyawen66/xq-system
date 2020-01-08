package com.cnaidun.police.util.bos;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.*;
import com.cnaidun.police.util.RandomUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BOSUtil
 * @Descriprion TODO
 * @Author dongyin
 * @Date 2019/10/23 09:45
 **/
public class BOSUtil {
    private static BosClient bosClient;
    private static String bucketName = "sanai-cloud";
    private static String endpoint = "https://su.bcebos.com";
    /**
     * 安全认证的Access Key ID
     */
    public static final String ACCESS_KEY_ID = "8cd6d86143dd4b47ba47d9c9343901e4";
    /**
     * 安全认证的Secret Access Key
     */
    public static final String SECRET_ACCESS_KEY = "90cf7d3a69014ffca17bceef055e878c";

    static {
        BosClientConfiguration config = new BosClientConfiguration();
        config.setMaxConnections(10);
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(endpoint);
        bosClient = new BosClient(config);
    }

    public static void main(String[] args) {
        File file = new File("c://xml/6750907163561037170.MP4");
        String name = file.getName();
        String ext = name.substring(name.lastIndexOf(".") + 1);
        String newFileName = RandomUtil.getRandomNum() + "." + ext;
        String objectKey = ObjectManage.CERTIFICATION.getValue() + newFileName;
        BOSUtil.uploadFileToBos(file, objectKey);
        System.out.println(endpoint + "/" +bucketName + "/" + objectKey);
    }

    /**
     * 百度bos以file形式上传文件（不超过5GB）
     *
     * @param file      要上传的文件
     * @param objectKey 文件路径/文件名（可以用“/”来创建多层文件夹）
     * @return 上传成功后的tag
     */
    public static PutObjectResponse uploadFileToBos(File file, String objectKey) {
        return bosClient.putObject(bucketName, objectKey, file);
    }

    /**
     * 以数据流形式上传Object（不超过5GB）
     *
     * @param multipartFile 要上传的数据流
     * @param prefixObjectKey   certification/
     * @return 上传成功后的URL
     */
    public static String uploadInputStreamToBos(MultipartFile multipartFile,String prefixObjectKey) {
        String name = multipartFile.getOriginalFilename();
        String ext = name.substring(name.lastIndexOf(".") + 1);
        String newFileName = RandomUtil.getRandomNum() + "." + ext;
        String objectKey = prefixObjectKey + newFileName;
        try {
            bosClient.putObject(bucketName, objectKey, multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return endpoint + "/" +bucketName + "/" + objectKey;
    }

    /**
     * 以二进制串上传Object（不超过5GB）
     *
     * @param file      要上传的byte
     * @param objectKey 文件路径/文件名（可以用“/”来创建多层文件夹）
     * @return 上传成功后的tag
     */
    public static PutObjectResponse uploadByteToBos(byte[] file, String objectKey) {
        return bosClient.putObject(bucketName, objectKey, file);
    }


    /**
     * 以字符串上传Object（不超过5GB）
     *
     * @param file      要上传的string
     * @param objectKey 文件路径/文件名（可以用“/”来创建多层文件夹）
     * @return 上传成功后的tag
     */
    public static PutObjectResponse uploadStringToBos(String file, String objectKey) {
        return bosClient.putObject(bucketName, objectKey, file);
    }

    /**
     * 删除已经上传的Object
     *
     * @param objectKey 文件路径/文件名（可以用“/”来创建多层文件夹）
     * @return 上传成功后的tag
     */
    public static void deleteObject(String objectKey) {
        bosClient.deleteObject(bucketName, objectKey);
    }


    /**
     * 批量删除Object(以Json格式的字符串)
     * 支持一次请求内最多删除1000个Object。
     * 消息体（body）不超过2M。
     * 返回的消息体中只包含删除过程中出错的Object结果；如果所有Object都删除都成功的话，则没有消息体。
     *
     * @param jsonObjectKeys 文件路径/文件名（可以用“/”来创建多层文件夹）        String jsonObjectKeys = "{\"objects\": [" + "{\"key\": \"token1.h\"}," + "{\"key\": \"token2.h\"}" + "]}";
     * @return 返回的消息体中只包含删除过程中出错的Object结果；如果所有Object都删除都成功的话，则没有消息体。
     */
    public static DeleteMultipleObjectsResponse deleteObjectListUseJson(String jsonObjectKeys) {
        DeleteMultipleObjectsRequest request = new DeleteMultipleObjectsRequest();
        request.setBucketName(bucketName);
        request.setJsonDeleteObjects(jsonObjectKeys);
        return bosClient.deleteMultipleObjects(request);

    }

    /**
     * 批量删除Object(用户只需指定指定参数即可)
     * 支持一次请求内最多删除1000个Object。
     * 消息体（body）不超过2M。
     *
     * <p>
     * List<String> objectKeys = new ArrayList<String>();
     * objectKeys.add("object1");
     * objectKeys.add("object2");
     *
     * @param objectKeys 文件路径/文件名（可以用“/”来创建多层文件夹）
     * @return 返回的消息体中只包含删除过程中出错的Object结果；如果所有Object都删除都成功的话，则没有消息体。
     */
    public static DeleteMultipleObjectsResponse deleteObjectList(List<String> objectKeys) {
        DeleteMultipleObjectsRequest request = new DeleteMultipleObjectsRequest();
        request.setBucketName(bucketName);
        request.setObjectKeys(objectKeys);
        return bosClient.deleteMultipleObjects(request);
    }

    /**
     * 获取一个分块上传事件-使用Multipart 上传文件
     *
     * @param objectKey 文件路径/文件名（可以用“/”来创建多层文件夹）
     * @return 分块上传事件
     */
    public static InitiateMultipartUploadResponse getMultipartUploadID(String objectKey) {
        InitiateMultipartUploadRequest initiateMultipartUploadRequest =
                new InitiateMultipartUploadRequest(bucketName, objectKey);
        return bosClient.initiateMultipartUpload(initiateMultipartUploadRequest);
    }


    /**
     * 使用Multipart 上传文件 应用场景
     * 1.需要支持断点上传。
     * 2.上传超过5GB大小的文件。
     * 3.网络条件较差，和BOS的服务器之间的连接经常断开。
     * 4.需要流式地上传文件。
     * 5.上传文件之前，无法确定上传文件的大小。
     *
     * @param file
     * @param objectKey 文件路径/文件名（可以用“/”来创建多层文件夹）
     */
    public static void uploadMultipartToBos(File file, String objectKey) {
        InitiateMultipartUploadRequest initiateMultipartUploadRequest =
                new InitiateMultipartUploadRequest(bucketName, objectKey);
        InitiateMultipartUploadResponse initiateMultipartUploadResponse = bosClient.initiateMultipartUpload(initiateMultipartUploadRequest);
        // 设置每块为 5MB
        final long partSize = 1024 * 1024 * 5L;
        // 计算分块数目
        int partCount = (int) (file.length() / partSize);
        if (file.length() % partSize != 0) {
            partCount++;
        }
        // 新建一个List保存每个分块上传后的ETag和PartNumber
        List<PartETag> partETags = new ArrayList<PartETag>();
        for (int i = 0; i < partCount; i++) {
            // 获取文件流
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);

                // 跳到每个分块的开头
                long skipBytes = partSize * i;
                fis.skip(skipBytes);

                // 计算每个分块的大小
                long size = partSize < file.length() - skipBytes ?
                        partSize : file.length() - skipBytes;

                // 创建UploadPartRequest，上传分块
                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(objectKey);
                uploadPartRequest.setUploadId(initiateMultipartUploadResponse.getUploadId());
                uploadPartRequest.setInputStream(fis);
                uploadPartRequest.setPartSize(size);
                uploadPartRequest.setPartNumber(i + 1);
                UploadPartResponse uploadPartResponse = bosClient.uploadPart(uploadPartRequest);

                // 将返回的PartETag保存到List中。
                partETags.add(uploadPartResponse.getPartETag());

                // 关闭文件
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("上传异常1");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传异常2");
            }
        }
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, objectKey, initiateMultipartUploadResponse.getUploadId(), partETags);
        // 完成分块上传
        CompleteMultipartUploadResponse completeMultipartUploadResponse =
                bosClient.completeMultipartUpload(completeMultipartUploadRequest);
        // 打印Object的ETag
        System.out.println("ETag getETag:" + completeMultipartUploadResponse.getETag());
        System.out.println("ETag getBucketName:" + completeMultipartUploadResponse.getBucketName());
        System.out.println("ETag getKey:" + completeMultipartUploadResponse.getKey());
        System.out.println("ETag getLocation:" + completeMultipartUploadResponse.getLocation());
        System.out.println("ETag list:" + partETags.toString());
    }


    /**
     * 取消分块上传事件
     *
     * @param objectKey
     * @param uploadId
     */
    public static void cancelMultipart(String objectKey, String uploadId) {
        AbortMultipartUploadRequest abortMultipartUploadRequest =
                new AbortMultipartUploadRequest(bucketName, objectKey, uploadId);

        // 取消分块上传
        bosClient.abortMultipartUpload(abortMultipartUploadRequest);
    }

    /**
     * 获取未完成的分块上传事件
     *
     * @return
     */
    public static ListMultipartUploadsResponse getBreakMultipart() {
        ListMultipartUploadsRequest listMultipartUploadsRequest = new ListMultipartUploadsRequest(bucketName);
        // 获取Bucket内所有上传事件
        ListMultipartUploadsResponse listing = bosClient.listMultipartUploads(listMultipartUploadsRequest);

        // 遍历所有上传事件
        for (MultipartUploadSummary multipartUpload : listing.getMultipartUploads()) {
            System.out.println("Key: " + multipartUpload.getKey() + " UploadId: " + multipartUpload.getUploadId());
        }
        return listing;

    }


    /**
     * 获取所有已上传的块信息
     *
     * @param objectKey
     * @param uploadId
     * @return
     */
    public static ListPartsResponse getRequestMultipartMsg(String objectKey, String uploadId) {
        ListPartsRequest listPartsRequest = new ListPartsRequest(bucketName, objectKey, uploadId);
        // 获取上传的所有Part信息
        ListPartsResponse partListing = bosClient.listParts(listPartsRequest);
        // 遍历所有Part
        for (PartSummary part : partListing.getParts()) {
            System.out.println("PartNumber: " + part.getPartNumber() + " ETag: " + part.getETag());
        }
        return partListing;
    }


    /**
     * 上传低频存储类型Object的初始化
     *
     * @param objectKey
     */
    public static void putMultiUploadStorageClassStandard(String objectKey) {
        InitiateMultipartUploadRequest iniReq = new InitiateMultipartUploadRequest(bucketName, objectKey);
        iniReq.withStorageClass(BosClient.STORAGE_CLASS_STANDARD_IA);
        bosClient.initiateMultipartUpload(iniReq);
    }

    /**
     * 上传冷存储类型Object的初始化
     *
     * @param objectKey
     */
    public static void putMultiUploadStorageClassCold(String objectKey) {
        InitiateMultipartUploadRequest iniReq = new InitiateMultipartUploadRequest(bucketName, objectKey);
        iniReq.withStorageClass(BosClient.STORAGE_CLASS_COLD);
        bosClient.initiateMultipartUpload(iniReq);
    }

    /**
     * 检查指定的文件夹是否存在
     */
    public static void checkBucketExist() {
        bosClient.doesBucketExist(bucketName);
    }

    /**
     * 拷贝一个文件
     *
     * @param srcBucketName
     * @param srcKey
     * @param destBucketName
     * @param destKey
     */
    public static void copyObject(String srcBucketName, String srcKey, String destBucketName, String destKey) {
        // 拷贝Object
        CopyObjectResponse copyObjectResponse = bosClient.copyObject(srcBucketName, srcKey, destBucketName, destKey);
        // 打印结果
        System.out.println("ETag: " + copyObjectResponse.getETag() + " LastModified: " + copyObjectResponse.getLastModified());
    }

    /*----------------------文件下载STARA-------------------------------*/

    /**
     * 直接下载Object到文件
     *
     * @param objectKey 文件目录以及文件名（用“/”分开 ）
     * @param file      下载后的文件
     */
    public static void getObjectRequest(String objectKey, File file) {
        // 新建GetObjectRequest
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, objectKey);
        //下载Object到文件
        /*ObjectMetadata objectMetadata = client.getObject(getObjectRequest, new File("/path/to/file","filename"));*/
        ObjectMetadata objectMetadata = bosClient.getObject(getObjectRequest, file);
    }

    /**
     * 范围下载
     * 为了实现更多的功能，可以通过使用GetObjectRequest来指定下载范围，实现更精细化地获取Object。如果指定的下载范围是0 - 100，
     * 则返回第0到第100个字节的数据，包括第100个，共101字节的数据，即[0, 100]。
     * 可以用此功能实现文件的分段下载和断点续传
     *
     * @param objectKey 文件目录以及文件名（用“/”分开 ）
     * @return 目标字节的数据
     */
    public static BosObject getObjectByteRequest(String objectKey) {
        // 新建GetObjectRequest
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, objectKey);

        // 获取0~100字节范围内的数据
        getObjectRequest.setRange(0, 100);

        // 获取Object，返回结果为BosObject对象
        return bosClient.getObject(getObjectRequest);
    }

    /**
     * 只获取ObjectMetadata而不获取Object的实体
     *
     * @param objectKey 文件夹和文件名
     * @return 文件信息
     * <p>
     * contentType    Object的类型
     * contentLength    Object的大小
     * contentMd5   Object的MD5
     * etag Object的HTTP协议实体标签
     * storageClass Object的存储类型
     * userMetadata 如果在PutObject指定了userMetadata自定义meta，则返回此项
     * xBceCrc  如果在PutObject指定了object的CRC值(循环冗余校验码)，则返回此项
     */
    public static ObjectMetadata getObjectMetadata(String objectKey) {
        ObjectMetadata objectMetadata = bosClient.getObjectMetadata(bucketName, objectKey);
        return objectMetadata;
    }
    /*----------------------文件下载END-------------------------------*/

    /**
     * 标准存储转为低频存储
     */
    /**
     * @param sourceBucketName 文件所在的BucketName
     * @param sourceKey        所在BucketName的key
     * @param bucketName       新位置的BucketName
     * @param key              新位置的BucketNameKEY
     * @param storageType      想要转换的存储类型    STANDARD(标准存储), STANDARD_IA(低频存储)和COLD(冷存储)
     */
    public static void changeStorageClass(String sourceBucketName, String sourceKey, String bucketName, String key, String storageType) {
        CopyObjectRequest copyObjectRequest = new CopyObjectRequest(sourceBucketName, sourceKey, bucketName, key);
        copyObjectRequest.setStorageClass(storageType);
        bosClient.copyObject(copyObjectRequest);
    }

    /**
     * 获取文件下载URL
     *
     * @param objectKey           文件夹和文件名
     * @param expirationInSeconds 有效期（默认1800，永久有效为-1）
     * @return 目标文件的下载url
     */
    public static String generatePresignedUrl(String objectKey, int expirationInSeconds) {
        URL url = bosClient.generatePresignedUrl(bucketName, objectKey, expirationInSeconds);
        return url.toString();
    }

    /**
     * 修改文件元信息
     * BOS修改Object的Metadata通过拷贝Object实现。即拷贝Object的时候，把目的Bucket设置为源Bucket，目的Object设置为源Object，
     * 并设置新的Metadata，通过拷贝自身实现修改Metadata的目的。如果不设置新的Metadata，则报错。
     *
     * @param objectKey
     * @param newObjectMetadata
     */
    public void setObjectMeta(String objectKey, ObjectMetadata newObjectMetadata) {
        CopyObjectRequest request = new CopyObjectRequest(bucketName, objectKey, bucketName, objectKey);
        // 设置新的ObjectMetadata
        request.setNewObjectMetadata(newObjectMetadata);
        // 拷贝Object
        CopyObjectResponse copyObjectResponse = bosClient.copyObject(request);
        // 打印结果
        System.out.println("ETag: " + copyObjectResponse.getETag() + " LastModified: " + copyObjectResponse.getLastModified());
    }
}
