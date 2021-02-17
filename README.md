# TeltechEmployees


## About The Project

Project is written in **Kotlin** following best practices. **Clean architecture + "MVVM"** is used as an architectural pattern. Communication flow is as follows:

`Activity/Fragment (View) -> ViewModel -> Usecase -> Source (Repository) -> Service (API)`

Extra information:

* Single activity app with two fragments

* Project is using **modularization** for separation of concerns and **Koin** for dependency injection. 

* `ViewBinding` is used for accessing UI fields.

* **Retrofit** is used for fetching API data. 

* Image loading is handled by **Glide**.

* **Swipe to refresh** mechanism is implemented on the master employees screen.

* **Internet connection indication** is implemented using **ConnectivityManager** network callback. When a user loses Internet connection, red bar is animated on the top of the screen. When a user regains Internet connection, red bar turns to green and collapses.

* **Localization** example is present in the `core` project module.

* **Event tracking** example is wrapped in `Analytics` class.


## Features

### Master screen
Rendering employees list screen with their personal names, roles and images. It is possible to refresh the list by swiping `RecyclerView`. This screen has several states:
1. `LoadingViewState` - waiting for data to be fetched. Progress bar is rendered as a loading indicator
2. `ErrorViewState` - if any error occurrs during data fetching, user is notified and can retry the data request by clicking on the rendered button.
3. `EmptyViewState` - empty employees list state which renders *No employees* text on the screen.
4. `EmployeesListViewState` - list of employee items rendered in `RecyclerView`.

### Details screen
By clicking on a `RecyclerView` item, user gets routed to the details screen. There is more information about the certain employee and available button for return to the main screen. No other functionalities is currently present on that screen.


### Connection bar
This small fragment is rendered when it gets notified that there is no interent connection. Whole concept behaves as a state machine with multiple states:
1. `INITIAL`
2. `DISCONNECTED`
3. `CONNECTING`
4. `CONNECTED`

When user enters the app, first state is `INITIAL`. If user phone loses internet connection, `DISCONNECTED` state is published and connection bar appears on the screen. If user regains connection, transitional `CONNECTING` state gets published which changes connection bar color and text and starts a timer of 3 seconds. After 3 seconds, `CONNECTED` state gets published which will start the collapsing animation and removing connection bar from the screen.


## Architecture
[Image](https://imgur.com/DgFVJWc)

Where to start...The concept is fairly simple, `ViewState` is an object that represents UI state at a certain point in time. `View` (Activity/Fragment) subscribes to the *communication channel* and observes `ViewState` changes. `ViewModel` coordinates business data from `Usecases` and prepares `ViewState` object for rendering. When a `ViewState` object preparation is finished, it gets published to the *communication channel* which notifies `View` that new view state needs to be rendered on the UI. `Usecase` is a pure business logic class which should be named properly and by name signature desribe what is its role (e.g. `QueryAllEmployees`). Use cases can be combined and shared between in order to encapsulate whole domain layer. `Source` is another name for `Repository` and its role is to expose network/database data to use cases. In this type of classes, there could be some reactive logic present based on the network layer complexity. Also, `Source` should map API data to domain readable data. `Service` class needs to expose all network communication using Retrofit annotations and associated mechanisms.

It is important to emphasize that all data processing is handled by the **background thread (Scheduler)** except for rendering UI. `ViewModel` publishes `ViewState` changes on Android main thread. 

### TLDR;
* `ViewState` - an object that represents UI state (`View`) at a certain point in time. There could be several `ViewState` objects for one screen (e.g. empty data, error and happy-path screens).
* `View` (Activity/Fragment) - subscribes to the *communication channel* and observes `ViewState` changes that need to be rendered
* `ViewModel` - coordinates business data from `Usecases`, prepares `ViewState` object for rendering and publishes `ViewState` changes.
* `Usecase` - pure business logic class which defines the project domain
* `Source` (`Repository`) - exposes network/database data to use cases.
* `Service` - exposes all network communication using Retrofit annotations and associated mechanisms

### BaseViewModel
This class is essential for this type of architecture. It has several roles:
1. Coordinates all `ViewState` subscriptions and puts them to map. `ViewState` is key, `ViewStatePublisher` is value. `View` has a reference to `ViewModel` and can subscribe on all `ViewState`s that could be possibly rendered at any point in time.
2. Subscriptions are added to composite disposable in order to stay alive while the `ViewModel` is alive.
3. Clears composite disposable on clearing `ViewModel`.
4. Handles navigation because it has a reference to `RoutingActionsDispatcher` which is described next.


## Navigation
Navigation is handled by the `Router`. This class has a notion of the fragment manager and verifies fragment transactions. Activity registers as a **routing actions consumer** which means that all routing actions are consumed while the activity is resumed and if not (app is in background and routing action triggers in a reactive manner), routing actions are queued and flushed once the consumer registers again (app returns to foreground). `BaseViewModel` has a reference to `RoutingMediator` which exposes dispatching routing actions.

## Modularization
There are 3 types of modules in this project: `feature`, `lib` and `common`. 
1. Feature module wraps data needed for rendering UI (Activity/Fragment, ViewState, ViewModel and resources). 
2. Lib module acts as a library and wraps whole business logic for one (or more shared) feature/s. 
3. Common module wraps key application structural components (base classes, util classes, extensions or navigation classes). In this project there are 3 common modules: `core`, `coreui` & `navigation`

* For example, if you need to render a list of employees, your feature module would be called something like `employees` and library module `employeeslib`. (I was using slightly different naming in this project because of scoped feature).

The key part is that feature module has a dependency on lib module (getting business data for UI rendering) and lib module does not have a notion of feature module. Common modules are used where needed.

## Dependency injection
By using Koin, definitions are described in **modules**. A module is a logical space which helps in organizing definitions and it complements nicely with modularization strategy. 
**Every project module has one Koin module which encapsulates all the related definitions**. Definitions are lazy, and then are resolved only when a a component is requesting it.

Koin is initialised in the application class by collecting all module declarations. Because of that, `app` module knows for every project module.

## Unit testing
As an example, there is one Unit test called `EmployeesSourceTest` which consists of multiple testing cases that are testing possible incoming data from the network and mapping that data to application domain.

## Comment section
Master-detail flow is implemented using `Parcelable` data object that gets bundled as an argument between the two fragments. Personally, I don't like this implementation and would be more "satisfied" if there was some kind of unique distinction between the employees (like ID number). Then I would be free to implement the business logic layer as needed, e.g. using database for local data persistance and calling `QueryEmployeeById` or firing another API call which would provide most recent data on certain employee's details. With that approach I would need to send just an ID number between fragments and be sure of data consistence and freshness.

Regarding nullable types in Kotlin, it would be best to use KotlinX serialization for network data parsing but I didn't really have time to go through the documentation yet.



