package com.cnaidun.police.controller;

import com.cnaidun.police.config.tip.ErrorTip;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.util.bos.BOSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileController
 * @Descriprion TODO
 * @Author dongyin
 * @Date 2019/12/5 09:42
 **/
@Api(description = "公共类")
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {


    @ApiOperation("上传文件")
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Tip uploadFile(@RequestPart("file") MultipartFile multipartFile, @RequestParam String prefixObjectKey) {
        String url = BOSUtil.uploadInputStreamToBos(multipartFile, prefixObjectKey);
        if (StringUtils.isBlank(url)) {
            return new ErrorTip("上传失败");
        }
        return new SuccessTip(url);
    }
}
