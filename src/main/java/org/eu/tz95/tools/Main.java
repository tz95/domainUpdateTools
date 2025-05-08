package org.eu.tz95.tools;

public class Main {
    public static void main(String[] args) {
        if (args[0] == "ipv4") {
            Setup ipv4 = new Setup("ipv4");
            ipv4.update();

        }else if (args[0] == "ipv6") {
            Setup ipv6 = new Setup("ipv6");
            ipv6.update();
        }
    }
}
