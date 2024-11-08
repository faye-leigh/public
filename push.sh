#!/bin/bash

# Push changes
kitty sh -c '
    echo -e "Pushing changes to faye-leigh/git:\n" 
    cd ~/git 
    git add . 
    git commit -a -m "update from laptop" 
    git push
    
    echo -e "Pushing changes to faye-leigh/documents:\n" 
    cd ~/documents
    git add . 
    git commit -a -m "update from laptop" 
    git push

    echo -e "Pushing changes to faye-leigh/photos:\n" 
    cd ~/photos
    git add . 
    git commit -a -m "update from laptop" 
    git push

    echo -e "Pushing changes to faye-leigh/music:\n" 
    cd ~/music
    git add . 
    git commit -a -m "update from laptop" 
    git push

    echo -e "Pushing changes to faye-leigh/osu:\n" 
    cd ~/osu
    git add . 
    git commit -a -m "update from laptop" 
    git push

    echo -e "\nFinished"
    read -p "Press enter to exit: " wait
'
