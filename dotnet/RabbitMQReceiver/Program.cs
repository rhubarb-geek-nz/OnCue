// Copyright (c) 2024 Roger Brown.
// Licensed under the MIT License.

using System;
using RabbitMQ.Client;
using RabbitMQ.Client.Events;

var factory = new ConnectionFactory { HostName = "localhost" };
using var connection = factory.CreateConnection();
using var channel = connection.CreateModel();

channel.QueueDeclare(queue: "hello",
                     durable: false,
                     exclusive: false,
                     autoDelete: false,
                     arguments: null);

var consumer = new EventingBasicConsumer(channel);

consumer.Received += (model, ea) =>
{
    Console.WriteLine(Convert.ToHexString(ea.Body.ToArray()));
};

string tag = channel.BasicConsume(queue: "hello",
                     autoAck: true,
                     consumer: consumer);

try
{
    Console.ReadLine();
}
finally
{
    channel.BasicCancel(tag);
}
