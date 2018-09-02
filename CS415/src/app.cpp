/*CS 415; Program #1; Name:
C++ program to demonstrate methods of passing arguments in function.
  Pass by value, Pass by reference, Pass by address.
*/

#include  <iostream>
using namespace std;

void swapByValue( int a , int b  );
void swapByRef  ( int &a, int &b );
void swapByAdr  ( int *a, int *b );

int main()
{
    int x = 10;
    int y = 20;

    cout << endl;
    cout << "Value before Swapping x:" << x << " y:" << y << endl;
    swapByValue( x , y  ); /*In call by value swapping does not reflect in calling function*/
    cout << "Value After  Swapping x:" << x << " y:" << y << endl << endl;

    cout << "Value before Swapping x:" << x << " y:" << y << endl;
    swapByRef( x , y  );  /*Swapping reflect but reference does not take space in memory*/
    cout << "Value After  Swapping x:" << x << " y:" << y << endl << endl;

    x = 50;
    y = 100;

    cout << "Value before Swapping x:" << x << " y:" << y << endl;
    swapByAdr( &x , &y  ); /*Swapping reflect but pointer takes space in memory*/
    cout << "Value After  Swapping x:" << x << " y:" << y << endl << endl;

    return 0;
}

void swapByValue( int a , int b  )
{
    int c;

    c = a;
    a = b;
    b = c;
}

void swapByRef( int &a , int &b  )
{
    int c;

    c = a;
    a = b;
    b = c;
}

void swapByAdr( int *a , int *b  )
{
    int c;

     c = *a;
    *a = *b;
    *b =  c;
}
