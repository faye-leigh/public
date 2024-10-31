#!/bin/bash

# Push changes

kitty sh -c '
    echo "Pushing changes to faye-leigh/git:" ; 
    cd ~/git ; git add . ; 
    git commit -a -m "update from laptop" ; 
    git push ; 
    # echo -e "\nFinished\n\nPress enter to exit:" ; 
    read -p -e "Finished\n\nPress enter to exit:" wait
'