package org.eu.tz95.tools.util;

import org.eu.tz95.tools.config.Record;


import java.net.http.HttpResponse;
import java.util.Properties;

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

            record = new Record(token, format, domain, subDomain);
        }

        return record;
    }

    public static String getRecordList(Record record) throws Exception {
        String formData = "login_token=" + record.getLogin_token()
                + "&format=" + record.getFormat()
                + "&domain=" + record.getDomain()
                + "&sub_domain=" + record.getSub_domain();
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
