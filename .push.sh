#!/bin/bash

kitty sh -c 'cd /home/faye/git ; git add . ; git commit -a -m "update from laptop" ; git push ; read test'

# kitty sh -c 'eval $(echo -e test) && read hold'
# kitty sh -c 'echo test && read hold'