// Copyright (c) 2024 Roger Brown.
// Licensed under the MIT License.

using MQTTnet;
using MQTTnet.Client;
using MQTTnet.Protocol;
using System;

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

var message = new MqttApplicationMessageBuilder()
    .WithTopic(topic)
    .WithPayload("Hello, MQTT!")
    .WithQualityOfServiceLevel(MqttQualityOfServiceLevel.AtLeastOnce)
    .WithRetainFlag()
    .Build();

await mqttClient.PublishAsync(message);

await mqttClient.DisconnectAsync();
