# Minesweeper Game (ComposeVersion)

_This is the game minesweeper for android.It is another way to implement minesweeper game using Jetpack Compose_

  
#### Here some screeshots of the main presentation of the game
![Screenshot_20240519_131919](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/20c33f85-70ab-456b-89de-eb8f09a17fef)
![Screenshot_20240519_131041](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/32173a3a-e39f-457b-9f78-b777019800fe)
![Screenshot_20240519_131110](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/5d8dd590-f1e6-486a-9457-f83c0f9b1701)




### Instructions for play 

_The main game includes three command buttons: Reset Button, Dig Mode Button (Shovel), Flag Mode Button (Flag)._

_When Dig Mode is activate you can reveal any cell exept the ones that are marked by a flag._

_When Flag Mode is activate you can mark or dismark any cell with a flag (The number of flags is equal to the total number of mines)_

_The reset button if for reset the game_

#### Here some screeshots of the main game

![Screenshot_20240519_131210](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/e937f24e-80c6-465f-ab30-a943ac5a5953)
![Screenshot_20240519_131246](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/440dc3e9-f7af-41f0-94ff-dfce7759a3c7)
![Screenshot_20240519_131320](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/4a3fab51-8d10-4e02-8d4a-0c8f80bd00bd)


## Tech Stack Used And Architectural pattern ⚙️

* Xml (eXtensible Markup Language) - For the views
* Navigation Component - For simple navigation between fragments
* MVP(Model-View-Presenter) - Main Architecture pattern
* Kotlin - The main language

### Personal Commentaries
_I use MVP architecture with the finality of separate the logic of the game from the view._
_With the use of MVP I create a main game presenter wich controlls all the logic of the game when_
_the main view (Game Fragment) just show all user interactions._

_In the domain file there are some auxiliar classes and structures for made the game more_
_simple to implement. These code is one of the multiple ways of implement the game._

## Authors ✒️

* **Mauro Serantes** - [Mauro Serantes](https://github.com/MauroSerantes)

## For The Future

* Add a timer
* Add an online mode
* Improve the UI
