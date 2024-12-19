package com.mergbw.core.event;

import java.io.Serializable;

public class EventMessage implements Serializable {
    private String action;
    private Object data;

    public EventMessage() {
    }

    public EventMessage(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public Object getData() {
        return this.data;
    }

    public EventMessage setData(Object obj) {
        this.data = obj;
        return this;
    }
}
