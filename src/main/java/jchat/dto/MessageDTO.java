package jchat.dto;

import java.sql.Date;

public class MessageDTO {
    private int messageId;
    private String sender;
    private String body;
    private String date;

    public MessageDTO(int messageId, String sender, String body, String date) {
        this.messageId = messageId;
        this.sender = sender;
        this.body = body;
        this.date = date;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
