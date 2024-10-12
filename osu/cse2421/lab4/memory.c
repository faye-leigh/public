// Copyright 2024 Neil Kirby, not for distributioon without written permission
// memory management functions
#include "memory.h"

#include <stdio.h>
#include <stdlib.h>

#include "debug.h"

void *allocate_thing(size_t size) {
    static int count = 0;
    void *thing = malloc(size);
    if (thing == NULL) {
        if (TEXT)
            printf("ERROR: allocate_thing: allocation failed on %ld bytes\n",
                   size);
    } else {
        count++;
        if (TEXT)
            printf(
                "DIAGNOSTIC: allocate_thing: %ld bytes allocated for object "
                "#%d\n",
                size, count);
    }
    if (DEBUG) printf("DEBUG: allocate_thing: returning %p\n", thing);
    return thing;
}

void free_thing(void *thing) {
    static int count = 0;
    if (thing != NULL) {
        if (DEBUG) {
            printf("DEBUG: free_thing: freeing %p\n", thing);
            fflush(stdout);
        }

        free(thing);
        count++;
        if (TEXT) printf("DIAGNOSTIC: free_thing: %d objects freed\n", count);
    } else {
        if (TEXT) printf("ERROR: free_thing: attempt to deallocate NULL\n");
    }
}
