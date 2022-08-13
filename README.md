# DW-WalrusKingdom
The Walrus Kingdom Game
In it's current state the game is little more than a couple of classes and weird looking files but we'll get there, I promise :)

## About
The first version of WalrusKingdom can be found [in my personal repositories](https://github.com/KaliPhobos/WalrusKingdom).
Most parts of the basic engine were already working but quite some interactive features such as NPCs and being able to enter buildings as well as the actual storyline were never implemented.

After the first version was little more than a proof of concept (writing an entire game engine with little more that Arrays, Ints and Strings) the second version aims to be an actual game that is hopefully fun to play.


## Current state of development (as of v0.0.14):

Materials
- [x] Data Structure for Material Types
- [x] Material Manager taking care of Material Types
- [x] Json Importer for Material Types
- [x] Json Exporter for Material Types

Triggers
- [x] Data Structure for Trigger Types
- [x] Trigger Manager taking care of Triggers

Maps
- [x] Data Structure for Maps
- [x] Initialisation for Maps
- [x] Map Manager taking care of Maps
- [x] Json Importer for Map Data
- [x] Json Exporter for Map Data

Game Data
- [ ] Add checks for broken JSON files or missing contents
- [x] Import & adapt old Material Data (from v1)
- [x] Import & adapt old Map Data (from v1)
- [x] Import old Tiles (Bitmaps from v1)

Graphics
- [x] Linking Bitmaps to Materials
- [ ] Main Game Loop
- [ ] Basic map renderer
- [ ] Player controls
- [ ] Scrolling
- [ ] Smooth Scrolling

Everything else
- [ ] More Advanced Debug Outputs
- [ ] super important stuff?