package com.ai.base.interceptor;

import com.ai.base.model.core.User;

/**
 * 线程级别的用户对象
 *
 * @author hejg
 */
public class UserManager {
    private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();

    public static User getUser() {
        return threadLocal.get();
    }

    static void setUser(User value) {
        threadLocal.set(value);
    }

    @Deprecated
    static void removeUser() {
        threadLocal.remove();
    }
}
