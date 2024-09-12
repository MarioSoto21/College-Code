#include <iostream>
#include<string>
using namespace std;
int main()
{
string fname;
cout <<  "Enter the first name: ";
cin >> fname;

string lname;
cout << "Enter the last name: ";
cin >> lname;

string name = fname + lname ;

cout << "The concatenated string is:  " <<name<< endl;

using namespace std;

    string str = name;
    cout << "The length of the concatenated string is: " << str.size();



return 0;
}
