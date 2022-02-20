package io.candyboyou.common.sso;

import io.candyboyou.common.sso.model.UserInfo;

public class UserInfoBag {
    private static ThreadLocal<UserInfo> _t = new ThreadLocal<UserInfo>();

    public static void cleanAndSet(UserInfo ui) {
        _t.remove();
        _t.set(ui);
    }

    public static UserInfo get() {
        return _t.get();
    }

    public static void clean() {
        _t.remove();
    }
}
