#include <iostream>
#include <stdlib.h>
#include "tombstones.h"

using namespace std;

void Error(const char *text)
{
   std::cout << "ERROR: " << text << std::endl;
   exit(-1);
}

int main(int argc, char **argv)
{
   Ptr<int> foo(new int(12));
   Ptr<int> tmp((int*)NULL);
   Ptr<int> bar = tmp;

   if (foo == 0)
   Error("Foo shouldn't be null!");
   if (bar != 0)
   Error("Bar should be null!");
   bar = new int(12);
   if (foo == bar)
   Error("Foo and bar are distinct pointers!");
   if (*foo != *bar)
   Error("Foo and bar should have the same value here!");

   release(foo);
   release(bar);
   cout << "foo1: OK" << endl;
   return 0;
}
