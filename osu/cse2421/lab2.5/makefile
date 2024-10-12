# Copyright 2024 Neil Kirby
# If you edit it, put your name here and next to your edits.

CFLAGS= -std=c99 -pedantic -Wformat -Wreturn-type 
LDFLAGS=-L. -Wl,--copy-dt-needed-entries
LIBS=-lbko -l:libncurses.so.5 -lm

all:  tags headers lab2


headers: *.c tags
	./headers.sh

tags: *.c
	ctags -R .


# this is our master compiler rule to generate .o files.
# It needs all 4 warnings

%.o:%.c *.h
	gcc $(CFLAGS) -g -c $< -o $@


lab2: lab2.o input.o output.o sim.o n2.o
	gcc -g -o $@ $^  $(LDFLAGS) $(LIBS)


# Prototypes go here


# Zip target goes here.  
# If it doesn't self test lab2, your lab is late.


