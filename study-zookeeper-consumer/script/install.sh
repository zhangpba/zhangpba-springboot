#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyzookeeperconsumer.service /etc/systemd/system/
sudo systemctl enable studyzookeeperconsumer
sudo systemctl start studyzookeeperconsumer