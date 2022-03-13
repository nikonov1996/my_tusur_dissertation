package ru.redramka.zener.dev.model;

import com.google.common.collect.ForwardingList;

import java.util.ArrayList;

public class UserList extends ForwardingList<UserProfilData> {

    private ArrayList<UserProfilData> delegate;

    public UserList() {
        this.delegate = new ArrayList<UserProfilData>();
    }

    public UserList(UserList contact){
        this.delegate = new ArrayList<UserProfilData>(contact.delegate);
    }

    @Override
    protected ArrayList<UserProfilData> delegate() {
        return delegate;
    }

}
