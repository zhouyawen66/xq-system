package com.cnaidun.police.util.bos;

/**
 * @ClassName BucketManage
 * @Descriprion TODO
 * @Author dongyin
 * @Date 2019/10/23 10:21
 **/
public enum ObjectManage {
    CERTIFICATION("certification/");

    /**
     * 内容描述
     */
    private String value;

    /**
     * @param value
     */
    private ObjectManage(String value){
        this.value=value;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     * @return
     */
    public String getValue(){
        return value;
    }
}
