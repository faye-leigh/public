#!/bin/bash

# Push changes
kitty sh -c '
    echo -e "Pushing changes:\n" 
    git add . 
    git commit -a -m "update from laptop" 
    git push
    echo -e "\nFinished"
    read -p "Press enter to exit: " wait
'
