#!/bin/bash

# Push changes
kitty sh -c '
    echo -e "Pushing changes to faye-leigh/git:\n" 
    cd ~/git 
    git add . 
    git commit -a -m "update from laptop" 
    git push
    echo -e "\nFinished"
    read -p "Press enter to exit: " wait
' || konsole sh -c '
    echo "Pushing changes to faye-leigh/git:" 
    cd ~/git ; git add . 
    git commit -a -m "update from laptop" 
    git push 
    echo -e "\nFinished" 
    read -p "Press enter to exit: " wait
'
