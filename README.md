
# Users and Manager Spring Boot Application
<table>
<tr>
<td>

  
This project is a Spring Boot application providing CRUD functionality for user management through four API endpoints: `/users`, /get_users, `/specificUser`,  `/deleteUser` and `/bulkUpdate`.
The application ensures proper data validation, maintains user records with UUID identifiers, timestamps, and manages user-manager relationships.
Error handling, logging, and comprehensive validation are implemented to ensure robust and reliable operations.
A manager table is prefilled for testing purposes to validate manager IDs.
</td>
</tr>
</table>

## Get all user 
<u>methos</u>: Get
 - <u>get all users end point </u>: **/users** <br />
 **Sample Response**
 <br />
 
   ```sample response
   [
    {
        "userId": "550e8400-e29b-41d4-a716-446655478400",
        "fullName": "Bharath Manda",
        "panNum": "ftjn4087k",
        "mobileNumber": "8745126395",
        "createdAt": "2045-09-24 10:15:00",
        "updatedAt": "2024-07-27 16:26:18.485575",
        "isActive": true,
        "manager": {
            "managerId": "550e8400-e29b-41d4-a716-446655477700",
            "name": "Mounika"
        }
    },
   ...
   ]
   
   ```

   
## to add new user
<u>methos</u>: Post
 - <u>adds new user in db end point </u>  **/users**

  ``` Sample Request body
        {
            "fullName": "Bharath Manda",
            "panNum": "ftjn4087k",
            "mobileNumber": "8745126395",
            "manager": {
                "managerId": "550e8400-e29b-41d4-a716-446655477700"
            }
        }
  ```
- <u>Sample Response</u>
 
   ```sample response
        [
        {
            "userId": "550e8400-e29b-41d4-a716-446655478400",
            "fullName": "Bharath Manda",
            "panNum": "ftjn4087k",
            "mobileNumber": "8745126395",
            "createdAt": "2045-09-24 10:15:00",
            "updatedAt": "2024-07-27 16:26:18.485575",
            "isActive": true,
            "manager": {
                "managerId": "550e8400-e29b-41d4-a716-446655477700",
                "name": "Mounika"
            }
        },
       ...
       ]
   
   ```
   ## Get Specific user
  <u>methos</u>: Post
 - <u>get specific user form db based on mobileNumber or userId</u> **/users**
  - <u>Sample Request with optional keys</u>
  ```sample request
      {
        "userId": "550e8400-e29b-41d4-a716-446655478400",
        "mobileNumber":"8745126395"    
      }
  ```
  - <u>Sample Response</u>

  ```sample response
    [
            {
            "userId": "550e8400-e29b-41d4-a716-446655478400",
            "fullName": "Bharath Manda",
            "panNum": "ftjn4087k",
            "mobileNumber": "8745126395",
            "createdAt": "2045-09-24 10:15:00",
            "updatedAt": "2024-07-27 16:26:18.485575",
            "isActive": true,
            "manager": {
                "managerId": "550e8400-e29b-41d4-a716-446655477700",
                "name": "Mounika"
            }
        },
    ]
  ```
  - <u>based on manager Id</u>
  ```sample request
    {
      "manager":{
      "managerId":"550e8400-e29b-41d4-a716-446655477700"
        }
    }
  ```
 - <u>Sample Response</u>

 ```
[
        {
            "userId": "550e8400-e29b-41d4-a716-446655478400",
            "fullName": "Bharath Manda",
            "panNum": "ftjn4087k",
            "mobileNumber": "8745126395",
            "createdAt": "2045-09-24 10:15:00",
            "updatedAt": "2024-07-27 16:26:18.485575",
            "isActive": true,
            "manager": {
                "managerId": "550e8400-e29b-41d4-a716-446655477700",
                "name": "Mounika"
            }
        },
       ...
       ]
```
 ## delete user
  <u>methos</u>: Post
 - <u>delete user based on userId or unique mobile number</u> **/deleteUser**
  - <u>Sample Request with optional keys</u>
   ```sample request
      {
        "userId": "550e8400-e29b-41d4-a716-446655478400",
        "mobileNumber":"8745126395"    
      }
  ```
- <u>Sample Response</u>
```user deleted successfully```

## update user
 <u>update bulk users or a specific user</u> **/bulkUpdate**
 - <u>Sample Request for bulk update</u>
  ```sample request
      {
        "userIds": ["550e8400-e29b-41d4-a716-446655478400","550e8400-e29b-41d4-a716-446655864400"],
        "userObj":{
                  
                  "manager":{
                            "managerId": "550e8400-e29b-41d4-a716-446655477700"
                            }
                  }    
      }
  ```
- <u>Sample Response</u>
```Updated Sucessfully```

- <u>Sample Request for specific user updagte</u>
 ```sample request
      {
        "userIds": ["550e8400-e29b-41d4-a716-446655478400"],
        "userObj":{
                  "fullName": "Bharath Manda",
                  "panNum": "ftjn4087k",
                  "mobileNumber": "8745126395",
                  "manager":{
                            "managerId": "550e8400-e29b-41d4-a716-446655477700"
                            }
                  }    
      }
  ```
- <u>Sample Response</u>
```Updated Sucessfully```

## Demo
Here is a working live demo :  Will  add


## [Usage](will add) 

### Development
Want to contribute? Great!

To fix a bug or enhance an existing module, follow these steps:

- Fork the repo
- Create a new branch (`git checkout -b improve-feature`)
- Make the appropriate changes in the files
- Add changes to reflect the changes made
- Commit your changes (`git commit -am 'Improve feature'`)
- Push to the branch (`git push origin improve-feature`)
- Create a Pull Request 
