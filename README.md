# Lab Equipment Manager

## Proposal

I will be designing an **application that keeps track of shared
laboratory equipment**. This application is meant to be used by
someone in an administrative position such as a lab manager 
or director of a research lab. This project is of interest to
me because I have enjoyed working in research labs, and I 
have thought about developing applications to improve lab
operations day-to-day. An example of this would be creating
a centralized tool to manage the shared lab instruments and 
equipment. I believe an application such as this would help 
in troubleshooting hurdles in lab operations and help in 
formulating decisions such as budget allocation.


## User Stories
In the context of my lab equipment management application:
- As a user, I want to be able to **add** new equipment to my lab 
inventory.
- As a user, I want to be able to **view** the list of equipment in 
my lab inventory.
- As a user, I want to be able to **update** the status of equipment 
as *out-of-service*.
- As a user, I want to be able to **remove** equipment from my lab that 
was broken or replaced.
- As a user, I want to be able to select an instrument and **list the 
names** of the scientists who have used it.
- As a user, I want to be able to select equipment and **update the 
running cost** spent on parts and repairs.
- As a user, I want to be able to **view statistics** for the list
of equipment such as the most frequently used, and most expensive item.
- As a user, I want to be able to save my equipment list to file.
- As a user, I want to be given the option to load my equipment list from file.

# Instructions for Grader

- You can locate my visual component by launching the GUI, right-click the EquipmentManagerGUI class and run main().
That should display the welcome screen containing an image which is my visual component.
- You can reload the state of my application by launching the application and clicking the "load from file" button.
- You can generate the first required event related to adding Xs to a Y by clicking load from file. This should display
the main menu containing a panel of current inventory items. Click and select an inventory item and click on the Edit button
on the bottom. You may enter a String into the newStatus or newUser text fields, and an integer into the newExpense text field.
Submit your entered value by clicking the button beside it. Alternatively, click the remove Equipment to remove it from the list.
- You can generate the second required event related to adding Xs to a Y by entering the main menu and clicking the Add New Equipment
Button. This will open up a pop-up window to enter parameters of the new equipment. Enter a String into the newStatus and 
newUser text fields, and an integer into the upfrontCost text field. Click Add New Equipment to submit the new equipment fields
and add it to the inventory.
- You can save the state of my application by entering the main-menu and clicking the "save to file" button.

# Phase 4: Task 2
- Thu Dec 01 15:30:39 PST 2022
- Mass Spectrometer has been added to the lab inventory!
- Thu Dec 01 15:30:56 PST 2022
- Status of Mass Spectrometer set to: Out Of Order
- Thu Dec 01 15:30:57 PST 2022
- Harbir has been added to the user history of theMass Spectrometer

- Process finished with exit code 0
