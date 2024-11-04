#!/bin/bash

# Pull changes
kitty sh -c '
    echo -e "Pulling changes from faye-leigh/git:\n" 
    cd ~/git 
    git pull
    
    echo -e "\nFinished"
    read -p "Press enter to exit: " wait
'
