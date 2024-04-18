README

Welcome to GreenBank!

As a way to gain experience with JavaFx and GUIs in general, I developed my own bank application simulation.

Some of the cool features included in the app and thing that I learnt along the way:

- minimum password requirements using regex
- MD5 self coded hashing algorithm so that the password are safely stored
- serialize and deserialization to save customers information and transactions
- javafx panes system, buttons, text fields, password fields, hyperlinks etc
- jfoenix hamburger menu

---

HOW TO RUN THE APP:

From the maven project’s root folder, run the following commands:

The first time:
mvn install

Everytime:
mvn exec:java

or simply
mvn javafx:run

If you are not using maven, you can run it with the following command, from the project's root folder: 
java --module-path $PATH_TO_FX --add-modules javafx.controls bankApp.BankApp

Where the environment variable $PATH_TO_FX points to your javafx libraries directory. (In my case it’s “/usr/lib/jfx/javafx-sdk-22/lib”)

---

HOW TO USE IT:
I created a user named Bob for users to be able to play with the app. To log in as Bob, use the following credentials:

login: bob

password: F1rst@User

Bob has withdrawn and deposited money a few times already, you can see all the transactions he’s made.

Feel free (and I encourage you) to create your own user, by clicking the “sign up!” link. 

You forgot your password? Just click the “Forgot your password?” link and reset it.

Enjoy!

