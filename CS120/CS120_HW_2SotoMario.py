# This is supposed to convert number into a (dollar,cent) format
# Creator: Mario Soto
# Date: 09/28/2021
# Course: C120

totalAmount = input("Please input number after decimal point")
x  = int(totalAmount)
totalAmount = input("Please input number after decimal point")
y = int(totalAmount)

print( x, "dollars", "and", y, "cents")



amount = float( input("please input a dollar amount: "))
dollar = int(amount)
cent = int((amount - dollar) *100)
print("your input"+ str(amount) + "dollars ="+ str
      (dollar) + " dollars and " + str(cent)+ " cents")
