package com.dxp.websocket.socket.vo;

/**
 * websocket消息体
 *
 * @author carzy
 * @date 2020/6/22
 */
public class SendMessage {

    private String type;

    private Object data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
