# Journey Android Coding Test 

1. Your task is to create a native Android app. 
2. If you can, please use Kotlin, MVVM and Android Jetpack. 
3. Use the endpoints at https://jsonplaceholder.typicode.com
4. Display posts in a list.
5. Open a detailed view with associated comments when user selects an item on the posts list.
6. Save the data into local storage.

## Nice to have:

1. Search functionality on the posts list and comments list.

## Screenshots

![List Of Posts Screen](https://github.com/cyclewala/JouneyAssignment/blob/main/screenshots/Screenshot_1.png)
![Post Derail Screen](https://github.com/cyclewala/JouneyAssignment/blob/main/screenshots/Screenshot_2.png)
![Search Post Screen](https://github.com/cyclewala/JouneyAssignment/blob/main/screenshots/Screenshot_3.png)
![Search Comment Screen](https://github.com/cyclewala/JouneyAssignment/blob/main/screenshots/Screenshot_4.png)

## Followed below points to achieve result

- MVVM design pattern followed
- Jetpack Components : Room Framework (for local storage), Hilt Framework (dependency injection),
  Navigation Component (screen navigations)
- Retrofit used for network api call
- Coroutines used to handle parallel api call and processing (without blocking main thread)
- For localstorage jetpack >> room framework used
- Databinding used to get ui updated as soon as api data and data processed

## Explanation

1. Splash Screen: This app fetches posts from this api end
   point : https://my-json-server.typicode.com/cyclewala/posts-data/db and stores data locally using
   room framework. this api is getting called on app launch from splash screen independently and
   lands to post list screen.

2. Posts Screen: Here we observes data change in posts entities in local db and as soon as data is
   inserted into db. screen gets notified and posts list gets rendered.

3. Post Detail Screen: Here we get selected post entity to post list screen and based on post id we
   fetch related comments from local db and show them in list view.

4. Post Search Screen: When user tap on search icon on Post list screen, user lands on this screen.
   here user can search posts based on post’s title and description. as soon as you enter the text,
   data will be searched from local db and get instantly populated on screen.

5. Comment Search Screen: When user tap on search icon on Post Detail Screen >> Comment list search
   icon, user lands on this screen. here user can search comment based on selected postId and
   comment’s body text. as soon as you enter the text, data will be searched from local db and get
   instantly populated on screen.

Following testing has been covered briefly

1. DataBaseTesting: This verifies that what we entered in db it gets fetched from given fetch query.
2. NetworkAPITesting: This verifies that network api result is received successfully and it has
   expected posts.
3. ViewModelTesting: This verifies that all data we fetch from local db for rendering list on
   screen.

