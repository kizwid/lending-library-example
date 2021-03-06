Library Homework Assignment

Instructions

Below is a programming assignment. You are allowed to use third-party libraries and tools in order for developing, building, and testing purposes. Please include an explanation of your design, assumptions as well as detailed instructions on how to run the application along with your code (no more than one side of A4). We don't require a front-end or a command-line interactive interface. We are most interested in how you design your classes and solve the problem at hand.

The code is assessed for:

the design
object-oriented programming skills
production quality
maintainability
extensibility
Please compress only the source (including the build files) into a single zip. There should be no jarfiles or executables in the submitted zip file.

The Problem

You have been tasked with building part of a simple library system that allows customers to borrow books, DVDs, VHSes, and CDs. Your code should provide the following functionality:

Borrow and return items - items are loaned out for a period of one week.
For example, a customer can borrow WarGames on 21st February and they will be expected to return it by 28th February.
Determine current inventory - this should show you the current items that are loanable. You should make allowances for multiple copies of the same item (i.e. there can be multiple copies of the same book/movie).
For example, if you choose to use the initial inventory, the current inventory should return the titles.
Determine overdue items. i.e. all items that should have been returned before today.
For example, if a book was due on 12th February and today is 15th February, that book should be flagged as overdue.
In order to test this application, you may choose to import the following initial inventory (in CSV format) or create your own (make sure it's part of the submitted zip file):

UniqueID,Type,Title
7,DVD,Pi
1,Book,The Art Of Computer Programming Volumes 1-6
2,Book,The Pragmatic Programmer
6,Movie,Hackers
3,Book,Java Concurrency In Practice
4,Book,Introduction to Algorithms
7,DVD,Pi
4,Book,Introduction to Algorithms
5,DVD,The Imitation Game
5,VHS,WarGames
6,VHS,Hackers
4,Book,Introduction to Algorithms
7,DVD,Pi