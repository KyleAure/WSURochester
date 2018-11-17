#include <iostream>
#include <stdlib.h>
#include "tombstones.h"

using namespace std;

void Error(const char *text)
{
   cout << "ERROR: " << text << endl;
   exit(-1);
}

void sub()
{
   Ptr<int> foo(new int(12));
}

int main(int argc, char **argv)
{
   sub();
   Error("Didn't blow up when leaking memory!");

   return 0;
}
