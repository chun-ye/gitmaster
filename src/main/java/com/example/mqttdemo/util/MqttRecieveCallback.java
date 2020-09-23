package com.example.mqttdemo.util;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @ClassName MqttRecieveCallback
 * @Description TODO
 * @Author lichun
 * @Date DATE
 * {TIME}
 */
public class MqttRecieveCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Client 接收消息主题 : " + s);
        System.out.println("Client 接收消息Qos : " + mqttMessage.getQos());
        System.out.println("Client 接收消息内容 : " + new String(mqttMessage.getPayload()));

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
