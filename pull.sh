#!/bin/bash

# Pull changes
kitty sh -c '
    echo -e "Pulling changes from faye-leigh/git:\n" 
    cd ~/git 
    git pull
    
    echo -e "Pulling changes from faye-leigh/documents:\n" 
    cd ~/documents 
    git pull

    echo -e "Pulling changes from faye-leigh/osu:\n" 
    cd ~/osu 
    git pull

    echo -e "Pulling changes from faye-leigh/music:\n" 
    cd ~/music 
    git pull

    echo -e "Pulling changes from faye-leigh/photos:\n" 
    cd ~/photos 
    git pull

    echo -e "\nFinished"
    read -p "Press enter to exit: " wait
'
