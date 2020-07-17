package com.wds.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseModel {
    private CompositeDisposable compositeDisposable;
    public void onDestroy(){
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }
}
