# A Virtual Knitting Notebook

## Purpose

Knitting can be a messy, non-linear endeavour. For example, I'll start knitting a sock, then stop working on the sock to start a much 
bigger project, then come back to finish the second sock a year later. As you can imagine, knitting the second sock is much harder than knitting the first.
If only I'd written down that trick I used to shape the heel, the number of rows to the cuff, or what kind of bind-off I used... 
"Documentation is important!" I know, but why didn't I just write down these notes in the first place?

I do have a physical notebook, but keeping track of multiple projects looks messy and disjointed, as you can't move the pages around to group each project.
Sometimes notes don't belong to a certain project, like tips/tricks that may have been discovered, or just a stray idea or thought that inspired you. In a physical notebook, these little free-floating notes can easily be lost.
As well, you can't easily paste in pictures, charts, or videos you might want to reference later. 

I hope this virtual knitting notebook will solve many of these issues and encourage me to document my process more often.
*Note, this is not meant to replace Ravelry, a wonderful website with **many** functionalities. I simply wanted a more free-form virtual space to document my thoughts*

## User stories

- I want to create multiple knitting projects in my notebook.
- I want to view the title's of all the knitting projects I've created.
- I want to add multiple new yarns, new needles, or new notes to a particular project.
- I want to remove a specific yarn or needle from a particular project.
- I want to be able to save everything I have added to the notebook.
- I want to be able to load a previously saved notebook when I open the app.

## Phase 4: Task 2

Below is an example of what gets printed to the console when the program is exited.

- Closing Notebook
- PRINTING ACTIONS PERFORMED:
- Added to Notebook: Grey Hat
- Added to project Grey Hat: Grey Worsted
- Added to project Grey Hat: 10mm circular
- Added a note to project Grey Hat
- Added to Notebook: Infinity Scarf
- Added to project Infinity Scarf: White Wool
- Added to project Infinity Scarf: 16 mm straight
- Added a note to project Infinity Scarf
- Removed from project Infinity Scarf: White Wool
- Added a note to project Infinity Scarf
- THAT'S IT

## Phase 4: Task 3

Since the Yarn and Needle models extend the same abstract class, it would've been nice to have this relationship in the ui folder too. This would've reduced duplication by allowing the same methods/class to be used to add or remove yarns and needles from the selected project.

As well, since the Yarn and Needle models only require the project name to print out the correct Event, I could've passed in a String instead to remove the association between KnittingSupplies and KnittingProject. Similarly, the KnittingProject field can be removed from Notes.



