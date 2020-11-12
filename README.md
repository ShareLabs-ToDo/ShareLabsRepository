# ShareLabs
ShareLabs is an Android application that allows users to easily create and track progress on their tasks and collaborate with other users interested in having group projects.

## User Stories (Required and Optional)

### Required Stories

- User can create an account with a unique username
- User can login
- User can see overview of project name, progress on project, where to access his or her own individual tasks, where to access all tasks
- User can access his or her own individual tasks
- User can access all tasks 
- User can create tasks 
- User can see his or her own profile
- User can see progress on his or her own individual tasks
- User can see profiles of other users
- User can see tasks of other users 
- User can see progress on tasks of other users
- User can post updates and comments about project
- User can view a description of the app


### Optional Stories

- User can edit their own profile picture
- User can change the color and design of the project overview screen
- User can view a description of the project

# Screen Archetypes

- Sign up screen
- - - User can create an account with a unique username
- Login screen
- - - User can login
- Home screen
- - - User can see overview of project name, progress on project, where to access his or her own individual tasks, where to access all tasks
- Your tasks screen
- - - User can access his or her own individual tasks
- - - User can see progress on his or her own individual tasks
- All tasks screen
- - - User can access all tasks 
- - - User can see profiles of other users
- Settings screen
- - - User can view a description of the app
- Profile screen
- - - User can see his or her own profile
- - - User can see tasks of other users (by landing on the profile screens of other users) 
- - - User can see progress on tasks of other users (by landing on the profile screens of other users) 
- Compose screen
- - - User can create tasks 
- - - User can post updates and comments about project
- App description screen (provides functionality for optional stories)
- - - User can change the color and design settings of the project overview screen


# Navigation

## Tab Navigation

- Your tasks tab
    => Your tasks screen
- All tasks tab
    => All tasks screen
- Settings tab
    => App description screen (provides functionality for optional stories)
- Profile tab
    => Profile screen
- Project view tab
    => Home screen
- Compose tab
    => Compose screen
- “Yes” tab (Clicked “Yes” to “You just finished a task! Would you like to post an update to the project feed?”)
    => Compose screen
- “No” tab (Clicked “No” to “You just finished a task! Would you like to post an update to the project feed?”)
    => Your tasks screen
    
## Flow Navigation

- Sign up screen
    => Login screen
- Login screen
    => Home screen
- Home screen 
    => Your tasks screen
    => All tasks screen
    => Settings screen
    => Profile screen
    => Home screen
    => Compose screen
- Your tasks screen
    => Profile screen
    => Home screen
    => Compose screen
- All tasks screen
    => Your tasks screen
    => Profile screen
    => Home screen
    => Compose screen
- Settings screen
    => Profile screen
    => Home screen
    => Compose screen
- Profile screen
    => Profile screen
    => Home screen
    => Compose screen
- Compose screen
    => Profile screen
    => Home screen
    => Compose screen
- App description screen (provides functionality for optional stories)
    => Profile screen
    => Home screen
    => Compose screen


## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
