package com.example.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/6/30.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    public static Result ok(){
        return new Result(0, "succeess", null);
    }

    public static Result ok(Object object){
        return new Result(0, "succeess", object);
    }

    public static Result fail(){
        return new Result(-1, "fail", null);
    }

    public static Result fail(String errorMsg){
        return new Result(-1, errorMsg, null);
    }

    public static Result fail(int code, String errorMsg){
        return new Result(code, errorMsg, null);
    }

    private int code;
    private String msg;
    private Object data;
}
