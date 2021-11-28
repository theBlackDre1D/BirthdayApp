# BirthdayApp
  
  
Please use build flavor debug to run this app.  
  
## Overview  
I used my own base module from my projects. It contains some extensions functions, base classes for Activities, Fragments, etc.  
  
The app is not covered by tests and users are not ordered as should be. I ran out of time.  
  
### Trades-off
- If I had more time I would make a custom view for items used in RecyclerView for example. In a modern project, views are repeated across the project so it would be reusable. You can check my repo Spot Guide V2 where I did it.  
- I would like to do it in Jetpack Compose but I don't have so much experience and probably it would take me more time than 3 hours.  
- I would use styles for text in the app to add size, font, and style at the same time to text view. Usually, it repeats in projects too.  
- Add pagination to users at the first screen using API. I checked the documentation and it supports it.  
- Order users on the client-side or API side.
- Fetch users with id's to better recognize unique users at UsersAdapterDiffUtils
