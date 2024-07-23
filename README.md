<h1 align="center"> Courses </h1>
<p align="center">
  <a href="#-tecnologies">Tecnologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-project">Project</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#memo-license">License</a>
  <p align="center">
  <img alt="License" src="https://img.shields.io/static/v1?label=license&message=MIT&color=49AA26&labelColor=000000">
</p>
</p>

<br>

## 🚀 Tecnologies

This application leverages Docker and Spring Boot to build an API designed for seamless integration with Angular.

- Java & Spring Boot
- Docker
- PostgreSQL
- Spring Validation
- Git & Github 

 <br>

## 💻 Project

This project offers a straightforward API for managing courses and lessons, featuring robust validations and adhering to SOLID principles in its design.

<br>

## :memo: License


This project is under license from MIT

<br>

## Endpoints 
<p>To test the application endpoints you can use the Postman, HttpPie, Insomnia...</p>

<br>

### Get all courses
 - <p> To get all registered courses, you'll use: </p>
 
```sh
  curl -X GET http://localhost:8080/api/courses
```

<br>

### Register a course
 - <p> To register a course, you can use this JSON model: </p>
 
```sh
curl -X POST http://localhost:8080/api/courses \
     -H "Content-Type: application/json" \
     -d {
            "name": "Course title",
            "category": "Back-end",
            "lessons":
            [
                {
                "name": "Some lesson",
                "youtubeUrl":"watchl.com"
                }
            ]
        }
```

<br>

<p> this must be the received response: </p>

```json
{
    "_id": 2,
    "name": "Course title",
    "category": "Back-end",
    "lessons": [
        {
            "id": 2,
            "name": "Some lesson",
            "youtubeUrl": "watchl.com"
        }
    ]
}
```

<br>

<p>Thanks for your attention, see you next time 💜</p>

