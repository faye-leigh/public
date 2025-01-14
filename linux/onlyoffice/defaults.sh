#!/bin/bash

# Push changes
kitty sh -c '
    echo -e "Setting default docx file:\n" 
    sudo cp new.docx /var/lib/flatpak/app/org.onlyoffice.desktopeditors/current/active/files/bin/opt/onlyoffice/desktopeditors/converter/empty/en-US/
        
    echo -e "Adding envelope template:\n" 
    sudo cp Envelope\ \#10.dotx /var/lib/flatpak/app/org.onlyoffice.desktopeditors/current/active/files/bin/opt/onlyoffice/desktopeditors/converter/templates/EN/Documents/

    echo -e "\nFinished"
    read -p "Press enter to exit: " wait
'
