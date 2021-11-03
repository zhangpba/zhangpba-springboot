#!/bin/bash
sudo systemctl stop studyzookeeperconsumer
sudo systemctl disable studyzookeeperconsumer
sudo rm -rf /etc/systemd/system/studyzookeeperconsumer.service
