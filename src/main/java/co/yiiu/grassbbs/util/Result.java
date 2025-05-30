package co.yiiu.grassbbs.util;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
public class Result {

    private String message; // 响应消息
    private Object data;    // 响应数据

    private Integer code;
    private String description;
    private Object detail;

    public Result() {}

    // 私有构造函数，防止直接实例化
    private Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 私有构造函数，带有数据
    private Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应
    public static Result success() {return new Result(200, "操作成功");}

    // 成功响应，带有数据
    public static Result success(Object data) {
        return new Result(200, "操作成功", data);
    }

    // 失败响应
    public static Result error(String message) {
        return new Result(500, message);
    }

    // 失败响应，带有数据
    public static Result error(int code, String message) {
        return new Result(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
