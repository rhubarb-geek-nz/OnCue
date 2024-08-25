// Copyright (c) 2024 Roger Brown.
// Licensed under the MIT License.

using System;
using Amqp;

Address address = new Address("amqp://guest:guest@localhost:5672");
Connection connection = new Connection(address);
Session session = new Session(connection);
ReceiverLink receiver = new ReceiverLink(session, "receiver-link", "hello");

Message message = receiver.Receive();
byte[] arrayBody = message.Body as byte[];
if (arrayBody != null)
{
    Console.WriteLine(Convert.ToHexString(arrayBody));
}
else
{
    Console.WriteLine($"{message.Body}");
}
receiver.Accept(message);

receiver.Close();
session.Close();
connection.Close();
