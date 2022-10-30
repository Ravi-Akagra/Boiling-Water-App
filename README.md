# Boiling-Water-App
Boiling Water is an Android based clone of Rotten Tomatoes. I made the app using Kotlin and the backend using Python.
>More on Backend here: [Boiling-Water-Backend](https://github.com/Ravi-Akagra/Boiling-Water-Backend)

### Launching the App
When you first launch the app, you are greeted with Sign-In page. The page supports a Google sign-in.
>![signin-page1](https://user-images.githubusercontent.com/79597232/198893263-abc4ba3d-ba89-4489-bac6-fbd52f905c44.jpg)

Sign in using a google account. This is same as used in other websites and apps also.
Once signed in, the user remains signed in until either data is cleared or app is deleted or Until user is Logged-out.

### Home Page
Home page has the following structure
>![home-page1](https://user-images.githubusercontent.com/79597232/198894327-f9765cb2-11df-4ffb-9f19-b5c37dd50f59.jpg)

The data is recieved from the API (Check [Backend](https://github.com/Ravi-Akagra/Boiling-Water-Backend))
The home page supports pagination implemented using onScrollListener. Each movie tile, when clicked open the movie page. Home page also has the log-out button.

### Movie Page
Here's how the movie page looks
>![movie_page1](https://user-images.githubusercontent.com/79597232/198894593-ef867a7e-8855-489c-9d28-a6b3437d875e.jpg)

In future, I might add "Watch Trailer" functionality.

### Comments
Implemented comments using Firebase Firestore. Each movie is a collection, each comment being a document, which contains information like content, username, link to profile pic and time of comment.The comments for each movie is sorted such that latest comment comes on top.
Here's how Comments look
>![comments1](https://user-images.githubusercontent.com/79597232/198895240-f2a6a50f-0c92-4041-87c5-fc7f442e8cc4.jpg)

### Logout
Logout alert pops up when Logout button is clicked on Home screen
>![logout1](https://user-images.githubusercontent.com/79597232/198895271-f91cc8a0-77bc-4674-8e08-e3b042b0bb41.jpg)


