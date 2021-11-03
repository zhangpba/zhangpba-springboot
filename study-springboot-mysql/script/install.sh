#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyspringbootmysql.service /etc/systemd/system/
sudo systemctl enable studyspringbootmysql
sudo systemctl start studyspringbootmysql