## Students
`http://localhost:8080/student-management-system/api/v1/students/create`

### POST: Add new student, (see example below).

```
{
    "firstName": "Gunnar",
    "lastName": "Gunnarsson",
    "email": "Gunnar@gunnar.se",
    "phoneNumber": "07077077070707"
}
```
### GET: Returns all students

`http://localhost:8080/student-management-system/api/v1/students/getall`


### GET: Find Student by Id

`http://localhost:8080/student-management-system/api/v1/students/id`

### DELETE: Delete Student by Id

`http://localhost:8080/student-management-system/api/v1/students/delete/id`

### UPDATE: Update Student by Id

`http://localhost:8080/student-management-system/api/v1/students/update`
```
{
"id": "2",
"firstname": "Jill",
"lastName" : "Arnold",
"email" : "jill@gmail",
"phoneNumber" : "77777777"
}
```


