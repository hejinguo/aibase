package com.ai.base.tool.vo;

/**
 * 键值对条目对象BEAN(SelectItem,Option)
 * @author hejg
 *
 * @param <T> text
 * @param <V> value
 */
public class SelectItem<T, V> {
	private T text;
	private V value;

	public SelectItem() {

	}

	public SelectItem(T text, V value) {
		this.text = text;
		this.value = value;
	}

	public T getText() {
		return text;
	}

	public void setText(T text) {
		this.text = text;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SelectItem [text=" + text + ", value=" + value + "]";
	}
}
