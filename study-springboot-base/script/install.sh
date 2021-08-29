#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyspringbootbase.service /etc/systemd/system/
sudo systemctl enable studyspringbootbase
sudo systemctl start studyspringbootbase