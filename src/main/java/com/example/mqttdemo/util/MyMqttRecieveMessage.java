package com.example.mqttdemo.util;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @ClassName MyMqttRecieveMessage
 * @Description TODO
 * @Author lichun
 * @Date DATE
 * {TIME}
 */
@Slf4j
public class MyMqttRecieveMessage {
    private static int QoS = 1;
    private static String Host = "tcp://ahrjrue.iot.gz.baidubce.com:1883";
    private static String userName = "thingidp@ahrjrue|dht001|0|MD5";
    private static String password = "044c16cbe448ef1383fec40e34b799bc";
    private static MemoryPersistence memoryPersistence = null;
    private static MqttConnectOptions mqttConnectOptions = null;
    private static MqttClient mqttClient  = null;

    public static void main(String[] args) {
        init("dht001");
    }
    public static void init(String clientId) {
        mqttConnectOptions = new MqttConnectOptions();
        memoryPersistence = new MemoryPersistence();
//        ilogger ilogger = new ilogger("MyMqttRecieveMessage", "init");
        if(null != memoryPersistence && null != clientId && null != Host) {
            try {
                mqttClient = new MqttClient(Host, clientId, memoryPersistence);
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            log.info("memoryPersistence clientId Host 有空值");
        }

        if(null != mqttConnectOptions) {
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(30);
            mqttConnectOptions.setKeepAliveInterval(45);
            mqttConnectOptions.setUserName(userName);
            mqttConnectOptions.setPassword(password.toCharArray());
            if(null != mqttClient && !mqttClient.isConnected()) {
                mqttClient.setCallback(new MqttRecieveCallback());
                try {
                    mqttClient.connect();
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else {
                log.info("mqttClient is error");
            }
        }else {
            log.info("mqttConnectOptions is null");
        }
    }


    public static void recieve(String topic) {
        int[] Qos = {QoS};
        String[] topics = {topic};
//        ilogger ilogger = new ilogger("MyMqttRecieveMessage", "subTopic");
        if(null != mqttClient && mqttClient.isConnected()) {
            if(null!=topics && null!=Qos && topics.length>0 && Qos.length>0) {
                try {
                    mqttClient.subscribe(topics, Qos);
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else {
                log.info("there is error");
            }
        }else {
            init("123444");
            recieve(topic);
        }
    }
}
