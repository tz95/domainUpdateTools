package org.eu.tz95.tools;

import org.eu.tz95.tools.connect.Ipv4Address;
import org.eu.tz95.tools.connect.Ipv6Address;
import org.eu.tz95.tools.connect.base.Address;

/**
 * @author tz95
 * @project dnspod-update
 * @date 2025/3/23
 */
public class Setup {

    private Address address;

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
        System.out.println("Hello!"+address);
    }
}
