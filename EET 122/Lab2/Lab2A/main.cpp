#include <iostream>
using namespace std;
int main()
{

double sale_price=645.00, sale_discount=0.15;
double discount_amount, total_price;

discount_amount= sale_price*sale_discount;
total_price=sale_price-discount_amount;

cout.setf(ios::fixed);
cout.setf(ios::showpoint);
cout.precision(2);
cout<<"the total price is: "<<total_price<<endl;
return 0;
}
