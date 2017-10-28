// C program to implement one side of FIFO
// This side reads first, then reads
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
 
//mkfifo filename (mkfifo /tmp/sbfifo)
int main()
{
	printf("Running\n");
    int fd1;
 
    // FIFO file path
    char * myfifo = "/tmp/sbfifo";
 
    // Creating the named file(FIFO)
    // mkfifo(<pathname>,<permission>)
    mkfifo(myfifo, 0666);
 
    char str1[2000];
    char str2[2000];

	fd1 = open(myfifo,O_RDONLY);
    while (1)
    {
	    read(fd1, str1, 2000);
	    int str1len = strlen(str1);
	    int j=0;
	    for(int i=0;i<str1len;i++) {
	    	  str2[j++] = str1[i];
	    	  if(str1[i]== '\n') {
			printf("User1: %s", str2);
			j=0;
			memset(str2,0,2000);
	    	  }
	    }
	    
//         printf("User1: %s", str1);
        memset(str1,0,2000);
        memset(str2,0,2000);
    }
    close(fd1);
    
    return 0;
}