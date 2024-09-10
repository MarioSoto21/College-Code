#include <iostream>
#include <math.h>
#include <stdio.h>
using namespace std;
int main()
{
double area, r, h;
cout<< "Enter the value of radius: ";
cin >> r;
cout << "Enter the value of height: ";
cin >> h;
float pi= 3.14;
double rs= pow(r,2);
double hs= pow(h,2);
area= pi*r*(r+sqrt(rs+hs));
cout<<"The area is:"<<area;
return 0;
}
