package com.ai.base.tool.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形节点
 * @author hejg
 *
 * @param <T> text
 * @param <V> value
 */
public class TreeItem<T, V> {
	private T text;
	private V value;
	private boolean checked;
	private List<TreeItem<T, V>> children;
	
	public TreeItem(){
		this.checked = false;
		this.children = new ArrayList<TreeItem<T,V>>();
	}
	
	public TreeItem(T text,V value){
		this.text = text;
		this.value = value;
		this.checked = false;
		this.children = new ArrayList<TreeItem<T,V>>();
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<TreeItem<T, V>> getChildren() {
		return children;
	}

	public void setChildren(List<TreeItem<T, V>> children) {
		this.children = children;
	}
}
