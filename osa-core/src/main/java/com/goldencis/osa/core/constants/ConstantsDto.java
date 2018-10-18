package com.goldencis.osa.core.constants;

/**
 * Created by limingchao on 2018/9/25.
 */
public class ConstantsDto {

    public static final Integer CONST_TRUE = 1;
    public static final Integer CONST_FALSE = 0;
    public static final Integer CONST_ERROR = -1;

    public static final Integer RESPONSE_SUCCESS = 0;
    public static final Integer RESPONSE_ERROR = 500;

    //账户状态
    public static final Integer ACCOUNT_STATUS_ENABLE = 11;

    //资源开放相关的资源文件配置
    public static final String SECURITY_FILE_PATH = "classpath:static/dataFiles/security.xml";
    public static final String SECURITY_NONE = "/security/http/@pattern";

    //排序规则，升序或降序
    public static final String ORDER_TYPE_ASC = "asc";
    public static final String ORDER_TYPE_DESC = "desc";

    public static final String REAL_PATH = "realPath";
    public static final String CONTEXT_PATH = "contextPath";
}
