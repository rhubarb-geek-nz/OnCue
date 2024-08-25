// Copyright (c) 2024 Roger Brown.
// Licensed under the MIT License.

package nz.geek.rhubarb.mqttrecv;

import java.io.IOException;
import java.util.UUID;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
  private static final String TOPIC = "hello";

  public static void main(String[] args) throws MqttException, IOException {
    String publisherId = UUID.randomUUID().toString();
    IMqttClient subscriber = new MqttClient("tcp://localhost:1883", publisherId);
    MqttConnectOptions options = new MqttConnectOptions();
    options.setAutomaticReconnect(true);
    options.setCleanSession(true);
    options.setConnectionTimeout(10);
    subscriber.connect(options);
    try {
      subscriber.subscribe(
          TOPIC,
          (topic, msg) -> {
            System.out.println(encode(msg.getPayload()));
          });
      while (System.in.read()>=0);
    } finally {
      subscriber.disconnect();
    }
    subscriber.close();
  }

  static final char[] map = {
    '0', '1', '2', '3',
    '4', '5', '6', '7',
    '8', '9', 'A', 'B',
    'C', 'D', 'E', 'F'
  };

  public static String encode(byte[] a) {
    int i = 0, offset = 0, length = a.length;
    char[] sb = new char[length * 2];

    while (0 != length--) {
      byte b = a[offset++];

      sb[i++] = map[(b >> 4) & 0xf];
      sb[i++] = map[b & 0xf];
    }

    return String.valueOf(sb, 0, i);
  }
}
