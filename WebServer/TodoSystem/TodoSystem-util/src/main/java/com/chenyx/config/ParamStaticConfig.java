package com.chenyx.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Data
public class ParamStaticConfig {
    /**
     * 配置值部分
     */
    @Value("${myConfig.redis.timeout}")
    private Long redisTimeout;


    @Value("${myConfig.userDefaultHeadImg}")
    private String userDefaultHeadImg;
    @Value("${myConfig.studentDefaultHeadImg}")
    private String studentDefaultHeadImg;



    @Value("${myConfig.file.uploadUrl}")
    private String uploadUrl;

    public enum ConfigParamKeyEnum {

        redisTimeout("redisTimeout"),
        myLogProjectName("myLogProjectName"),
        myLogProjectId("myLogProjectId"),
        myLogSendLogIP("myLogSendLogIP"),
        myLogSendSystemMessageIP("myLogSendSystemMessageIP"),
        myLogExecutorPollNumber("myLogExecutorPollNumber"),
        userDefaultHeadImg("userDefaultHeadImg"),
        studentDefaultHeadImg("studentDefaultHeadImg"),
        smsSignName("smsSignName"),
        smsAccessKeyId("smsAccessKeyId"),
        smsAccessKeySecret("smsAccessKeySecret"),
        smsTemplateCode("smsTemplateCode"),
        uploadUrl("uploadUrl"),

        nullParam("nullParam"),
        ;


        ConfigParamKeyEnum(String paramName){
            this.paramName = paramName;
        }
        private String paramName;
        public String getParamName(){
            return paramName;
        }
        public static ConfigParamKeyEnum findByParamName(String paramName){
            for(ConfigParamKeyEnum v: ConfigParamKeyEnum.values()){
                if(v.getParamName().equals(paramName)){
                    return v;
                }
            }
            return nullParam;
        }
    }

    /**
     * 静态配置部分
     */
    public static Map<ConfigParamKeyEnum,Object> staticConfigParam = new HashMap<>();
    public static Object getWebappPathStatic(ConfigParamKeyEnum key){
        return ParamStaticConfig.staticConfigParam.get(key);
    }
    @Bean
    public int initStatic(){
        Field[] fields = ParamStaticConfig.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                Object fieldValue = getFieldValue(this, field);
                ParamStaticConfig.staticConfigParam.put(ConfigParamKeyEnum.findByParamName(field.getName()),fieldValue);
            }
        }
        return 0;
    }
    private static Object getFieldValue(Object o, Field f) {
        try {
            return o.getClass().getMethod("get" + upperFirstLatter(f.getName())).invoke(o);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String upperFirstLatter(String letter) {
        return letter.substring(0, 1).toUpperCase() + letter.substring(1);
    }
}
