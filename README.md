# ShareLabs
ShareLabs is an Android application that allows users to easily create and track progress on their tasks and collaborate with other users interested in having group projects.

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Our app allows a group of people to come together and create a project. When you create a project, you are able to invite others to it and create initial tasks for them, as well as a project deadline. Once the project is initialized, the group members are able to check off what tasks they've finished and post to a shared update feed to inform others of their progress. You can easily see the cumulative progress of your work on the main project screen via a progress bar, as well as see the tasks of other group members.

### App Evaluation
- **Category:** Social Networking/ Productivity
- **Mobile:** This app would mainly be designed with the idea that one would finish tasks throughout the day, using their phone to easily access the app, check off what they have done, or view the progress of others over time.
- **Story:** Users come together to complete a project. When you finish a task, you can send a message to a group feed to update them of progress, or remind someone else to finish their tasks before the deadline.
- **Market:** Anyone could use the app, and invite others that they know to work together. This is an all ages app, but it would make the most sense to use this in family/friend/work groups rather than with strangers.
- **Habit:** The app is designed to be checked on a few times a day, and the user will be able to see at a glance how much cumulative progress has been made on the project.
- **Scope:** First we would like to focus on using the app for personal purposes such as families, friends, or work groups. However, in the future, we could possibly expand this and add features that could allow anybody to invite others who they think would fit their project. This would most likely be done using a preferences and ratings system, that would allow people to invite others to their project depending on their skills, interests, and reliability.

## Product Spec

## User Stories (Required and Optional)

### Required Stories

- [x] User can create an account with a unique username
- [x] User can login
- [x] User can see overview of project name, progress on project, where to access his or her own individual tasks, where to access all tasks
- [x] User can access his or her own individual tasks
- [x] User can create tasks 
- [ ] User can see his or her own profile
- [x] User can see progress on his or her own individual tasks
- [ ] User can see profiles of other users
- [ ] User can see tasks of other users 
- [ ] User can see progress on tasks of other users
- [x] User can post updates and comments about project
- [x] User can see a timeline of all posts
- [ ] User can view a description of the app


### Optional Stories

- [ ] User can edit their own profile picture
- [ ] User can change the color and design of the project overview screen
- [ ] User can view a description of the project


# Screen Archetypes

- Sign up screen
    - User can create an account with a unique username
- Login screen
    - User can login
- Home screen
    - User can see overview of project name, progress on project, where to access his or her own individual tasks, where to access all tasks
- Your tasks screen
    - User can access his or her own individual tasks
    - User can see progress on his or her own individual tasks
- All tasks screen
    - User can access all tasks 
    - User can see profiles of other users
- Settings screen
    - User can view a description of the app
- Profile screen
    - User can see his or her own profile
    - User can see tasks of other users (by landing on the profile screens of other users) 
    - User can see progress on tasks of other users (by landing on the profile screens of other users) 
- Compose screen
    - User can create tasks 
    - User can post updates and comments about project
- App description screen (provides functionality for optional stories)
    - User can change the color and design settings of the project overview screen


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
<img src="wirefram1.png" width=600>
<img src="wirefram2.png" width=600>
<img src="wirefram3.png" width=600>

### Digital Wireframes & Mockups
<a href="https://imgur.com/yoXrFLV"><img src="https://i.imgur.com/yoXrFLV.png" title="source: imgur.com" /></a>

### Interactive Prototype
<a href="https://imgur.com/u8kGsu4"><img src="https://i.imgur.com/u8kGsu4.gif" title="source: imgur.com" /></a>

## Schema 

### Models

**Task**
| Property      | Type          | Description  |
| ------------- |:-------------:| ------------:|
| objectId     | string | unique id for the user task (default field) |
| title     | string |   main title of the task |
| author      | pointer to User |   post author |
| isDone | boolean  | checks if the task has been finished|
| deadline  | DateTime     |   date when task should be finished |
| createdAt | DateTime     |   date when task is created (default field) |
| updatedAt | DateTime     |   date when task is last updated (default field) |

**User**
| Property      | Type          | Description  |
| ------------- |:-------------:| ------------:|
| objectId     | string | unique id for the user (default field) |
| username  | string |   username of the User |
| password| string | password of the user|
| email  | string  |   email of the user |
| profileImage  | file  |   profile image of the user |
| friends  | Lists of Users  |   friends/collaborators of the user |
| createdAt | DateTime     |   date when user is created (default field) |
| updatedAt | DateTime     |   date when user is last updated (default field) |

**Post**
| Property      | Type          | Description  |
| ------------- |:-------------:| ------------:|
| objectId     | string | unique id for the user post (default field) |
| author      | pointer to User |   post author |
| message | string    |    post message by author|
| image | File (optional)  |  image that User can add to post |
| likesCount | number    |  number of likes for post|
| createdAt | DateTime     |   date when post is created (default field) |
| updatedAt | DateTime     |   date when post is last updated (default field) |

### Networking
## Networking Requests

- Your Tasks Screen
    - (GET) Query all tasks current user created not marked complete
    - (POST) Update a task
    - (DELETE) Delete existing task
- Detailed Task Screen
    - (GET) Properties of Current Task
- Profile Screen
    - (GET) Query all tasks marked complete by user
    - (GET) Query profile image uploaded by user
- All Tasks
    - (GET) Query profile image of other users
- Compose Screen
    - (POST) Create a new task
    - <a href="https://imgur.com/n2bMOgw"><img src="https://i.imgur.com/n2bMOgw.png" title="source: imgur.com" /></a>

    
### Build Progress

## November 27, 2020
<img src='ShareLabs Progress Gif 1.gif' title='11/27/20' width='' alt='Video Walkthrough' />
<img src='ShareLabs Progress Gif 12_11_20.gif' title='12/11/20' width='' alt='Video Walkthrough' />
 


GIFs created with [LiceCap](http://www.cockos.com/licecap/).

## Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

