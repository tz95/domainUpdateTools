package org.eu.tz95;

/**
 * @author tz95
 * @project dnspod-update
 * @date 2025/3/23
 */
public class Setup {

    public Setup(String type) {
        if (type.equals("ipv4") || type.equals("IPV4")) {

        }else if (type.equals("ipv6") || type.equals("IPV6")) {

        }else {
            throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    public void update() {

    }
}
