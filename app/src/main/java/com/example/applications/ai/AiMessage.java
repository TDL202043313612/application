package com.example.applications.ai;

import java.io.Serializable;

public class AiMessage implements Serializable {
    public static String SEND_BY_ME = "me";
    public static String SEND_BY_BOT = "bot";


    public enum StreamMessage{
        string_start,
        string_underway,
        string_end
    }

    /*发送内容*/
    String message;
    /*通过什么发送*/
    String sentBy;
    /*当前状态*/
    StreamMessage stream;


    public void setStream(StreamMessage stream) {
        this.stream = stream;
    }
    public StreamMessage getStream() {
        return stream;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public AiMessage(String message, String sentBy , StreamMessage stream) {
        this.message = message;
        this.sentBy = sentBy;
        this.stream = stream;
    }
}