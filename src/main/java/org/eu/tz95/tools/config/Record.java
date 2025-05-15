package org.eu.tz95.tools.config;

public class Record {

    private String login_token;

    private String format;

    private String domain;

    private String sub_domain;

    private String record_type;

    private String record_line;

    private Integer ttl;

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

    public String getRecord_type() {
        return record_type;
    }

    public String getRecord_line() {
        return record_line;
    }

    public Integer getTtl() {
        return ttl;
    }

    public Record() {
    }

    public Record(String login_token, String format, String domain, String sub_domain, String record_type, String record_line, Integer ttl) {
        this.login_token = login_token;
        this.format = format;
        this.domain = domain;
        this.sub_domain = sub_domain;
        this.record_type = record_type;
        this.record_line = record_line;
        this.ttl = ttl;
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
