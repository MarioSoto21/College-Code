#include <iostream>

using namespace std;

int main()
{
  int total_num=5, i=0, element, sum=0, average;

  cout<<"Enter 5 integer number and press enter"<<endl;

    do
  {
      cin>>element;
      sum=sum+element;
      i++;
      average=sum/total_num;
  }
    while (i<total_num);


  cout<<"The average value is: "<<average<<endl;

  return 0;

}
