# Minesweeper Game (ComposeVersion)

_This is the game minesweeper for android.It is another way to implement minesweeper game using Jetpack Compose_

  
### About the app 📋

_The main selection screen contains three principal options: Casual Game,Paying Modes and History.
Playing Modes and History are not developed yet, wich means the app is not finish.
In Casual Game you could select into three different levels of play: Beginner (64 tiles and 10 mines),
Intermediate (256 tiles 40 mines) and Advanced (480 tiles and 99 mines)_
_All this three modes of plays contains a mine counter and a timer._
  
#### Here some screeshots of the main presentation of the game
![Screenshot_20240519_131919](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/20c33f85-70ab-456b-89de-eb8f09a17fef)
![Screenshot_20240519_131041](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/32173a3a-e39f-457b-9f78-b777019800fe)
![Screenshot_20240519_131110](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/5d8dd590-f1e6-486a-9457-f83c0f9b1701)

### Instructions for play in Casual Game

_For reveal the cells you must click on them._

_For mark the cells with a flag you need to make a long click on them_

_Once you lose or win it will display the end game dialog. _

#### Here some screeshots of the Casual Game (mode) 

![Screenshot_20240519_131210](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/e937f24e-80c6-465f-ab30-a943ac5a5953)
![Screenshot_20240519_131246](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/440dc3e9-f7af-41f0-94ff-dfce7759a3c7)
![Screenshot_20240519_131320](https://github.com/MauroSerantes/Minesweeper_ComposeVersion/assets/146656323/4a3fab51-8d10-4e02-8d4a-0c8f80bd00bd)


## Tech Stack Used And Architectural pattern ⚙️

* Jetpack Compose - For the UI
* Navigation Component - For navigation between screend
* MVVM(Model-View-ViewModel) - Main Architecture pattern
* Kotlin - The main language

### Personal Commentaries
_This time i use MVVM because it was more easy to integrate with jetpack compose declarative ui._
_The game view model controls all the logic of the game and provides a state updater based in the
user actions._
_The game works but it feels a kind of slow. It has to be the way that i implemented it. 
I continue learning Jetpack Compose and all its features._

_I use the same auxiliar data structures for the development of the game._

## Authors ✒️

* **Mauro Serantes** - [Mauro Serantes](https://github.com/MauroSerantes)

## For The Future

* Finish all the app functionalities
* Improve the UI
