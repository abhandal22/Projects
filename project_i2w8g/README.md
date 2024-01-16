# Movie Organizer

## The Future of Organization

This is the Movie Organizer project by Arshvir Bhandal. This application's purpose is to help people organize their 
movies. It will do this by allowing movie fans to keep track of the movies/TV shows they watched. It will allow them to 
create a watchlist of movies and/or TV shows. It will also allow people to leave ratings on movies/TV shows from **0** 
(_haven't watched it_) to **10** (_the best movie ever_). It will also allow you to see all movies of a certain genre in
the database with ease.

The Movie Organizer application can be used by anyone. Though it is targeted at movie goers, readers can make use of 
these features as well though I plan on using it mostly for film. This project is interesting to me for more than one
reason. One I really enjoy watching movies and TV shows, but I find myself wanting to watch so many things I tend to 
forget other movies I plan on watching. So to combat this I think it's a good idea to create a way to keep track of 
everything I want to watch on list, so I don't forget. I also just like organized lists, so this project is what I feel 
is right for me.

## User Stories

- As a user, I want to be able to enter/change a rating for every movie I add to the list.
- As a user, I want to be able to add movies to a plan to watch list/already watched list.
- As a user, I want to be able to see all movies in a certain genre in the plan to watch list and already watch list 
  with a simple search.
- As a user, I want to see how many movies I have watched.
- As a user, I want to see how many movies I want to watch.
- As a user, I want the ability to remove movies from either the plan to watch list or already watched list.
- As a user, I want the code to account for duplicates in either list.
- As a user, I want to be able to see my plan to watch list and already watched list.
- As a user, I want to be able to save both my plan to watch list and already watched list.
- As a user, I want to be able to reload both plan to watch list and already watched list from previous uses and 
continue from where I left off.

### Phase 4: Task 2
Sun Nov 21 12:51:15 PST 2021
A movie was added to already watched list

Sun Nov 21 12:51:17 PST 2021
Already watched list was displayed

Sun Nov 21 12:51:20 PST 2021
A movie was removed form already watched list

Sun Nov 21 12:51:22 PST 2021
Already watched list was displayed

Sun Nov 21 12:51:27 PST 2021
Already watched list was loaded

Sun Nov 21 12:51:27 PST 2021
A movie was added to already watched list

Sun Nov 21 12:51:27 PST 2021
A movie was added to already watched list

Sun Nov 21 12:51:27 PST 2021
A movie was added to already watched list

Sun Nov 21 12:51:28 PST 2021
Already watched list was displayed

Sun Nov 21 12:51:33 PST 2021
Already watched list was saved

Sun Nov 21 12:51:35 PST 2021
Already watched list was displayed

Sun Nov 21 12:51:53 PST 2021
A movie was added to plan to watch list

Sun Nov 21 12:51:54 PST 2021
Plan to watch list was displayed

Sun Nov 21 12:51:58 PST 2021
A movie was removed from plan to watch list

Sun Nov 21 12:51:59 PST 2021
Plan to watch list was displayed

Sun Nov 21 12:52:03 PST 2021
Plan to watch list was loaded

Sun Nov 21 12:52:03 PST 2021
A movie was added to plan to watch list

Sun Nov 21 12:52:03 PST 2021
A movie was added to plan to watch list

Sun Nov 21 12:52:03 PST 2021
A movie was added to plan to watch list

Sun Nov 21 12:52:04 PST 2021
Plan to watch list was displayed

Sun Nov 21 12:52:09 PST 2021
Plan to watch list was saved

Sun Nov 21 12:52:10 PST 2021
Plan to watch list was displayed

### Phase 4: Task 3

- If I had more time I would introduce the composite pattern to my code. As I seem to have a lot of repetition with my
AlreadyWatchedList and PlanToWatchList.
  - As they are both lists of Movie objects with few differences.
  - Can be done by make MovieList the component and change it to a normal class instead of an interface with the 
    repeating functions (add, remove, etc.), a new class TypeOfWatchList the composite and 
    PlanToWatchList and AlreadyWatchedList the leaves. 
  - This could allow me to add more types of lists as well perhaps a favourites.
  - This can also allow for coupling to be reduced with the listeners and movie app
- I could introduce an abstract class to reduce repetition as well between my AlreadyWatchedList and PlanToWatchList
- My read and write class can also benefit from an abstract class to reduce repetitive code.
    