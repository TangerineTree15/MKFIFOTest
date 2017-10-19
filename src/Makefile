CC=gcc
JCC=javac
FLAGS= -Wall -O2
JFLAGS= -g -verbose

all: cRead cWrite javaRead javaWrite 
cRead: ReadFIFO_C.c 
	$(CC) $(FLAGS) -o cRead ReadFIFO_C.c

cWrite: WriteFIFO_C.c 
	$(CC) $(FLAGS) -o cWrite WriteFIFO_C.c

javaRead: ReadFIFOJava.java 
	$(JCC) $(JFLAGS) ReadFIFOJava.java $(LIBS)

javaWrite: WriteFIFOJava.java 
	$(JCC) $(JFLAGS) WriteFIFOJava.java $(LIBS)
# mkfifo testtemp



.PHONY:clean
clean:
	rm -f cRead cWrite gen_csv *.class testtemp
