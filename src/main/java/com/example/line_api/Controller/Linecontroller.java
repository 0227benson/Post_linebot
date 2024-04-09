package com.example.line_api.Controller;

import com.example.line_api.Response.Common_Response;
import com.example.line_api.Service.LineMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class Linecontroller {
    @Autowired
    private LineMessageService lineMessageService;

    @PostMapping("/send-message/{stu_num}")
    public ResponseEntity<Common_Response> sendMessageToLine(@PathVariable("stu_num") String stunum) {
        LocalDateTime now = LocalDateTime.now();//獲取當前日期和時間
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//將時間轉成想要的格式

        String message = "學生 " + stunum + " 於 " + now.format(formatter) + " 完成做題";//將想要發送的訊息內容合併

        boolean messageSent = lineMessageService.sendMessageToLineAPI(message);//發送訊息給指定用戶
        Common_Response rsp = new Common_Response();
        if (messageSent) {
            rsp.SUCCESS();
        } else {
            rsp.BAD_PARAM();
        }
        return ResponseEntity.ok(rsp);//回傳請求狀態資訊
    }
}