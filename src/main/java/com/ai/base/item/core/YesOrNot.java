package com.ai.base.item.core;

/**
 * 表示肯定或否定的枚举
 *
 * @author hejg
 */
public enum YesOrNot {
    YES(1, "是"), NO(0, "否");

    private int value;
    private String name;

    private YesOrNot(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static YesOrNot get(int value) {
        for (YesOrNot item : YesOrNot.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
