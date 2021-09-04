#!/bin/bash
sudo systemctl stop studyspringbootvelocity
sudo systemctl disable studyspringbootvelocity
sudo rm -rf /etc/systemd/system/studyspringbootvelocity.service
