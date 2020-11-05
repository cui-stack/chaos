package com.firepongo.chaos.app.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
public class JsonConfig {

    @Bean
    public HttpMessageConverters fastJsonConfigure() {

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        // 日期格式化
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteDateUseDateFormat);

        // 序列化配置Long类型转成String返回避免Long类型精度丢失
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);

        fastJsonConfig.setSerializeConfig(serializeConfig);
        ValueFilter dateFilter = (Object var1, String var2, Object var3) -> {
            try {
                if (var3 == null && var1 != null && LocalDateTime.class.isAssignableFrom(var1.getClass().getDeclaredField(var2).getType())){
                    return "";
                }
                if (var3 == null && var1 != null && Date.class.isAssignableFrom(var1.getClass().getDeclaredField(var2).getType())){
                    return "";
                }
            } catch (Exception e) {}
            return var3;
        };
        fastJsonConfig.setSerializeFilters(dateFilter);

        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        fastConverter.setFastJsonConfig(fastJsonConfig);

        return new HttpMessageConverters(fastConverter);

    }

}
