package com.cool.tageventbus;


public class StickyEvent {
    public String tag;
    public Class<?>[] eventTypes;
    public Object[] events;

    public StickyEvent(String tag, Class<?>[] eventTypes,Object[] events) {
        this.tag = tag;
        this.eventTypes = eventTypes;
        this.events = events;
    }
}
