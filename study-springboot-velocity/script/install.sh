#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyspringbootvelocity.service /etc/systemd/system/
sudo systemctl enable studyspringbootvelocity
sudo systemctl start studyspringbootvelocity