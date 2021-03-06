// C program to implement one side of FIFO
// This side writes first, then reads
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
    int fd;
 
    // FIFO file path
    char * myfifo = "/tmp/sbfifo";
 
    // Creating the named file(FIFO)
    // mkfifo(<pathname>, <permission>)
    mkfifo(myfifo, 0666);
 
    char arr2[80];

    // Open FIFO for write only
    fd = open(myfifo, O_WRONLY);
    while (1)
    {
        // Take an input arr2ing from user.
        // 80 is maximum length
        fgets(arr2, 80, stdin);
 
        // Write the input arr2ing on FIFO
        // and close it
        write(fd, arr2, strlen(arr2)+1);
    }
    close(fd);
    
    return 0;
}
