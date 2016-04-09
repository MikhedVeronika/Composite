package com.epam.composite.entity;

public class LexemeLeaf implements Component {

    private ComponentType componentType;
    private String content;

    public LexemeLeaf(ComponentType componentType, String content) {
        this.componentType = componentType;
        this.content = content;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    @Override
    public Object getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }
}
