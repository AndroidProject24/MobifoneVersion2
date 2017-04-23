package com.toan_itc.mobifone.data.rxjava;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Toan.IT
 * Date: 13/07/2016
 */

public class RxBus {
    private static final RxBus instance = new RxBus();

    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public static RxBus getDefault() {
        return instance;
    }

    public void send(Object o) {
        _bus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return _bus;
    }

    public boolean hasObservers() {
        return _bus.hasObservers();
    }
}
