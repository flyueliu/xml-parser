package com.flyiu.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XMLObject {

    private String tagName;

    private XMLObject parent;

    private Map<String, String> attributes;

    private String content;

    private List<XMLObject> children;

    public List<XMLObject> getChild(String tagName) {
        return this.children.stream().filter(item -> {
            return item.getTagName().equals(tagName);
        }).collect(Collectors.<XMLObject>toList());
    }

    public XMLObject getSingleTag(String tagName) {
        return this.children.stream().filter(item -> {
            return item.getTagName().equals(tagName);
        }).findFirst().get();
    }

    public String getTagName() {
        return tagName;
    }

    void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<XMLObject> getChildren() {
        return children;
    }

    void setChildren(List<XMLObject> children) {
        this.children = children;
    }

    public String getContent() {
        return content;
    }


    void addContent(String content) {
        if (this.content == null) {
            this.content = content;
        } else {
            this.content += content;
        }
    }

    public XMLObject getParent() {
        return parent;
    }

    void setParent(XMLObject parent) {
        this.parent = parent;
    }

    void addChild(XMLObject child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }

    void addAttribute(String key, String value) {
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        attributes.put(key, value);
    }

    @Override
    public String toString() {
        return "{" +
                "\"tagName\":" + "\"" + tagName + "\"" +
                (attributes == null ? "" : (", \"attributes\":" + attributes.toString().replaceAll("=", "\":\"")
                        .replaceAll("\\{", "{\"")
                        .replaceAll(",", "\",\"")
                        .replaceAll("}", "\"}"))) +
                (content.isEmpty() ? "" : (",\"content\":" + "\"" + content + '\"')) +
                (children == null ? "" : (", \"children\":" + children)) +
                '}';
    }
}
