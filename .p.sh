#!/bin/bash

git config --global user.email "faye@fayeleigh.com"
git config --global user.name "L13"

git add .
git commit -a -m "update from laptop"
git push

kitty -e echo test