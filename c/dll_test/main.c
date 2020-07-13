#include<Windows.h>
#include<stdio.h>
#include<stdlib.h>

#include"Header.h"

//#include"encryptdll.h"

//#pragma comment(lib, "EncryptionDLL.lib")


int main() {

	char*(*MD5Encrypt)(char *) = NULL;

	HMODULE hdll = LoadLibraryA(("Md5.dll"));

	if (hdll == NULL)
	{
		system("error load");
	}

	MD5Encrypt = (char*(*)(char *))GetProcAddress(hdll, "MD5Encrypt");

	if (MD5Encrypt != NULL) {

		char* ok = MD5Encrypt("Hello everyone!");
		printf("%s", ok);
	
	}

	printf("Hello world!");

	return 0;
	
}