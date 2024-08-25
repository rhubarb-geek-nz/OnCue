// Copyright (c) 2024 Roger Brown.
// Licensed under the MIT License.

package nz.geek.rhubarb.mqttsend;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Main {
  private static final String TOPIC = "hello";

  public static void main(String[] args) throws MqttException {
    String publisherId = UUID.randomUUID().toString();
    IMqttClient publisher = new MqttClient("tcp://localhost:1883", publisherId);
    MqttConnectOptions options = new MqttConnectOptions();
    options.setAutomaticReconnect(true);
    options.setCleanSession(true);
    options.setConnectionTimeout(10);
    publisher.connect(options);
    try {
      MqttMessage msg = new MqttMessage("Hello MQTT!".getBytes());
      msg.setQos(0);
      msg.setRetained(true);
      publisher.publish(TOPIC, msg);
    } finally {
      publisher.disconnect();
    }
    publisher.close();
  }
}
