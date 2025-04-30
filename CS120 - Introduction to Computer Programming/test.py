temp_amount = 10.34
if '.' in temp_amount:
    amount = temp_amount.split('.')
    dollars = amount[0]
    cents = amount[1]
else:
    dollars = temp_amount
    cents = '00'
amt = num2eng.num2eng(dollars)
total = amt + 'and %s/100 Dollars' % cents
print (total)
