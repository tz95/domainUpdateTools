package org.eu.tz95.tools.util;

import org.eu.tz95.tools.config.Record;
import org.eu.tz95.tools.model.RecordFull;
import org.json.JSONArray;
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
        flattenJsonToProperties("", jsonObject, prop);
        return prop;
    }

    private static void flattenJsonToProperties(String parentKey, JSONObject jsonObj, Properties prop) {
        for (String key : jsonObj.keySet()) {
            String fullKey = parentKey.isEmpty() ? key : parentKey + "." + key;

            Object value = jsonObj.get(key);
            if (value instanceof JSONObject) {
                // 递归处理嵌套对象
                flattenJsonToProperties(fullKey, (JSONObject) value, prop);
            } else if (value instanceof JSONArray) {
                // 处理数组（示例JSON中未出现但考虑兼容性）
                processJsonArray(fullKey, (JSONArray) value, prop);
            } else {
                // 基础类型直接存入
                prop.setProperty(fullKey, String.valueOf(value));
            }
        }
    }

    private static void processJsonArray(String prefix, JSONArray array, Properties prop) {
        for (int i = 0; i < array.length(); i++) {
            Object item = array.get(i);
            String itemKey = prefix + "[" + i + "]";

            if (item instanceof JSONObject) {
                flattenJsonToProperties(itemKey, (JSONObject) item, prop);
            } else {
                prop.setProperty(itemKey, String.valueOf(item));
            }
        }
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

    public static String writeRecordFullToJson(RecordFull recordFull) {
        JSONObject jsonObject = new JSONObject(recordFull.toJson());

        return jsonObject.toString();
    }




}
