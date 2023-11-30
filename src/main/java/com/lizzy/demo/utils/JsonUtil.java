//package com.lizzy.demo.utils;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//
//import java.text.SimpleDateFormat;
//import java.util.TimeZone;
//import java.util.logging.Logger;
//
//public class JsonUtil {
//    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
//
//
//    private static ObjectMapper objectMapper = new ObjectMapper();
//    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
//
//
//    static {
//        // 对象的所有字段全部列入
//        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
//        // 取消默认转换timestamps形式
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        // 忽略空bean转json的错误
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        //设置为东八区
//        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        // 统一日期格式
//        objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
//        // 反序列化时,忽略在json字符串中存在, 但在java对象中不存在对应属性的情况, 防止错误
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        // 序列换成json时,将所有的long变成string
//        objectMapper.registerModule(new SimpleModule().addSerializer(Long.class, ToStringSerializer.instance).addSerializer(Long.TYPE, ToStringSerializer.instance));
//    }
//
//
//    /**
//     * 对象序列化成json字符串
//     * @param obj
//     * @param <T>
//     * @return
//     */
//    public static <T> String objToStr(T obj) {
//        if (null == obj) {
//            return null;
//        }
//
//
//        try {
//            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
//        } catch (Exception e) {
//            log.warn("objToStr error: ", e);
//            return null;
//        }
//    }
//
//
//    /**
//     * json字符串反序列化成对象
//     * @param str
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    public static <T> T strToObj(String str, Class<T> clazz) {
//        if (StringUtils.isBlank(str) || null == clazz) {
//            return null;
//        }
//
//
//        try {
//            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
//        } catch (Exception e) {
//            log.warn("strToObj error: ", e);
//            return null;
//        }
//    }
//
//
//    /**
//     * json字符串反序列化成对象(数组)
//     * @param str
//     * @param typeReference
//     * @param <T>
//     * @return
//     */
//    public static <T> T strToObj(String str, TypeReference<T> typeReference) {
//        if (StringUtils.isBlank(str) || null == typeReference) {
//            return null;
//        }
//
//
//        try {
//            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
//        } catch (Exception e) {
//            log.warn("strToObj error", e);
//            return null;
//        }
//    }
//}
