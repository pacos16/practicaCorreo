package com.germangascon.navigationdrawersample.modelo;

import java.io.Serializable;

import java.util.Date;

public class Mail implements Serializable,Comparable{

    private String from;
    private String to;
    private String subject;
    private String body;
    private Date sentOn;
    private boolean readed;
    private boolean deleted;
    private boolean spam;

    public Mail(){    }
    public Mail(String from, String to, String subject, String body, Date sentOn, boolean readed, boolean deleted, boolean spam) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.sentOn = sentOn;
        this.readed = readed;
        this.deleted = deleted;
        this.spam = spam;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isSpam() {
        return spam;
    }

    public void setSpam(boolean spam) {
        this.spam = spam;
    }

    @Override
    public int compareTo(Object o) {
        Mail m= (Mail) o;
        return  m.getSentOn().compareTo(this.sentOn);
    }
}
