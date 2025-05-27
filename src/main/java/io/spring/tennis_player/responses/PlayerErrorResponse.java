package io.spring.tennis_player.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class PlayerErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime timeStamp;
    private int statusCode;
    private String path;
    private String msg;

    public PlayerErrorResponse(ZonedDateTime timeStamp, int statusCode, String path, String msg) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.path = path;
        this.msg = msg;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
