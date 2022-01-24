## Space Invader Game using Java GUI

### SpaceInvader class 
- subclass of JFrame with main method to start the game
- game have 500 by 450 pixels in dimension and displayed centered on the screen 

### Panel class
- subclass of JPanel which is keep track of all invaders, missliles, laser base, mystery ship, score, and fire misslies

### Time and Action Events
- Help synchronizing the dynamics of the game
- Timer object generates intermittent ActionEvent events. The state of the game is updated each time an event is generate
- Timer created when the panel is constructed 

### Keyboard events 
- Implementing ActionListener to handle Timer events, Panel receives keyboard events
- Panel should regconize when space bar, left arrow or right arrow key pressed respectively

### Invaders, Missile, and Base Classes
- The objective of the game is to use missiles to destroy invaders before they can land. These objects,whether invaders, missiles or the base, must be positioned, displayed and moved on the screen as the game advances
- Drawable objects are either missiles or ships (represented by the classes Missile and Ship), where ships are subdivided into base or invader objects (Base and Invader), and where invaders are further subdivided into mystery ships (Mystery), and top, middle and bottom invaders (InvaderTop, InvaderMiddle and InvaderBottom)

### Drawable class
- Objects drawn on the panel (except the score) is a subclass of Drawable. This class defines fields keeping the x and y position of the object, as well as its width and height. This class should define getter and setter methods, an abstract method for getting painted given a Graphics object, and concrete methods for moving the object a number of pixels in a certain direction (either up, down, left or right)

### Missile class
- Missiles are the simplest displayable objects in the game. They are implemented in the Missile class and are drawn as white-filled rectangles of dimensions 2-by-10 pixels wide and high

### Ship class 
- There are two types of ships in the game: invaders and the base. Their common functionality is implemented in the Ship abstract class. This class defines several fields: one indicating whether the ship was hit by a missile, another one keeping track of an audio clip with the sound that the ship makes when hit, and a third one referring to an image of the ships when it has been hit
- This class should include a method to find out whether a given missile has hit the ship, and another method changing the state of the ship to “hit” (at which point it should play the appropriate sound)
- All ships (whether invaders or the laser base) use the same sound when hit. This sound is given as an audio clip named “aud_hit.wav”

### Base class
- This class defines fields holding the image of the base when it is alive (“img_base.gif”) and when it has been hit (“img_basehit.gif”). It also defines a field to hold the audio clip “aud_basefire.wav” used when the base shoots a missile, and a method for playing this clip

### Invader class
- The abstract class Invader is the super-class of all invaders, either mystery ships or any of the three different types of invaders in the wave
- This class defines a field indicating how many points an invader is worth. Note though that this field should be initialized by individual subclasses, as each invader is worth different number of points
- The points of a mystery ship should be randomly chosen among 4 equally distributed values

### InvaderTop, InvaderMiddle, and InvaderBottom classes
- These classes define the invaders in the wave. Invaders on the top row are instances of InvaderTop, invaders on the second and third row (from the top) are instances of InvaderMiddle, and invaders on the bottom two rows are instances of InvaderBottom
- When playing the game, these invaders alternate displaying an image each time they move

### Mystery class
- The mystery ship is an invader that crosses the top of the screen and never descends. It travels either from left to right or from right to left, and could be worth 50, 100, 150 or 300 points. The direction and points it is worth should be randomly selected when ships are created
- Differently from other invaders, this ship does not have an alternating image, and should continuously play a sound when traveling across the screen
- The image and audio clip for the mystery ship are “img_mystery.gif” and “aud_mystery.wav”, respectively
- If a mystery ship is flying across the screen and the game is paused then its sound should stop and should resume when (and if) the game resumes. No more than one mystery ship can exist at the same time
