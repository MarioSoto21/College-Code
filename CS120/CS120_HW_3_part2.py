x = float(input( "Number of ounces: " ) )

import math
math.floor(x)

if x <= 1:
    print( "30 cents")
elif x > 1:
    cost = math.floor(x)*25+30
    print(cost, "cents")
