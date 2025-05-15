package org.eu.tz95.tools;

import org.eu.tz95.tools.connect.Ipv4Address;
import org.eu.tz95.tools.connect.Ipv6Address;
import org.eu.tz95.tools.connect.base.Address;
import org.eu.tz95.tools.config.Record;
import org.eu.tz95.tools.model.RecordFull;
import org.eu.tz95.tools.util.ConnectionUtil;
import org.eu.tz95.tools.util.RecordUtil;
import org.json.JSONObject;

import java.net.http.HttpResponse;

/**
 * @author tz95
 * @project dnspod-update
 * @date 2025/3/23
 */
public class Setup {

    private Record record;

    private Address address;

    private void getRecordId(){
        record = RecordUtil.getRecordProp();
        if (record == null) {
            System.out.println("RecordId is null");
            return;
        }
        System.out.println("record = " + record);
    }

    public Setup(String type) {
        if (type.equals("ipv4") || type.equals("IPV4")) {
            address = Ipv4Address.getInstance();
        }else if (type.equals("ipv6") || type.equals("IPV6")) {
            address = Ipv6Address.getInstance();
        }else {
            throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    public void update() {
        getRecordId();
        RecordFull recordFull = null;
        recordFull = RecordUtil.getRecordFullProp(record);
        if (recordFull == null) {
            System.err.println("未成功获取到记录信息,请检查配置文件并重试");
            return;
        }
        if (recordFull.getValue().equals(address.getAddress())){
            System.out.println("当前地址与记录一致,无需更新");
        }else {
            System.out.println("当前地址与记录不一致,开始更新");
            recordFull.setValue(address.getAddress());
            HttpResponse<String> resp = ConnectionUtil.sendRequest(
                    "https://dnsapi.cn/Record.Ddns",
                    new String[]{"Content-Type", "application/x-www-form-urlencoded"},
                    recordFull.toUrlEncoded()
                    );
            if (resp != null) {
                JSONObject respJson = new JSONObject(resp.body());
                String code = respJson.getJSONObject("status").getString("code");
                if("1".equals(code)){
                    System.out.println("更新成功");
                }else {
                    System.out.println("更新失败,错误信息: " +
                            respJson.getJSONObject("status").getString("message") + "" +
                            " 错误码: " + code
                    );
                }
            }

        }
    }
}
