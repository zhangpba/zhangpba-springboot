#!/bin/bash
sudo systemctl stop studyzookeeperproducer
sudo systemctl disable studyzookeeperproducer
sudo rm -rf /etc/systemd/system/studyzookeeperproducer.service
