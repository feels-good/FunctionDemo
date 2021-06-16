package com.fzj.functiondemo.binding.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class User extends ViewModel {
    public final MutableLiveData<String> age = new MutableLiveData<>();


    public void clickage() {
        age.setValue(Math.random() + "");
    }
}
