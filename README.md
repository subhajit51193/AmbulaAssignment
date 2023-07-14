
## Screenshots

![App Screenshot](https://raw.githubusercontent.com/subhajit51193/AmbulaAssignment/main/SwaggerImage.jpg)


# UserLocationApp

This is a springboot application for managing endpoints related to users and their locations. Users can have different roles and based on that they can access endpoints.


## Features

- Signup and Signin using JWT token
- Basic CRUD operations
- READER and ADMIN roles of Users
- Authorization of users before they can access endpoints
- ADMIN users can perform  operations like POST, DELETE, PATCH, etc.
- READER users can perform PATCH and GET .
- Cross Platform


## API Reference

 - POST : user/signUp  - Signup method for all users
 - GET : /signIn  - Login method for users
 - GET : user/getMyDetails  - Get details of logged in user
 - PATCH : user/updateEndUserName/{name}  - Update method for Readers to change their name 
 - PATCH : user/updateUserLocationName/{name}  - update method for readers to change their name for UserLocation object
 - GET : user/getUsers/{num}  - Get list of nearest users from point (0,0)
 - POST : admin/createData  - Post method for admins to create UserLocation object
 - PUT : admin/updateData  - Update mthod for Admins to update contents of UserLocation object
 - DELETE : admin/deleteUser/{userId} : Delete method for Admins to delete any user
## Installation

Clone from Repo. Run in IDE 

```bash
  Default port 8080
```
    
## Deployment

Not yet deployed




## Tech Stack

**Client:** Java,SpringBoot,HSQL

**Server:** Embedded

## Authors

- [@Subhajit Saha](https://github.com/subhajit51193)


## Feedback

If you have any feedback, please reach out to us at nnorth87@gmail.com


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://subhajit51193.github.io/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/subhajit-saha-103110185/)
