package com.example.mqttdemo.controller;

import com.example.mqttdemo.util.MyMqttRecieveMessage;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * 
 * MQTT消息发送
 * @param null
 * @author lichun
 * @date 2020/9/18 15:17
 * @return 
*/
@RestController
public class MqttController {

    /*
     *
     * 发送MQTT消息
     * @param message
     * @author lichun
     * @date 2020/9/18 15:26
     * @return org.springframework.http.ResponseEntity<java.lang.String>
    */
    @ResponseBody
    @GetMapping(value = "/mqtt")
    public void sendMqtt() throws MqttException {
        MyMqttRecieveMessage.init("dht001");
    }
}
