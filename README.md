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
- Retrofit used for network api call
- Coroutines used to handle parallel api call and processing (without blocking main thread)
- For localstorage jetpack >> room framework used
- Databinding used to get ui updated as soon as api data and data processed

## Explanation

    

