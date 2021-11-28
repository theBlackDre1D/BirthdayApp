# BirthdayApp
  
  
Please use build flavor debug for run this app.  
  
## Overview  
I used my own base module from my projects. It contains some extensions functions, base classes for Activities, Fragments etc.  
  
App is not covered by tests and users are not ordered as sholud be. I ran out of the time.  
  
### Trades-off
- If I had more time I would make custom view for item used in RecyclerView for example. In modern project views are repeting across project so it would be reusable. You can check my repo Spot Guide V2 where I did it.  
- I would like to do it in Jetpack Compose but I don't have so much experience and probobly it would took me more time than 3 hours.  
- I would use styles for text in app to add size, font and style at the same time to text view. Usualy it repeats in projects too.  
- Add pagination to users at first screen using API. I checked documentation and it support it.  
- Order users on client side or API side.
- Fetch users with id's to better recognising unique users at UsersAdapterDiffUtils
