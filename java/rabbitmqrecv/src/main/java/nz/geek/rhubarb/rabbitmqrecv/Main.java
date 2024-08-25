// Copyright (c) 2024 Roger Brown.
// Licensed under the MIT License.

package nz.geek.rhubarb.rabbitmqrecv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {
  private static final String QUEUE_NAME = "hello";

  public static void main(String[] args)
      throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);

    DeliverCallback deliverCallback =
        (consumerTag, delivery) -> {
          System.out.println(encode(delivery.getBody()));
        };
    String tag = channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
    try
    {
        while (System.in.read() >= 0);
    }
    finally {
        channel.basicCancel(tag);
    }
    channel.close();
    connection.close();
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
