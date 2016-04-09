package com.epam.composite.entity;

public interface Component {
    ComponentType getComponentType();
    Object getContent();
}
