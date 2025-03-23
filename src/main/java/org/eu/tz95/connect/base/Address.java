package org.eu.tz95.connect.base;

/**
 * @author tz95
 * @project dnspod-update
 * @date 2025/3/23
 */
public abstract class Address {
    protected String address;

    abstract public void updateAddress();

    public String getAddress() {return address;}
    
}
