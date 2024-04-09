package com.example.line_api.Controller;

import com.example.line_api.Service.LineMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class studentcontroller {
    @Autowired
    private LineMessageService lineMessageService;

    @PostMapping("/send-message/{stu_num}")
    public ResponseEntity<String> sendMessageToLine(@PathVariable("stu_num") String stuNum) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = now.format(formatter);

        String message = "學生 " + stuNum + " 於 " + currentTime + " 完成做題";

        boolean messageSent = lineMessageService.sendMessageToLineAPI(message);

        if (messageSent) {
            return new ResponseEntity<>("訊息成功發送到 Line", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("訊息發送失敗", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}