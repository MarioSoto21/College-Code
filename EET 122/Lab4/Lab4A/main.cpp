#include <iostream>
using namespace std;
int main()
{

    double x, bonus;
    cout<< "Enter the salesperson's monthly earning: ";
    cin>>x;
        if(x>9000)
    {
        bonus=x*.078;
    }
   else if(x>4200)
   {
       bonus=x*.062;
   }
   else if(x<=4200)
   {
       bonus=x*.055;
   }
    cout.setf(ios::fixed);
    cout.setf(ios::showpoint);
    cout.precision(3);
    cout<<"The bonus pay is: "<<bonus<<endl;
    return 0;
}
