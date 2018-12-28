# TMDbAPI Android App

## Summary
A simple android app that lists upcoming movies from the TMDb API and display its details.

## Architecture
The app is built with MVVVM architecture and rxAndroid. It uses Retrofit Library for http requests and Glide Library for image downloading and display.

## Features
- Uses ViewModel architecture component
- Uses Observer and Livedata to display updated information on screen
- loads next page when reaching bottom
- Menu option to change environment between DUMMY and REMOTE
- Glide implementation to load images from remote
- Click on item launches Movie Detail Screen
- recycler list made with grid layout of 3 columns
- Swipe to refresh list
- UI watermark for dummy list
- Menu option to change environment available only for debug Build Type
- Swipe to refresh loading icon used for dummy and remote loading actions
- Search field that filters listed items

## Build Instructions
- Checkout "development" branch
- Run build type "debug"

## Third Party Libraries/Dependencies
* [Glide](https://github.com/bumptech/glide) for downloading, caching and displaying movie posters images given their url address
* [Retrofit2 Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) for deserializing JSON files into objects 
* [Retrofit2 Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
  for intercepting and logging all request and response made using retrofit 
* [Retrofit2 RxJava2 Adapter](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2) Used for providing http responses back to requestor in a reactive manner
* [RxAndroid: Reactive Extensions for Android](https://github.com/ReactiveX/RxAndroid) Android specific bindings for RxJava 2
* [RxJava: Reactive Extensions for the JVM](https://github.com/ReactiveX/RxJava) Java VM implementation of Reactive Extensions
* [RxKotlin: Kotlin Extensions for RxJava](https://github.com/ReactiveX/RxKotlin) adds convenient extension functions to RxJava
  
## Limitations/Known issues
- Genres names are statically implemented and should be queried from TMDb API in future releases
- Image's format URL are statically implemented and should be queried from TMDb API in future releases
- Search feature only ilter local results but should be able to query remote in future releases

## Screenshots
![movie list](https://github.com/yuricfurusho/TMDbAPI/blob/development/screenshots/device-2018-12-28-154633.png)
![movie details](https://github.com/yuricfurusho/TMDbAPI/blob/development/screenshots/device-2018-12-28-154646.png)
