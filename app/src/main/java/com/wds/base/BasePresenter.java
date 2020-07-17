package com.wds.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    protected V view;
    private ArrayList<BaseModel> list=new ArrayList<>();
    public void setView(V view) {
        this.view = view;
    }

    public BasePresenter() {
        initModel();
    }
    public void onDestroy(){
        view=null;
        if (list.size()>0){
            for (BaseModel baseModel : list) {
                baseModel.onDestroy();
            }
            list.clear();
        }
    }
    public void addModel(BaseModel baseModel){
        list.add(baseModel);
    }
    protected abstract void initModel();
}
