#!/bin/bash

# Install kitty terminal
kitty sh -c 'sudo dnf install -y kitty ; echo "Press enter to continue:" ; read wait'

# Configure kitty settings
kitty sh -c 'echo "Writing kitty.conf file:" ; echo "cursor_shape beam" > ~/.config/kitty/kitty.conf ; echo "touch_scroll_multiplier 5.0" >> ~/.config/kitty/kitty.conf ; echo "confirm_os_window_close 0" >> ~/.config/kitty/kitty.conf ; echo "Finished writing kitty.conf" ; printf "\nPress enter to exit:" ; read wait' 
