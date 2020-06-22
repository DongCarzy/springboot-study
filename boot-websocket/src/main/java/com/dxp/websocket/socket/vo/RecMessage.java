package com.dxp.websocket.socket.vo;

/**
 * 收到客户端的消息体
 *
 * @author carzy
 * @date 2020/6/22
 */
public class RecMessage {

    private String type;

    private String msg;

    private Object data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "type='" + type + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
