#include<stdio.h>
#include<stdlib.h> 
#include<time.h>  
#include<string.h>

#include"Header.h"

// 追加字符串, 但在使用时要注意src与des的关系
void append(char* src, char* des, int src_len, int des_len) {

	char* ps = src;
	char* pd = des;

	while (src_len > 0) {

		src_len--;

		*((pd++) + des_len) = *ps++;

	}

}