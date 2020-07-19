#include<stdio.h>
#include<stdlib.h> 
#include<time.h>  
#include<string.h>

#include"Header.h"

#pragma warning(disable:4996)


char main() {

    char* result = MD5Encrypt("Hello everyone!");

    for (int i = 0; i < strlen(result); i++) {

        printf("%c", result[i]);

    }

    return 0;

}

