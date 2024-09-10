#include <iostream>

using namespace std;

int main()
{
int number;
cout<<"Enter the integer number: ";
cin>>number;
int n = number%2;
if (n==0)
{
cout<<"It is an even number";
}
else
{
cout<<"It is an odd number";
}
return 0;
}
