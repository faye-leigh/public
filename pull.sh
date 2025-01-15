#!/bin/bash

# Pull changes
kitty sh -c '
    echo -e "Pulling changes:\n"  
    git pull
    echo -e "\nFinished"
    read -p "Press enter to exit: " wait
'
