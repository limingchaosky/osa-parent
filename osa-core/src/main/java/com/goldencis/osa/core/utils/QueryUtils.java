package com.goldencis.osa.core.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.function.Function;

/**
 * Created by limingchao on 2018/10/9.
 */
public class QueryUtils {

    /**
     * 设置分页参数
     * @param params 请求中的参数Map
     * @param <T> 包装类的泛型
     * @return 分页对象
     */
    public static <T> IPage<T> paresParams2Page(Map<String, String> params) {
        //获取分页参数的起始位置
        Integer start = StringUtils.isEmpty(params.get("start")) ? 0 : Integer.valueOf(params.get("start"));
        //获取分页参数的每页条数
        Integer length = StringUtils.isEmpty(params.get("length")) ? 10 : Integer.valueOf(params.get("length"));
        //获取分页页码
        Integer pageNo = (start / length) + 1;

        return new Page(pageNo, length);
    }

    /**
     * 设置模糊查询条件(不能再添加其他查询条件)
     * @param wrapper 查询的包装类
     * @param params 请求中的参数Map
     * @param columns 需要匹配查询条件的字段
     * @param <T> 包装类的泛型
     * @return 查询的包装类
     */
    public static <T> QueryWrapper<T> setQeryConditionByParamsMap(QueryWrapper<T> wrapper, Map<String, String> params, String... columns) {
        if (params.containsKey("searchStr") && !StringUtils.isEmpty(params.get("searchStr"))) {
            String searchStr = params.get("searchStr");
            //遍历字段，为其设置查询条件，模糊查询
            for (String column : columns) {
                wrapper.or().like(column, searchStr);

            }
        }

        return wrapper;
    }

    /**
     * 设置查询时间
     * @param wrapper 查询的包装类
     * @param params 请求中的参数Map
     * @param column 需要匹配时间条件的字段
     * @param <T> 包装类的泛型
     * @return 查询的包装类
     */
    public static <T> QueryWrapper<T> setQeryTimeByParamsMap(QueryWrapper<T> wrapper, Map<String, String> params, String column) {
        String startTime;
        String endTime;
        //分为三种情况，开始时间和结束时间都有，或者只有其中一个。
        if (params.containsKey("startTime") && params.containsKey("endTime")) {
            startTime = params.get("startTime");
            endTime = params.get("startTime");
            if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
                wrapper.between(column, startTime, endTime);
            }
        } else if (params.containsKey("startTime")) {
            startTime = params.get("startTime");
            if (!StringUtils.isEmpty(startTime)) {
                wrapper.ge(column, startTime);
            }
        } else if (params.containsKey("endTime")) {
            startTime = params.get("startTime");
            endTime = params.get("startTime");
            if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
                wrapper.le(column, endTime);
            }
        }

        return wrapper;
    }

    /**
     * 设置排序条件
     * @param wrapper 查询的包装类
     * @param params 请求中的参数Map
     * @param defaultOrderType 默认的排序类型
     * @param defaultOrderColumn 默认的排序字段
     * @param <T> 包装类的泛型
     * @return 查询的包装类
     */
    public static <T> QueryWrapper<T> setQeryOrderByParamsMap(QueryWrapper<T> wrapper, Map<String, String> params, String defaultOrderType, String defaultOrderColumn) {
        //增加默认排序
        if (!params.containsKey("orderType") && !StringUtils.isEmpty(defaultOrderType)) {
            params.put("orderType", defaultOrderType);
        }
        if (!params.containsKey("orderColumn") && !StringUtils.isEmpty(defaultOrderColumn)) {
            params.put("orderColumn", defaultOrderColumn);
        }

        if (params.containsKey("orderType") && params.containsKey("orderColumn")) {
            String orderType = params.get("orderType");
            String orderColumn = params.get("orderColumn");
            if (!StringUtils.isEmpty(orderType) && !StringUtils.isEmpty(orderColumn)) {
                boolean isAsc = "desc".equals(orderType) ? false : true;
                wrapper.orderBy(true, isAsc, orderColumn);
            }
        }

        return wrapper;
    }

    /**
     * 设置排序条件
     * @param wrapper 查询的包装类
     * @param params 请求中的参数Map
     * @param <T> 包装类的泛型
     * @return 查询的包装类
     */
    public static <T> QueryWrapper<T> setQeryOrderByParamsMap(QueryWrapper<T> wrapper, Map<String, String> params) {
        return setQeryOrderByParamsMap(wrapper, params, null, null);
    }
}
