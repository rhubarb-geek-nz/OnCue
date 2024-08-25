// Copyright (c) 2024 Roger Brown.
// Licensed under the MIT License.

using System;
using System.IO;
using System.Threading.Tasks;
using MQTTnet;
using MQTTnet.Client;

string broker = "localhost";
int port = 1883;
string clientId = Guid.NewGuid().ToString();
string topic = "hello";

var factory = new MqttFactory();
var mqttClient = factory.CreateMqttClient();

var options = new MqttClientOptionsBuilder()
    .WithTcpServer(broker, port)
    .WithClientId(clientId)
    .WithCleanSession()
    .Build();

var connectResult = await mqttClient.ConnectAsync(options);

if (MqttClientConnectResultCode.Success != connectResult.ResultCode)
{
    throw new Exception($"Failed to connect to MQTT broker: {connectResult.ResultCode}");
}

await mqttClient.SubscribeAsync(topic);

mqttClient.ApplicationMessageReceivedAsync += e =>
{
    Console.WriteLine(Convert.ToHexString(e.ApplicationMessage.PayloadSegment));
    return Task.CompletedTask;
};

try
{
    using (Stream s = Console.OpenStandardInput())
    {
        using (StreamReader reader = new StreamReader(s))
        {
            await reader.ReadLineAsync();
        }
    }
}
finally
{
    await mqttClient.UnsubscribeAsync(topic);
}

await mqttClient.DisconnectAsync();
