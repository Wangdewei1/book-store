package com.itwang.book.utils;

public class ResultEntity <T>{
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";

    //封装请求结果
    private String result;

    //对于查询操作，使用T类型封装查询结果
    private T data;

    //封装操作失败后的提示消息
    private String message;

    /**
     * 增删改操作成功
     */
    public static <T> ResultEntity<T> ok(){
        ResultEntity<T> resultEntity = new ResultEntity<>();

        resultEntity.setResult(SUCCESS);

        return resultEntity;
    }

    /**
     * 查询操作成功
     * @return
     */
    public static <T> ResultEntity<T> ok(T data){
        ResultEntity<T> resultEntity = new ResultEntity<>();

        resultEntity.setResult(SUCCESS);
        resultEntity.setData(data);
        return resultEntity;
    }

    /**
     * 查询失败
     * @return
     */
    public static <T> ResultEntity<T> failed(String message){
        ResultEntity<T> resultEntity = new ResultEntity<>();
        resultEntity.setResult(FAILED);
        resultEntity.setMessage(message);
        return resultEntity;
    }



    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
