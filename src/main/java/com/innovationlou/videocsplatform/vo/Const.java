package com.innovationlou.videocsplatform.vo;

/**
 * 该类中因为涉及到JSON序列化, 所以保留了非常量变量, 仅供序列化使用, 请不要主动调用
 */
public class Const {

    public static final Integer COMMON_ERROR = -100;
    public final Integer COMMON_ERROR_ = -100;

    public static final Integer OPERATION_SUCCESS = 100;
    public final Integer OPERATION_SUCCESS_ = 100;

    /**
     * 关于登录使用的常量
     */
    public static final Integer USERNAME_NOT_FOUND = -1001;
    public final Integer USERNAME_NOT_FOUND_ = -1001;

    public static final Integer PASSWORD_WRONG = -1002;
    public final Integer PASSWORD_WRONG_ = -1002;

    public static final Integer ROLE_TYPE_WRONG = -1003;
    public final Integer ROLE_TYPE_WRONG_ = -1003;

    public static final Integer VERIFYCODE_WRONG = -1004;
    public final Integer VERIFYCODE_WRONG_ = -1004;

    public static final Integer LOGIN_SUCCESS = 1001;
    public final Integer LOGIN_SUCCESS_ = 1001;

    /**
     * 关于修改密码使用的常量
     */
    public static final Integer IDCARD_WRONG = -1005;   // 修改密码时传入的身份证号码不正确
    public final Integer IDCARD_WRONG_ = -1005;   // 修改密码时传入的身份证号码不正确
    public static final Integer FIND_PASSWORD_SUCCESS = 1009;
    public final Integer FIND_PASSWORD_SUCCESS_ = 1009;
    public static final Integer PASSWORD_INVALID = -1006;   // 密码不符合规矩
    public final Integer PASSWORD_INVALID_ = -1006;   // 密码不符合规矩


//    // 认证参数 管理员可以访问任何接口
//    public static final int AUTH_STUDENT = 2;
//    public final int _AUTH_STUDENT = 2;
//    public static final int AUTH_TEACHER = 3;
//    public final int _AUTH_TEACHER = 3;
//    public static final int AUTH_ADMIN = 4;
//    public final int _AUTH_ADMIN = 4;
//    public static final int AUTH_ALL = 5;
//    public final int _AUTH_ALL = 5;

}
