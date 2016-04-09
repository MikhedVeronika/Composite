package com.epam.composite.handler;

import com.epam.composite.entity.ComponentType;
import com.epam.composite.entity.Composite;

public abstract class Handler {

    protected Handler successor;
    protected Composite element;
    private ComponentType type;

    public Handler(ComponentType type) {
        this.type = type;
    }

    abstract public void parse(String text);

    public Composite chain(String text) {
        element = new Composite(type);
        parse(text);
        return element;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
}
