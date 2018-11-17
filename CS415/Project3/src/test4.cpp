#include <iostream>
#include <stdlib.h>
#include "tombstones.h"

using namespace std;

void Error(const char *text)
{
    cout << "ERROR: " << text << endl;
    exit(-1);
}

int main(int argc, char **argv)
{
    Ptr<int> foo;
    release(foo);
    Error("Didn't blow up when releasing uninitialized pointer!");

    return 0;
}
