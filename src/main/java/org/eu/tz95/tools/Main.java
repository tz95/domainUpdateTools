package org.eu.tz95.tools;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify the type of IP address to update (ipv4 or ipv6).");
            return;
        }
        if ("ipv4".equals(args[0])) {
            Setup ipv4 = new Setup("ipv4");
            ipv4.update();
        }else if ("ipv6".equals(args[0])) {
            Setup ipv6 = new Setup("ipv6");
            ipv6.update();
        }
    }
}
