# rhubarb-geek-nz/OnCue
Playground for queues

## Introduction

This is a playground for simple code using queues. The principal being start from something that works.

## MQTT

[MQTT](https://en.wikipedia.org/wiki/MQTT) is a lightweight publish and subscribe mechanism.

### eclipse-mosquitto

[Mosquitto Docker](docker/mosquitto/Dockerfile) for running a local MQTT server in docker. Run with port 1883 published.

### MQTTnet

[Dotnet receiver](dotnet/MqttReceiver/Program.cs) example.

[Dotnet sender](dotnet/MqttSender/Program.cs) example.

### org.eclipse.paho.client.mqttv3

[Java receiver](java/mqttrecv/src/main/java/nz/geek/rhubarb/mqttrecv/Main.java) example.

[Java sender](java/mqttsend/src/main/java/nz/geek/rhubarb/mqttsend/Main.java) example.

## AMQP

[AMQP](https://en.wikipedia.org/wiki/Advanced_Message_Queuing_Protocol) is a message-orientated middleware protocol.

### rabbitmq

[RabbitMQ Docker](docker/rabbitmq/Dockerfile) for running a local AMQP server in docker. Run with port 5672 published.

### AMQPNetLite

[Dotnet receiver](dotnet/AmqpReceiver/Program.cs) example.

[Dotnet sender](dotnet/AmqpSender/Program.cs) example.

### RabbitMQ.Client

[Dotnet receiver](dotnet/RabbitMQReceiver/Program.cs) example.

[Dotnet sender](dotnet/RabbitMQSender/Program.cs) example.

### com.rabbitmq/amqp-client

[Java receiver](java/rabbitmqrecv/src/main/java/nz/geek/rhubarb/rabbitmqrecv/Main.java) example.

[Java sender](java/rabbitmqsend/src/main/java/nz/geek/rhubarb/rabbitmqsend/Main.java) example.
