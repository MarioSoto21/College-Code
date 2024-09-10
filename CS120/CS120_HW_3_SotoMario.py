# Python program that can accept inputs from user and make decision. 
# Creator: Mario Soto
# Date: 10/19/2021
# Course: CS120

#1.
x = float(input( "Number of ounces: " ) )

import math
math.floor(x)

if x <= 1:
    print( "30 cents")
elif x > 1:
    cost = math.floor(x-1)*25+30
    print(cost, "cents")
#2.
month = {
        1: "January",
        2: "February",
        3: "March",
        4: "April",
        5: "May",
        6: "June",
        7: "July",
        8: "August",
        9: "September",
        10: "October",
        11: "November",
        12: "Deember"}
n = int(input("Enter number of the month: "))
print(month[n])
