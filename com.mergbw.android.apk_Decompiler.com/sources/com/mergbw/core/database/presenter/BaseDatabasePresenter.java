package com.mergbw.core.database.presenter;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BaseDatabasePresenter {
    public final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public <T> void addDisposable(Flowable<T> flowable, Consumer<T> consumer) {
        this.compositeDisposable.add(flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer));
    }

    public <T> void addDisposable(Completable completable, Action action) {
        this.compositeDisposable.add(completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(action));
    }

    public <T> void addDisposable(Maybe<T> maybe, Consumer<T> consumer) {
        this.compositeDisposable.add(maybe.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer));
    }
}
