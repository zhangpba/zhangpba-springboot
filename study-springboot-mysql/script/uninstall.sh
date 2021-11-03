#!/bin/bash
sudo systemctl stop studyspringbootmysql
sudo systemctl disable studyspringbootmysql
sudo rm -rf /etc/systemd/system/studyspringbootmysql.service
