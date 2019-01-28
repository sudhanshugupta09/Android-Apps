# Android-Apps
These apps were created as a part of curriculum
Each Project has their own ReadMe file. Only the .java files and manifest files have been uploaded for each project

# Project 1: Contact Saver
The app has an option to enter the name of the contact to be saved, and if the name is as per the standards, the contact app will open with the name pre-populated.

# Project 2: Car Gallary and Merchants
The app provies a gallary of top-of-the-line cars in the city of Chicago and the list top 5 car dealers selling that car.
There are around 15-20 cars in the collection, the user has the ability to a) view the car's picture in full screen, b) view the office page of the car manufacturer to know morer about the car, c) view the list of top 5 car dealers and ability to call them directly or view their address

# Project 3: Attractions in New York and San Francisco
There are 3 apps:
  A1: Is the interface to send out a broadcast message to the other apps to show the list of attractions of the city of NY and SF.
  A2: This app receives an ordered broadcast(top priority) and relays the message to the other apps 
  A3: Shows the desired attractions list to the user of the selected city and links to their office webpages
  
# Project 4: Guess Four Game
The app provides an interface for 2 different players to take chances to guess the other player's secret 4 digit number. 
          Every guess will provide the player with 2 hints: 
          a) The numbers that were guessed correctly and were placed in the right place.
          b) The numbers that were guessed correctly but were plced wrong.
The players have max 10 chances each to guess the number or else, the game will be drawn
For this implementation , the computer plays the game for both the players( A Smart Stratergy bot and a Not-So-Smart Stratergy bot)

# Project 5: U.S. Treasury Data App
There are 2 apps:
  1) FederalMoneyClient 
  2) FederalMoneyServer
The Client app asks the server app to connect to the treasury service(https://treasury.io) and get the required data, as soon as the request is received by the Server, it is bounded to the client and processes the request using an AIDL methodology for the communication between Server and Client. 
