#!/bin/bash
sudo systemctl stop studyspringbootbase
sudo systemctl disable studyspringbootbase
sudo rm -rf /etc/systemd/system/studyspringbootbase.service
