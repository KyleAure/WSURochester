#include <iostream>
#include <stdlib.h>
#include "tombstones.h"

using namespace std;

void Error(const char *text)
{
    cout << "ERROR: " << text << endl;
    exit(-1);
}

Ptr<int> &rec(int n, Ptr<int> foo)
{
    static Ptr<int> result(new int(*foo));
    if (0 == n)
	return result;
    result = (Ptr<int>&)(rec(n-1, foo));
    *result += 1;
    return result;
}

Ptr<int> &recref(int n, Ptr<int> &foo)
{
    if (0 == n)
	return foo;
    *foo += 1;
    return rec(n-1, foo);
}

void sub()
{
    Ptr<int> foo(new int(0));
    Ptr<int> bar(rec(100, foo));

    if (*bar != 100 || *foo != 0)
	Error("Foo or bar incorrect after rec()!");

    foo = recref(100, bar);
    if (*foo != 200)
	Error("Foo incorrect after recref()!");
    if (foo != bar)
	Error("Foo not an alias for bar after recref()!");

    release(foo);
}

int main(int argc, char **argv)
{
    sub();
    Error("Didn't blow up when leaking memory!");

    return 0;
}
