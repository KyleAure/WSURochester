#include <iostream>
#include <stdlib.h>
#include "tombstones.h"

using namespace std;

void Error(const char *text)
{
    cout << "ERROR: " << text << endl;
    exit(-1);
}

typedef struct _foo
{
    int a;
    int b;
    _foo(int x, int y) : a(x), b(y) {}
}
twoints;

int main(int argc, char **argv)
{
    Ptr<int> foo(new int(12));
    Ptr<twoints> bar(new twoints(12,12));

    if (*foo != bar->a || *foo != bar->b)
	Error("Foo, bar->a, and bar->b should all be 12");

    release(foo);
    release(bar);
    cout << "foo3: OK" << endl;
    return 0;
}
