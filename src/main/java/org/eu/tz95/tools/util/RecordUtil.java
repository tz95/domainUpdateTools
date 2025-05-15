package org.eu.tz95.tools.util;

import org.eu.tz95.tools.config.Record;
import org.eu.tz95.tools.model.RecordFull;


import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author tz95
 * @project dnspod-update
 * @date 2025/4/3
 */
public class RecordUtil {

    public static Record getRecordProp() {
        Record record = null;
        Properties prop = ResourceUtil.readProperties("config.properties");

        if (prop != null) {
            String domain = prop.getProperty("domain");
            String subDomain = prop.getProperty("subdomain");
            String token = prop.getProperty("login_token");
            String format = prop.getProperty("format");
            String recordType = prop.getProperty("record_type");
            String recordLine = prop.getProperty("record_line");
            String ttl = prop.getProperty("ttl");

            record = new Record(token, format, domain, subDomain,recordType,recordLine,Integer.parseInt(ttl));
        }

        return record;
    }

    public static RecordFull getRecordFullProp(Record record) {
        RecordFull recordFull = null;
        String recordList = null;
        try {
            recordList = getRecordList(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (recordList != null) {
            // System.out.println("recordList = " + recordList);
            Properties prop = JsonUtil.readJsonToProp(recordList);
            // System.out.println(prop);
            int i = 0;
            while(true){
                String name = prop.getProperty("records[" + i + "].name");
                System.out.println("前缀["+i+"] = " + name);
                i++;
                if (name == null) {
                    break;
                }
            }
            System.out.println("请输入需要更新的前缀序号:");
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            scan.close();
            int index = Integer.parseInt(s);
            String recordId = prop.getProperty("records[" + index + "].id");
            String sub_domain = prop.getProperty("records[" + index + "].name");

            // String record_line = prop.getProperty("records[" + index + "].record_line");
            String value = prop.getProperty("records[" + index + "].value");
            recordFull = new RecordFull(record,recordId,value);
            recordFull.setSub_domain(sub_domain);
            // recordFull.setRecord_line(record_line);
        }
        return recordFull;
    }

    public static String getRecordList(Record record) throws Exception {
        String formData = "login_token=" + record.getLogin_token()
                + "&format=" + record.getFormat()
                + "&domain=" + record.getDomain()
                // + "&sub_domain=" + record.getSub_domain();
                ;
        HttpResponse<String> resp = ConnectionUtil.sendRequest("https://dnsapi.cn/Record.List",
                new String[]{"Content-Type", "application/x-www-form-urlencoded"},
                formData);
        if (resp != null && resp.statusCode() == 200) {
            return resp.body();
        } else {
            throw new Exception("请求资源失败");
        }
    }

}
