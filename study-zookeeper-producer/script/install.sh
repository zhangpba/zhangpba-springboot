#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyzookeeperproducer.service /etc/systemd/system/
sudo systemctl enable studyzookeeperproducer
sudo systemctl start studyzookeeperproducer