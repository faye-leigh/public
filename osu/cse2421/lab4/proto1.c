/* Copyright 2024 Faye Leigh */
/*    OSU CSE 2421 - AU24    */

/* Proto 1: terse insert     */

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

// #include "libll.h"

typedef struct Node {
    struct Node *next;
    void        *data;
} Node;

static bool ball_order() {}

void        insert(Node *newnode, Node **p2p2change) {
    while (*p2p2change != NULL && (**p2p2change).data > newnode->data) {
        p2p2change = &((**p2p2change).next);
    }
    newnode->next = *p2p2change;
    *p2p2change   = newnode;
}

int main() {
}
