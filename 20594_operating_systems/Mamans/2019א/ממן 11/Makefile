CC=gcc
FLAGS = -Wall  -L./ -m32

ph: binsem.a ut.a ph.c 
	$(CC) $(FLAGS) ph.c -lbinsem -lut -o ph

binsem.a:
	$(CC) $(FLAGS)  -c binsem.c
	ar rcu libbinsem.a binsem.o
	ranlib libbinsem.a 
	rm -f binsem.o


ut.a:
	$(CC) $(FLAGS)  -c ut.c
	ar rcu libut.a ut.o
	ranlib libut.a 
	rm -f ut.o

clean:
	rm -f *.o 
	rm -f a.out
	rm -f *~
	rm -f ph
	rm -f *a 
