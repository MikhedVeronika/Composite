package com.epam.composite.entity;

import com.epam.composite.logic.LexemesList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Composite implements Component {
    private ComponentType componentType;
    ArrayList<Component> content;

    public Composite(ComponentType componentType) {
        this.componentType = componentType;
        this.content = new ArrayList<>();
    }

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }

    @Override
    public List<Component> getContent() {
        return Collections.unmodifiableList(content);
    }


    public void add(Component component) {
        content.add(component);
        if (component.getComponentType() == ComponentType.LEXEME) {
            LexemesList.addLexeme(component.getContent().toString());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (componentType) {
            case OPERATOR:
                sb.append("\n");
                appendComponents(sb);
                break;
            case METHOD:
                sb.append("\n");
                appendComponents(sb);
                break;
            case FIELD:
                sb.append("\n");
                appendComponents(sb);
                break;
            case BLOCK:
                sb.append("\n");
                appendComponents(sb);
                break;
            case CLASS:
                appendComponents(sb);
                sb.append("\n");
                break;
            default:
                sb.append(" ");
                break;
        }
        return sb.toString();
    }

    private void appendComponents(StringBuilder sb) {
        for (Component c : content) {
            if (c.toString().equals("}")) {
                sb.append("\n");
            }
            sb.append(c);
            sb.append(" ");
        }
    }
}
