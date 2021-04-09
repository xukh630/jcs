package com.jcs.socket.socket.designPattern.observer;

import lombok.Data;

import java.util.Observable;

@Data
public class JavaStackObservable extends Observable {

    private String article;

    /**
     * 发布
     * @param article
     */
    public void publish(String article){
        this.article = article;

        this.setChanged();

        this.notifyObservers();
    }
}
