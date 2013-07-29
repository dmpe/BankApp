# Bank application #

This application **simulates ATM** (which is our GUI) for your bank account. In the app there are already some account predefined, thus you can: 

1.  **Insert your card number and pin number**. The app will connect to one already predefined accounts and this one will be used until you take your card out. 
2.  You can withdraw your money (which are already predefined) from the bank account. **You can do so in 2 ways**. Either you choose one of a given amounts of money. Or you can insert your own amount which you want to take out.
3.  Then app includes a lot of messages, one of them is the **info box**. 

Application is based on 2 exercises I have done during my 2. semester at the university. All of them are here, [on github](https://github.com/Johnmalc/Homeworks2), and one of them is this [one](https://github.com/Johnmalc/Homeworks2/tree/master/Aufgabe1) and another one is [here](https://github.com/Johnmalc/Homeworks2/tree/master/Aufgabe13). The main logic is taken from [here](https://github.com/Johnmalc/Homeworks2/tree/master/Aufgabe13) (not GUI/Vars/Main.java).

This is how the app looks something like (based on the FXML design; subjected to change) this:

![Design](/image.jpg)

**Contribution to the project:**

You are *W E L C O M E* to make issues & and your pull requests. I will look on them and with very high probability accept them. 

**License: MIT** 

Do whatever you need to do, I don't care. I use libraries so must comply with them too.

**Libraries used**

1. [JavaFX Dialogs](https://github.com/marcojakob/javafx-ui-sandbox/tree/master/javafx-dialogs)

2. [JFXtras](https://github.com/JFXtras/jfxtras-labs)

**BLOG**

Look more on my blog where I have wrote about it.

Here [http://www.komentovaneudalosti.cz/blog/?p=1126](http://www.komentovaneudalosti.cz/blog/?p=1126)

**Features in the future** 

1. [X] Allow to add accounts in the cashmaschine dynamically, through another fxml model. Nice to create some menu **See Problems**
2. [x] TextField should be done with Scrollbar

----------

- to add some close button > delete menu label and correct the print all accounts button
- The only problem is that if i add new account, it wont get updated > listview for accounts
- try to change methods to allow delete in textfields [bug see this](https://forums.oracle.com/thread/2564384)
- https://forums.oracle.com/thread/2543153
- http://code.metager.de/source/xref/netbeans/javafx2.samples/FXML-LoginDemo/src/demo/Main.java
- http://docs.oracle.com/javafx/2/api/index.html?javafx/stage/Window.html
- http://stackoverflow.com/questions/15041760/javafx-open-new-window
- https://gist.github.com/jewelsea/1887631
- http://stackoverflow.com/questions/10486731/how-to-create-a-modal-window-in-javafx-2-1
- http://java-buddy.blogspot.cz/2012/12/open-new-window-in-javafx-2.html