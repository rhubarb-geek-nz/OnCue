# Copyright (c) 2024 Roger Brown.
# Licensed under the MIT License.

FROM eclipse-mosquitto:2

RUN ls /mosquitto/config/mosquitto.conf

RUN echo allow_anonymous true >> /mosquitto/config/mosquitto.conf

RUN echo listener 1883 0.0.0.0 >> /mosquitto/config/mosquitto.conf
