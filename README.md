# bikeridz

Wouldn’t it be nice if there was an app that knows where you are and finds the closest bikeshop when
 you break down on a ride? Bike Ridz (pronounced “bike rides”) is a multi-utility bicycling app that
 gives the rider one-click access to information about the nearest bikeshop so that he or she
 can get their bike fixed at the closest shop on their route and get back to biking.

## Explanation

_Bike Ridz_ uses Android's Location Service to find the nearest bikeshop. It displays information
about that shop in one activity; launches the route via Google Maps in another activity, and
constructs a turn-by-turn cue sheet from MapQeust data in a third activity. The fourth activity,
still in progress, hosts a bike inventory, where details about the rider's bike and their
parts may be accessed for use at a bicycle shop.


## Testing
Please note that _Bike Ridz_ does not contain an exhaustive list of bikeshops. At the moment, it
contains nearly 40 shops in Maryland alone. To test the app, please us the following coordinates
in the emulator. The coordinates and expected nearest bikeshops are as follows:

1. Latitude: 39.3714, Longitude: -76.6576, Nearest Shop: Joe's Bike Shop
2. Latitude: 39.258160, Longitude: -76.732190, Nearest Shop: The Hub-C'Ville Bikes
3. Latitude: 39.413840, Longitude: -77.409410, Nearest Shop: Wheel Base Bikes


## Known Bugs
The application _will_ crash the first time it is launched. This is because I have not implemented 
the bikeshop database helper in an asynchronous manner and have created a race condition on the
first launch. Three of the activities depend upon data in the database, but the data is not 
populated until one of the actitivies is launched. Restart the application and it will start.
I will be implementing an asynchronous bikeshop database helper to fix this issue, but ran out of
time for v. 1.0 of the app.