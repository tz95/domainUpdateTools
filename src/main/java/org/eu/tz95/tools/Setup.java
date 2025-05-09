package org.eu.tz95.tools;

import org.eu.tz95.tools.connect.Ipv4Address;
import org.eu.tz95.tools.connect.Ipv6Address;
import org.eu.tz95.tools.connect.base.Address;
import org.eu.tz95.tools.config.Record;
import org.eu.tz95.tools.util.RecordUtil;

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
        String recordList = null;
        try {
            recordList = RecordUtil.getRecordList(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("recordList = " + recordList);

        System.out.println("Hello!"+address);
    }
}
