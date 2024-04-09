package com.example.line_api.Response;

public class Common_Response {
    private  String rsp_msg;
    private  int rsp_code;

    public String getRsp_msg() {
        return rsp_msg;
    }

    public void setRsp_msg(String rsp_msg) {
        this.rsp_msg = rsp_msg;
    }

    public int getRsp_code() {
        return rsp_code;
    }

    public void setRsp_code(int rsp_code) {
        this.rsp_code = rsp_code;
    }

    public void SUCCESS() {
        this.rsp_code = 201;
        this.rsp_msg = "Success訊息成功發送到Line";
    }


    public void BAD_PARAM() {
        this.rsp_code = 500;
        this.rsp_msg = "INTERNAL_SERVER_ERROR訊息發送失敗";
    }
}
