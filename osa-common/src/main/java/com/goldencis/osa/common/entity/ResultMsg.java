package com.goldencis.osa.common.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by limingchao on 2018/9/26.
 */
public class ResultMsg {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer resultCode;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    // 分页查询时的总记录数
    private Integer total;

    // 分页查询时的记录
    private Object rows;

    public ResultMsg() {

    }

    public ResultMsg(Integer status, String msg, Object data) {
        this.resultCode = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultMsg(Object data) {
        this.resultCode = 0;
        this.msg = "success";
        this.data = data;
    }

    public ResultMsg(Page page) {
        this.resultCode = 0;
        this.total = Math.toIntExact(page.getTotal());
        this.rows = page.getRecords();
    }

    public static ResultMsg ok(Object data) {
        return new ResultMsg(data);
    }

    public static ResultMsg ok() {
        return new ResultMsg(null);
    }

    public static ResultMsg build(Integer status, String msg, Object data) {
        return new ResultMsg(status, msg, data);
    }

    public static ResultMsg build(Integer status, String msg) {
        return new ResultMsg(status, msg, null);
    }

    public static ResultMsg page(Page page) {
        return new ResultMsg(page);
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return resultCode;
    }

    public void setStatus(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     * jsonNode 是解析树形结构的  类似于解析DOM
     *  {"age" : 29,
     *   "messages" : [ "msg 1", "msg 2", "msg 3" ],
     *	 "name" : "mkyong"
     *	 }
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static ResultMsg formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResultMsg.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ResultMsg format(String json) {
        try {
            return MAPPER.readValue(json, ResultMsg.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static ResultMsg formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static ObjectMapper getMAPPER() {
        return MAPPER;
    }
}
