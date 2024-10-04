package com.silva.nexuside.models;

public class Symbol {
    private final String label;
    private final String insert;

    public Symbol(String label) {
        this(label, label);
    }

    public Symbol(String label, String insert) {
        this.label = label;
        this.insert = insert;
    }

    public String getLabel() {
        return label;
    }

    public String getInsert() {
        return insert;
    }
}