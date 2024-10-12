# Copyright 2024 Neil Kirby
# If you edit it, put your name here and next to your edits.
# Edited by Faye Leigh

CFLAGS= -std=c99 -pedantic -Wformat -Wreturn-type 
LDFLAGS= -L. -Wl,--copy-dt-needed-entries
# LIBS=-lbko -l:libncurses.so.5 -lm
LIBS= -lbko -llinkedlist -lm

all: tags headers


headers: *.c 
	./headers.sh

tags: *.c
	~/ctags -R .


# this is our master compiler rule to generate .o files.
# It needs all 4 warnings

%.o:%.c *.h
	gcc $(CFLAGS) -g -c $< -o $@

# Section added by Faye Leigh
# lab3: lab3.o input.o output.o text.o graphics.o sim.o collision.o n2.o
# 	gcc -g -o $@ $^  $(LDFLAGS) $(LIBS)

# Prototypes
proto1: proto1.o
	gcc -g -o $@ $^  $(LDFLAGS) $(LIBS)
proto2: proto2.o
	gcc -g -o $@ $^  $(LDFLAGS) $(LIBS)
text: text.o
	gcc -g -o $@ $^  $(LDFLAGS) $(LIBS)

# Zip target goes here.  
# If it doesn't self test lab2, your lab is late.
lab3.zip: makefile *.c *.h README_LAB3 n2.o *.a
	zip $@ $^
    # remove old install folder
	rm -rf install
    # create new install folder
	mkdir install
    # unzip lab files to install folder
	unzip lab2.zip -d install
    # self test make
	make -C install -r lab2
    # remove install folder
	rm -rf install
	