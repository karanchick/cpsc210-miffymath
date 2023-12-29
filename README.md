# My Personal Project

## Miffy Math🐰!
The application will present simple arithmetic questions, 
alongside a Miffy *(character)*, on a card to the user, and 
keep track of correct scores. When the user answers a certain number of questions correctly, 
they collect the Miffy card and add it to their collection! 

It could *include features* such as:
- Different categories of arithmetic
- Tracking of high score

The **target audience** for this application is parents, who
want to encourage their children to practice math, as well as
the children themselves.

The inspiration behind this project comes from my
previous experience as an English and math tutor for
young students. I've found that a **fun game is the best
motivation!** Additionally, I love Miffy.

### User Stories:
 - As a user, I want to be able to add a point to my total score, and a Miffy card to my collection.
 - As a user, I want to be able to view a list of all my Miffy cards, and sort by rarity.
 - As a user, I want to be able to perform different math operations
   (addition, subtraction, multiplication)
 - As a user, I want to be able to track my progress and see my high scores.
 - As a user, I want to save my high scores and MiffyCard collection (if I so choose).
 - As a user, I want to have the option & be able to reload my scores and MiffyCard collection.

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Miffy Cards to a User" 
by clicking play button, then answering 2 questions correctly. You can then see your cards by going back to the start menu
and clicking collection button

- You can generate the second required action by pressing the collection button, and entering a number
  (1-5), to sort your cards (Xs), by rarity, as indicated by the user input.

- You can locate one of my visual components by pressing play, and answering 2 questions correctly.
Upon doing this and getting a Miffy Card, an image of Miffy appears.

- You can save the state of my application by pressing the save button in start menu
- You can reload the state of my application by pressing the load button in start menu


# Phase 4: Task 2
Wed Nov 29 14:31:17 PST 2023 <br />
New Question <br />
Wed Nov 29 14:31:22 PST 2023 <br />
Point added! <br />
Wed Nov 29 14:31:22 PST 2023<br />
New Question<br />
Wed Nov 29 14:31:24 PST 2023<br />
Point added!<br />
Wed Nov 29 14:31:24 PST 2023<br />
New Question<br />
Wed Nov 29 14:31:24 PST 2023<br />
Miffy Card added!<br />
Wed Nov 29 14:31:27 PST 2023<br />
Incorrect answer :( Game ended!

# Phase 4: Task 3
If given more time, I would make the following two changes to my code: <br />
1) Adhere to the single responsibility principle, and separate the User class into two separate classes.
2) Reduce redundant code by separating the MiffyMath class, and abstracting reused implementations. <br />

Firstly, my User class currently handles both the User's collection, and score. Due to these two separate 
responsibilities, this class is not very cohesive. To improve this, I would split User into a UserScore class, 
and a UserCollection class. This would overall make the program simpler to understand and maintain in the future. <br />

Second, in my MiffyMath class, I am loading and holding all pages and elements of my GUI. On top of this, 
I have a respective clear() and return() method for each page, which evidently creates a lot of redundancy in my code. 
This is because each clear() and return() method simply set the elements of the page to visible/non-visible. It would
be better if I made a separate class that handles each page (ex. StartScreen, GameScreen, etc.), that holds all 
its elements. Then, I could abstract out the clear() and return() methods, creating just one of each. These could then
take each page's elements as a parameter and change the visibility as needed. Or using a JPanel method I could do 
this as well. This change would make my code much cleaner and easier to read, as well as easier to maintain. 
For example, if I added a new element to my start screen, I would only have to add it in one place (the class). In 
comparison, now I would have to make changes in the load(), clear(), and return(), method for my start screen.


