#include <iostream>
#include <stdlib.h>
#include "tombstones.h"

using namespace std;

void Error(const char *text)
{
    cout << "ERROR: " << text << endl;
    exit(-1);
}

void rec(Ptr<int> &foo, int n)
{
    Ptr<int> bar(foo);

    if (n == 0)
	*bar = 100;
    else
    {
	rec(bar, n-1);
	if (n == 10)
	{
	    if (*foo != 100)
		Error("Linking of pointers not correct!");
	    release(foo);
	}
    }
}

int main(int argc, char **argv)
{
    Ptr<int> foo(new int(0));
    rec(foo, 10);
    Ptr<int> bar(foo);
    Error("Didn't complain about use of dangling pointer foo!");

    return 0;
}
