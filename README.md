# COMPX575
Due on **Friday the 16th of May at midday**.

# Task Goal
* Use the Room Persistence Library to store your contacts in a database.

## Preamble
1. This assignment extends your previous assignment.
2. Rather than forking this repository, simply perform further commits on your Assignment Nine repository.

## Task Specification
Instructions for this assignment are provided in *Android Room Exercises.pdf*

The following take precedence over the instructions in the above pdf:
* In the first section in the PDF (Update gradle files) you are instructed to look at a link to determine the version numbers when editing your gradle files.  Instead, please use version 1.1.1 for both.
* HINT: In the last two sections (Connect with Data and Deleting Contacts), the **contactRepository.update** and **contactRepository.remove** functions take a **Contact** object as a parameter.  The SQLLite repository will use the primary key id (not the Contact name) to identify contacts.  Whilst not explicitly stated in the instructions, this means you will have to make sure this field is correct in the **Contact** object you pass a a parameter.

## Grading
This task is worth as much as 5% of your final grade and is marked out of 5.
* 1 mark for updating the gradle files, creating an entity and the Data access object.
* 1 mark for establishing a connection to a database.
* 1 mark each for adding, updating and removing entries.

## Submission
On top of the commits to your Git repository, please upload your source files to moodle.
