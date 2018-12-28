# TMDbAPI
## Summary
Android App that lists Upcoming movie titles from the TMDb API

## Architecture
The app is built with MVVVM architecture and rxAndroid. It uses Retrofit Library for http requests and Glide Library for image download display.

## Build Instructions
- Checkout "development" branch
- Run build type "debug"

## List of third­party libraries used and short description of why/how they were used
- android.arch.lifecycle:extensions:1.1.1'
  - Used ViewModel, Observer and LiveData
- implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
  - Used to work with kotlin language
- com.android.support:appcompat-v7:28.0.0'
  - Used for searchView components, fragments and many other components back supported 
- com.android.support.constraint:constraint-layout:1.1.3'
  - Used to build each layout as it is the most recommended way to organize layout files.
- com.android.support:recyclerview-v7:28.0.0'
  - Used to display list of upcoming movies
- com.github.bumptech.glide:glide:4.8.0'
  - Used to display images given url address os movie posters
- com.squareup.okhttp3:logging-interceptor:3.11.0'
  - Used for logging http requests and responses
- com.squareup.retrofit2:adapter-rxjava2:2.4.0'
  - Used for providing http responses back to requestor
- com.squareup.retrofit2:converter-gson:2.4.0'
  - Used for deserializing JSON files into objects 
- com.squareup.retrofit2:retrofit:2.4.0'
  - Used for making http requests
- io.reactivex.rxjava2:rxandroid:2.1.0'
  - Used for providing http responses back to requestor
- io.reactivex.rxjava2:rxjava:2.2.3'
  - Used for providing http responses back to requestor
- io.reactivex.rxjava2:rxkotlin:2.2.0'
  - Used for providing http responses back to requestor
- junit:junit:4.12'
  - Used for testing
- com.android.support.test:runner:1.0.2'
  - Used for testing
- com.android.support.test.espresso:espresso-core:3.0.2'
  - Used for testing
  
## Limitations/Known issues
- Genres names are statically implemented and should be queried from TMDb API in future releases
- Image's format URL are statically implemented and should be queried from TMDb API in future releases
- Search feature only ilter local results but should be able to query remote in future releases

