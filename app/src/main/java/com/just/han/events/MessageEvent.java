package com.just.han.events;

/**
 * Created by Just on 16/7/10.
 *
 * Event Message
 */
public class MessageEvent {
    public final int code;
    public final String message;

    public MessageEvent(int Code,String Message){
        this.code = Code;
        this.message = Message;
    }

}
