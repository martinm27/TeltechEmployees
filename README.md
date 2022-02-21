# CredentialsGenerator

## Installation instructions:
* Unzip the .zip file

* Open the unzipped source code in Android Studio or any other adequate IDEA. 
  
* Run the project when the Gradle synchronization finishes

* Alternatively, if you're a Terminal guy, navigate to root folder and build & install app with commands: gradlew assembleDebug & gradlew installDebug

## Usage instructions:

When you launch the app, the first screen you land on is the home screen that should show the list of users.

[Image](https://imgur.com/KVwKkbW)

When you click the Generate New User button, new user is being generated and saved locally with an unique UUID. Also, first request is sent for the credential generation via RPC method.

[Image](https://imgur.com/XqYbDon)

If you click on that user, you are being navigated to the Credentials screen on which you can see every credential generated for that user.

[Image](https://imgur.com/t3TTAyr)   

When you click the Generate new credential button, new credential is being generated via RPC method and persisted in the local storage.

[Image](https://imgur.com/tn4djWU)

## Comment section

I didn't have a lot of time to work on this because of my tight schedule with the current company I work for, but I would like to give my opinion on all the requirements.

### Data persistence
I didn't do any local data saving logic i.e. when you delete the storage/app, everything disappears so this should be addressed:
1. If we want to persist the generated keys, we should send them either to remote server or doing the Data backup (by adopting some authentication via Google or something else).
2. If the keys persistence is not a problem, I wonder how you clear the data on the server for the "deleted" user? :)

### Security
Everything is sent in plaintext so there should be some authentication mechanism adopted for the system to work securely. (SSL/TLS or Google token-based authentication)

### Nice to haves

* Notifications - I would create notification channel on the app startup and once the credential is successfully generated, I would just use the NotificationManager to show the notification.
* Differentiate between the credentials that you have seen and the new ones - I would flag the newly generated credential with a boolean flag "alreadySeen" and save that locally in the database (false as default value). Once the user clicks the newly generated credential and navigates on the single credential display screen, I would override "alreadySeen" with true. There would be also some kind of a UI indication that the credential is new in the list view.
* Credential storage, so that you don't need to look for all of them every time. - This is implemented.
* A view to display a single credential - This could be done in 2 ways, either send a Credential object between fragments via Parcelable object or share primary ID between fragments (master & details) and use database for fetching the needed data to be shown on the UI.


## About The Project

Project is written in **Kotlin** following best practices. **Clean architecture + "MVVM"** is used as an architectural pattern. Communication flow is as follows:

`Activity/Fragment (View) -> ViewModel -> Usecase -> Source (Repository) -> Service (API)`

Extra information:

* Single activity app with two fragments

* Project is using **Koin** for dependency injection.

* `ViewBinding` is used for accessing UI fields.

## Architecture:
The concept is fairly simple, `ViewState` is an object that represents UI state at a certain point in time. `View` (Activity/Fragment) subscribes to the *communication channel* and observes `ViewState` changes. 
`ViewModel` coordinates business data from `Usecases` and prepares `ViewState` object for rendering. When a `ViewState` object preparation is finished, it gets published to the *communication channel* which notifies `View` that new view state needs to be rendered on the UI. 
`Usecase` is a pure business logic class which should be named properly and by name signature describe what is its role (e.g. `QueryUsers`). Use cases can be combined and shared between in order to encapsulate whole domain layer. 
`Source` is another name for `Repository` and its role is to expose network/database data to use cases. In this type of classes, there could be some reactive logic present based on the network layer complexity. Also, `Source` should map API data to domain readable data.

* `ViewState` - an object that represents UI state (`View`) at a certain point in time. There could be several `ViewState` objects for one screen (e.g. empty data, error and happy-path screens).
* `View` (Activity/Fragment) - subscribes to the *communication channel* and observes `ViewState` changes that need to be rendered
* `ViewModel` - coordinates business data from `Usecases`, prepares `ViewState` object for rendering and publishes `ViewState` changes.
* `Usecase` - pure business logic class which defines the project domain
* `Source` (`Repository`) - exposes network/database data to use cases.

### Navigation
Navigation is handled by the `Router`. This class has a notion of the fragment manager and verifies fragment transactions. Activity registers as a **routing actions consumer** which means that all routing actions are consumed while the activity is resumed.

### Dependency injection
By using Koin, definitions are described in **modules**. A module is a logical space which helps in organizing definitions and it complements nicely with modularization strategy.
Definitions are lazy, and then are resolved only when a a component is requesting it. Koin is initialised in the application class by collecting all module declarations.
