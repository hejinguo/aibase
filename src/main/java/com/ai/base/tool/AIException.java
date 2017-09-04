package com.ai.base.tool;

/**
 * 自定义异常类型
 *
 * @author hejg
 */
public class AIException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AIException() {
		super();
	}

	public AIException(String message) {
		super(message);
	}
}
