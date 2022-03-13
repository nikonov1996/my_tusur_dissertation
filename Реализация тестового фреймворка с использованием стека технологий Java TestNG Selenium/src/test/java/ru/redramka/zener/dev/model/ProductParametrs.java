package ru.redramka.zener.dev.model;

import com.google.common.collect.ForwardingList;

import java.util.ArrayList;

public class ProductParametrs extends ForwardingList<ProductData> {
    private ArrayList<ProductData> delegate;

    public ProductParametrs() {
        this.delegate = new ArrayList<ProductData>();
    }

    public ProductParametrs(ProductParametrs contact){
        this.delegate = new ArrayList<ProductData>(contact.delegate);
    }

    @Override
    protected ArrayList<ProductData> delegate() {
        return delegate;
    }
}
