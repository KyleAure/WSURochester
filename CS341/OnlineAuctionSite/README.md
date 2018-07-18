# CS341OnlineAuctionSite
## CS 341: Advanced Data Structures

### Homework #2

#### Purpose: 
To practice defining and using Java classes and the concepts of inheritance and aggregation.

#### Description: 
Your job is to design and implement some classes to use for an online auction site. You must
design the following ADTs:

#### User ADT: 
Describes and manipulates user information. You must track the following information about a user / provide the following methods:
  1. username
  2. firstName
  3. lastName
  4. a list of the 10 most recently purchased items
  5. A user can bid on and purchase Items
The User class should have a default constructor, as well as one accepting all parameters.
It should also provide accessor (getter) and mutator (setter) methods for appropriate methods. By default, a user is able to buy products only (not sell them).

#### Item ADT: 
Describes a single item being sold. An item object keeps track of the current
high bid for that item in addition to information about the object itself. You must track
the following information about an item:
  1. name
  2. description
  3. highBid (currentBid)
  4. buyNowPrice
  5. location
  6. dateBiddingComplete
The Item class should have a default constructor, as well as one accepting all parameters.
In addition, this class contains functions for retrieving information about the item.

#### Seller ADT: 
Seller is a specialization of user. You should implement it as such. In
addition to all user attributes, sellers also have:
  1. Seller is a specific type of User that has a list of up to 20 items for sale


Note that for each ADT, you will define both an interface and a class. For example, you will
create an ItemADT interface that will include methods such as getItemName, among others. The
Item class implements this interface and implements all methods defined in the interface. Provide
the output from running your own test cases on all aspects of your program.

Submit your work to D2L. Put all individual’s names in the D2L comments section of your
submission. 

You should submit:
  1. A UML class diagram of your system
  2. .java and .class files for the three classes you created
  3. Output showing your code compiling and results from executing your own test cases
You will be graded on the quality of your code, documentation and test cases. To receive full
credit, you should meet the requirements listed, demonstrate this through your test cases, and all
code should be well formatted and commented.
