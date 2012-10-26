Scenario:  Should distribute balance according to participant shares
 
Given Participant A with 3 shares
Given Participant B with 1 shares
When the balance is distributed
Then Participant A has 7 euros and 50 cents 
And Participant B has 2 euros and 50 cents 
 
