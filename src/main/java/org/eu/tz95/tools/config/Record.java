package org.eu.tz95.tools.config;

public class Record {

    private String login_token;

    private String format;

    private String domain;

    private String sub_domain;

    public String getLogin_token() {
        return login_token;
    }

    public String getFormat() {
        return format;
    }

    public String getDomain() {
        return domain;
    }

    public String getSub_domain() {
        return sub_domain;
    }

    public Record() {
    }

    public Record(String login_token, String format, String domain, String sub_domain) {
        this.login_token = login_token;
        this.format = format;
        this.domain = domain;
        this.sub_domain = sub_domain;
    }

    @Override
    public String toString() {
        return "Record{" +
                "login_token='" + login_token + '\'' +
                ", format='" + format + '\'' +
                ", domain='" + domain + '\'' +
                ", sub_domain='" + sub_domain + '\'' +
                '}';
    }
}
