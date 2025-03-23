package org.eu.tz95;

public class Main {
    public static void main(String[] args) {
        if (args[0] == "ipv4")
        {
            Setup ipv4 = new Setup("ipv4");
            ipv4.update();
        }
    }
}
