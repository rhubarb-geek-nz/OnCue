// Copyright (c) 2024 Roger Brown.
// Licensed under the MIT License.

using System.Text;
using Amqp;

Address address = new Address("amqp://guest:guest@localhost:5672");
Connection connection = new Connection(address);
Session session = new Session(connection);

Encoding encoding = Encoding.UTF8;
Message message = new Message();
byte[] ba = encoding.GetBytes("Hello AMQP!");
var data = new Amqp.Framing.Data();
data.Binary = ba;
message.BodySection = data;
SenderLink sender = new SenderLink(session, "sender-link", "hello");
sender.Send(message);

sender.Close();
session.Close();
connection.Close();
