package org.eu.tz95.tools.util;

import org.eu.tz95.tools.config.Record;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author tz95
 * @project dnspod-update
 * @date 2025/5/9
 */
public class JsonUtil {

    public static Properties readJsonToProp(String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);
        Properties prop = new Properties();
        for (String key : jsonObject.keySet()) {
            prop.setProperty(key, jsonObject.getString(key));
        }
        return prop;
    }

    public static String writeRecordToJson(Record record) {
        // 目前存在一定设计问题，采用反射获取Record类的字段属性,后续会考虑其它方法进行改进
        Class<? extends Record> aClass = record.getClass();
        Field[] recordFields = aClass.getDeclaredFields();
        JSONObject jsonObject = new JSONObject();
        for (Field field : recordFields) {
            field.setAccessible(true);
            try {
                String value = (String) field.get(record);
                jsonObject.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String json = jsonObject.toString();
        System.out.println("Generated JSON: " + json);
        return json;
    }
}
