# Project & Task Manager (Introduction)

The project is designed under the object of creating a task management system
where tasks and projects are organised in a tree structure such that a project can 
have sub-projects or tasks, and the tasks can have further sub-tasks. Therefore, 
the purpose of the assignment is to create an utmost of 3 Level General tree where 
Level 0 is represented by the project, Level 1 could be Sub-project or Task, Level 2
states Task or Sub-task and Level 3 constitutes sub-task (if any). Moreover, the
assignment brief instated a challenge task which was to put a sorting technique in place 
based on Covey's philosophy, but due to lack of time this task wasn't accomplished. 
Furthermore, the requirement of showing a completed and missed task(s) was not established due to the
aforementioned reason.
Finally, the assignment thus submitted involves a fully functioning User Interface required 
to create a tree structure, hence the program is able to display the entered information in
form of directory; making it easier for the user to follow through the projects and tasks. The 
program also involves most of the relevant error handling techniques such as prohibiting the user from entering due date which
comes before the present day's date; or the due date assigned to task which comes before the parent
project's or sub-project's due date and so on.

[This project is highly inspired from the Temperature recording application
designed and developed by Errol Thompson.]

# References

[1] Thompson, E., 2020. Temperaturerecording. [online] gitlab.com. Available at: <https://gitlab.com/FoOOSD/temperaturerecording.git> 
[Accessed 11 - 21 October 2020].

********************************************************************
Title: TemperatureRecording
Author: Thompson, E (@thompel1)
Date: 2015
Code Version: N/A
Availability: https://gitlab.com/FoOOSD/temperaturerecording.git
********************************************************************
[Source Code] https://gitlab.com/FoOOSD/temperaturerecording.git
 

# Logs
[1] 11-10-2020 13:20
@manmohansingh -
    Creating frame and layout for the project and task manager user-interface;
    Added project panel details to the frame alongside working on the other panels 
    to be used as an input screen for tasks, sub-tasks and sub-projects.
    
[2] 13-10-2020 11:00
@manmohansingh -
    Adding few components to the Project panel, making the window auto adjust the
    panels on resizing the window by overriding the pre-defined library 
    paintComponents and paint functions.

[3] 13-10-2020 20:55
@manmohansingh -
    Added "Task Details" panel to the left column.

[4] 14-10-2020 10:34
@manmohansingh -
    Added "Importance" field to Task Details panel.

[5] 16-10-2020 11:49
@manmohansingh -
    Added Add, Modify, Find and Delete buttons to the UI
    alongside some minor changes to the text and field(s)
    adjustments.

[6] 17-10-2020 16:00
@manmohansingh -
    Added data model to the program in order to build a tree
    map for the data entered into the fields and display the tree
    structure inside the ProjectTreePanel.
    
[7] 17-10-2020 18:00
@manmohansingh -
    Added Error Panel to display necessary errors to the
    user streamed through errorMessage from Data Model.
    
[8] 17-10-2020 18:25
@manmohansingh - 
    Added support for auto adjustment to the error panel in
    order to make it stay in its configuration even after 
    the resizing of the overall scale of the window.

[9] 17-10-2020 18:32
@manmohansingh -
    The assignment still lacks Data Listeners in order to modify,
    delete and find the existing data, since the data entered
    into the fields upon running the application is not being
    written into any external file.
    The UI still lack fields in order assign date to the tasks
    and projects; as well as the importance band.
    The application is unable to calculate urgency of any task by
    making mathematical comparisons with the other tasks or 
    projects; or by keeping a check on estimated duration.

[10] 17-10-2020 18:38
@manmohansingh -
    The assignment needs to have Test cases in order to handle
    various common Errors and preventing user from making
    such (Error Handling).

[11] 17-10-2020 18:42
@manmohansingh -
    Majority of the Data Model still lacks comments which would be added
    later upon the course of this assignment.
    
[12] 19-10-2020 11:17
@manmohansingh -
    Made "due Date" visible alongside Project and Title name.
    Need to display the importance band alongside the text as well.
    Need to put in error handling for, if the user enters due date which comes
    before today's (present day's) date.
 
[13] 20-10-2020 10:21
@manmohansingh -
    Added dataListeners to delete and working on dataListeners for 
    Modify button.

[14] 21-10-2020 16:33
@manmohansingh -
    Successfully Added Error handling to check for due Date being higher than
    the parent's due date (if provided) and displaying the applicable error.

[15] 21-10-2020 18:07
@manmohansingh -
    Integrated importance inside the data model for Task, still lacking the core features
    such as sorting of Tasks according to urgency.


