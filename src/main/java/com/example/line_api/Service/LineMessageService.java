package com.example.line_api.Service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class LineMessageService {

    //定義請求和傳輸路徑
    private static final String LINE_API_URL = "https://api.line.me/v2/bot/message/push";
    private static final String CHANNEL_ACCESS_TOKEN = "wUIH87Nogz9YIm2iUT6NhIEzKBighOlHLrEIQQ3/zDOw8JygcgqlxCsAivucnPnDvhq5oUoaxtPJV2KIXqChdezYFRiJSq6VCH6keRnoDQQqmfMYlgfDf2rX6GP6gZlZLMYAQq9W3KCShAHy2059UQdB04t89/1O/w1cDnyilFU=";
    private static final String LINE_USER_ID = "U09ae06ed14f0cce2efae3f7c05ade6f2";

    public boolean sendMessageToLineAPI(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);//設置header為Json格式
        headers.set("Authorization", "Bearer " + CHANNEL_ACCESS_TOKEN);//設置授權header

        String jsonMessage = "{\"to\": \"" + LINE_USER_ID + "\", " +
                "\"messages\":[{\"type\":\"text\",\"text\":\"" + message + "\"}]}";//設置訊息格式

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonMessage, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(LINE_API_URL, requestEntity, String.class);//使用restTemplate發送請求到Line API的網址

        return responseEntity.getStatusCode() == HttpStatus.OK;//回傳狀態碼
    }
}
