// Copyright (c) 2024 Roger Brown.
// Licensed under the MIT License.

package nz.geek.rhubarb.rabbitmqsend;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {
  private static final String QUEUE_NAME = "hello";

  public static void main(String[] args) throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      String message = "Hello RabbitMQ!";
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    }
  }
}
