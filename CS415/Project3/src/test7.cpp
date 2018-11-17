#include <iostream>
#include <stdlib.h>
#include "tombstones.h"

using namespace std;

void Error(const char *text)
{
    cout << "ERROR: " << text << endl;
    exit(-1);
}

void sub(Ptr<int> &foo)
{
    Ptr<int> bar(foo);
    Ptr<int> bat(bar);
    Ptr<int> qix(bat);
    Ptr<int> glorch(qix);

    *glorch = 100;
    if (*foo != 100)
	Error("Linking of pointers not correct!");

    release(glorch);
}

int main(int argc, char **argv)
{
    Ptr<int> foo(new int(0));
    sub(foo);
    *foo = 1000;
    Error("Didn't complain about use of dangling pointer foo!");

    return 0;
}
