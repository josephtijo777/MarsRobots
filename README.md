# Exercise
Your task is to write the first screen of a new app all about Mars. This screen will show a gallery of images returned from the [NASA Image and Video Library API](https://api.nasa.gov/api.html#Images).

Here is the API call you should use to obtain image links and metadata: https://images-api.nasa.gov/search?q=mars&media_type=image 

In essence, for each item in `collection -> items` in the API response, we want you to display a card with the image found under `links -> href` and also show `data -> description` and `data -> date_created`.

A rough design can be found in the file `design.png` in the same directory as this readme (with placeholder images, obviously).

## Notes
Please don’t spend any more than 2–3 hours on this exercise. We don’t expect you to completely finish, but it would be great if you could get something working end-to-end and have an idea of what else you would do, given more time. Please write this down as notes when send us the exercise.

You may write your code in Kotlin or Java.

## What we're looking for
* The app must work offline, as best it can. It is up to you to decide how the app should behave in this situation.
* The ability to explain why you decided to implement something the way you did, what you explicitly decided not to do and what improvements you would make given more time.
* Good architectural principles. You should assume that this screen will form part of a larger project, with many screens.
* Readable, maintainable code.
* If you’re about to run out of time, add a few comments indicating what you were planning to do.
* Examples of how to test different parts of your codebase. For this assessment, you don’t have to test everything.
* Resilience to Android app component lifecycle changes.
* Use of open source libraries, where appropriate.

## General Overview

Application developed in Kotlin language.It follows MVVM Architecture with the Use of LiveData. Offline logic is handled with Room Database
Sample Unit test for Entity is added.  

## Technologies

* MVVM Architecture
* LiveData
* Databinding
* ViewModel
* Unit Test
* Dependency Injection
* RxJava
* Coroutine

## Libraries Used

* Retrofit2
* Glide
* Dagger 2
