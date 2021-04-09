package com.jcs.socket.socket.designPattern.observer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Observable;
import java.util.Observer;

@RequiredArgsConstructor
public class ReaderObserver implements Observer {

    @NonNull
    private String name;

    private String article;


    @Override
    public void update(Observable o, Object arg) {
        updateArticle(o);
    }

    public void updateArticle(Observable o){
        JavaStackObservable javaStackObservable = (JavaStackObservable) o;
        this.article = javaStackObservable.getArticle();
        System.out.printf("我是读者：%s，文章已更新为：%s\n", this.name, this.article);
    }
}
