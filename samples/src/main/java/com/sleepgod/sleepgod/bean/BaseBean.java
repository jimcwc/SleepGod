package com.sleepgod.sleepgod.bean;


public class BaseBean<T> {
    public int ERRORCODE;
    public T RESULT;

    public boolean isSuccess(){
        if(ERRORCODE == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "ERRORCODE=" + ERRORCODE +
                ", RESULT=" + RESULT +
                '}';
    }
}
