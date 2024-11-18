#!/bin/bash

# Push changes
kitty sh -c '
echo "test" | read test
echo $test

echo -e "\nFinished" 
read -p "Press any key to exit: " wait
'
