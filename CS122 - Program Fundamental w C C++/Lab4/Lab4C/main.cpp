#include <iostream>

using namespace std;

int main()
{
  int sum=0;

  cout<<"Numbers between 200 and 250, divisible by 7:"<<endl;

     while (sum<=0)
  {
      for(int i=200;i<250;i++) {
      if(i%7==0){
        cout<<i<<" ";
        sum=sum+i;
      }
     }
   }
  cout<<endl;



  cout<<"The sum is: "<<sum<<endl;

  return 0;

}
