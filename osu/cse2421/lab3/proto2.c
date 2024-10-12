
#include <stdio.h>
#include <stdlib.h>
#include "libll.h"

bool first_letter(void *data1, void *data2){
    char *str1 = data1, *str2 = data2;
    return str1[0] < str2[0];
}

int main() {
    char *str1 = "Oliver", *str2 = "Qliver";
    void *data1 = str1, *data2 = str2;
    printf("%d\n", first_letter(data1, data2));

    void *list = NULL;
    insert(data1, data2, first_letter,1);
}
