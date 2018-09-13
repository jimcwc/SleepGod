package com.cool.butterknife.compiler;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;


public class Log {
    private Messager messager;

    public Log(Messager messager) {
        this.messager = messager;
    }

    public void e(String msg){
        messager.printMessage(Diagnostic.Kind.NOTE,msg);
    }
}
