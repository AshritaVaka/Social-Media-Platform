# ENDPOINTS DIRECTORY 
##Please follow this to be able to test the code or run it 

## LOGIN
URL: `/login`  
Method: POST  
Request Body: 
```json
{
  "email": "string",
  "password": "string"
}

###Responses:

-Login Successful
-Username/Password Incorrect
-User does not exist


##SIGNUP
URL: /signup
Method: POST
Request Body:

json
Copy code
{
  "email": "string",
  "name": "string",
  "password": "string"
}

###Responses:

-Account Creation Successful
-Forbidden, Account already exists

##USER DETAIL
URL: /user
Method: GET
Query Parameter: userID

Response Body:
json
Copy code
{
  "name": "string",
  "userID": "int",
  "email": "string"
}

###Responses:
-User does not exist

##USER FEED
URL: /
Method: GET

Response Body:
json
Copy code
{
  "posts": [
    {
      "postID": "int",
      "postBody": "string",
      "date": "date",
      "comments": [
        {
          "commentID": "int",
          "commentBody": "string",
          "commentCreator": {
            "userID": "int",
            "name": "string"
          }
        }
      ]
    }
  ]
}

##POST
URL: /post

##Create a New Post
Method: POST
Request Body:

json
Copy code
{
  "postBody": "string",
  "userID": "int"
}
###Responses:

-Post created successfully
-User does not exist

##Retrieve an Existing Post

Method: GET
Query Parameter: postID
Response Body:

json
Copy code
{
  "postID": "int",
  "postBody": "string",
  "date": "date",
  "comments": [
    {
      "commentID": "int",
      "commentBody": "string",
      "commentCreator": {
        "userID": "int",
        "name": "string"
      }
    }
  ]
}
###Responses:

-Post does not exist

##Edit an Existing Post
Method: PATCH
Request Body:

json
Copy code
{
  "postBody": "string",
  "postID": "int"
}

###Responses:

-Post edited successfully
-Post does not exist

##Delete a Post

Method: DELETE
Query Parameter: postID

Responses:
-Post deleted
-Post does not exist

##COMMENT
URL: /comment

##Create a New Comment
Method: POST
Request Body:

json
Copy code
{
  "commentBody": "string",
  "postID": "int",
  "userID": "int"
}
###Responses:
-Comment created successfully
-User does not exist
-Post does not exist

##Retrieve an Existing Comment

Method: GET
Request Param: commentID
Response Body:

json
Copy code
{
  "commentID": "int",
  "commentBody": "string",
  "commentCreator": {
    "userID": "int",
    "name": "string"
  }
}
###Responses:

-Comment does not exist

##Edit an Existing Comment
Method: PATCH
Request Body:

json
Copy code
{
  "commentBody": "string",
  "commentID": "int"
}
###Responses:
-Comment edited successfully
-Comment does not exist

##Delete an Existing Comment

Method: DELETE
Query Parameter: commentID

###Responses:
-Comment deleted
-Comment does not exist

##ALL USERS
URL: /users
Method: GET
Response Body:

json
Copy code
{
  "users": [
    {
      "name": "string",
      "userID": "int",
      "email": "string",
      "posts": [
        {
          "postID": "int",
          "postBody": "string",
          "date": "date",
          "comments": [
            {
              "commentID": "int",
              "commentBody": "string",
              "commentCreator": {
                "userID": "int",
                "name": "string"
              }
            }
          ]
        }
      ]
    }
  ]
}
