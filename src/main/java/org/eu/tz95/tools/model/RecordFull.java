package org.eu.tz95.tools.model;

import org.eu.tz95.tools.config.Record;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author tz95
 * @project dnspod-update
 * @date 2025/5/9
 */
public class RecordFull {

    private String login_token;

    private String format;

    private String domain;

    private String sub_domain;

    private String record_id;

    private String record_type;

    private String record_line;

    private String value;

    private Integer ttl;

    public void setLogin_token(String login_token) {
        this.login_token = login_token;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setSub_domain(String sub_domain) {
        this.sub_domain = sub_domain;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public void setRecord_type(String record_type) {
        this.record_type = record_type;
    }

    public void setRecord_line(String record_line) {
        this.record_line = record_line;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

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

    public String getRecord_id() {
        return record_id;
    }

    public String getRecord_type() {
        return record_type;
    }

    public String getRecord_line() {
        return record_line;
    }

    public String getValue() {
        return value;
    }

    public Integer getTtl() {
        return ttl;
    }

    public RecordFull() {
    }

    public RecordFull(String login_token, String format, String domain, String sub_domain, String record_id, String record_type, String record_line, String value, Integer ttl) {
        this.login_token = login_token;
        this.format = format;
        this.domain = domain;
        this.sub_domain = sub_domain;
        this.record_id = record_id;
        this.record_type = record_type;
        this.record_line = record_line;
        this.value = value;
        this.ttl = ttl;
    }
    public RecordFull(Record record,String record_id, String value) {
        this.login_token = record.getLogin_token();
        this.format = record.getFormat();
        this.domain = record.getDomain();
        this.sub_domain = record.getSub_domain();
        this.record_id = record_id;
        this.record_type = record.getRecord_type();
        this.record_line = record.getRecord_line();
        this.value = value;
        this.ttl = record.getTtl();
    }

    public String toJson() {
        return "{" +
                "\"login_token\":\"" + login_token + '\"' +
                ",\"format\":\"" + format + '\"' +
                ",\"domain\":\"" + domain + '\"' +
                ",\"sub_domain\":\"" + sub_domain + '\"' +
                ",\"record_id\":\"" + record_id + '\"' +
                ",\"record_type\":\"" + record_type + '\"' +
                ",\"record_line\":\"" + record_line + '\"' +
                ",\"value\":\"" + value + '\"' +
                ",\"ttl\":" + ttl +
                "}";
    }

    public String toUrlEncoded() {
        String encoded = String.format(
                "login_token=%s&format=%s&domain=%s&sub_domain=%s&record_id=%s&record_type=%s&record_line=%s&value=%s&ttl=%d",
                URLEncoder.encode(login_token, StandardCharsets.UTF_8),
                URLEncoder.encode(format, StandardCharsets.UTF_8),
                URLEncoder.encode(domain, StandardCharsets.UTF_8),
                URLEncoder.encode(sub_domain, StandardCharsets.UTF_8),
                URLEncoder.encode(record_id, StandardCharsets.UTF_8),
                URLEncoder.encode(record_type, StandardCharsets.UTF_8),
                URLEncoder.encode(record_line, StandardCharsets.UTF_8),
                URLEncoder.encode(value, StandardCharsets.UTF_8),
                ttl
        );

        return encoded;
    }
}
