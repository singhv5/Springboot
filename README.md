# Social Media Application

This is a Spring Boot project demonstrating the creation of RESTful APIs with the integration of H2 Database using JPA and Hibernate. Additionally, MySQL is used as a Docker container to showcase the configuration with an external database.

## Features

- User Management: Create, read, update, and delete users.
- Post Management: Users can create, read, update, and delete their posts.

## Technologies Used

- Spring Boot
- H2 Database
- MySQL
- JPA (Java Persistence API)
- Hibernate
- Docker

## Prerequisites

Before running the application, ensure you have the following installed:

- Java Development Kit (JDK) 8 or later
- Maven
- Docker (if you want to use MySQL as a Docker container)

## Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/singhv5/Springboot.git
    ```

2. Navigate to the project directory:

    ```bash
    cd social-media-application
    ```

3. If you want to use MySQL as a Docker container, run the following command to pull and run the MySQL Docker image:

    ```bash
    docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=<password> -e MYSQL_DATABASE=<database_name> -p 3306:3306 mysql:latest
    ```

    Replace `<password>` with your desired MySQL root password and `<database_name>` with the name of the database.

4. Build the project using Maven:

    ```bash
    mvn clean package
    ```

5. Run the application:

    ```bash
    java -jar target/social-media-application.jar
    ```

## Usage

- Access the application through the following URL: `http://localhost:8080`

- Use tools like Postman or curl to interact with the RESTful APIs provided by the application.

## API Endpoints

- **GET /users**: Retrieve all users.
- **GET /users/{userId}**: Retrieve a specific user by ID.
- **POST /users**: Create a new user.
- **PUT /users/{userId}**: Update an existing user.
- **DELETE /users/{userId}**: Delete a user.

- **GET /users/{userId}/posts**: Retrieve all posts of a specific user.
- **GET /users/{userId}/posts/{postId}**: Retrieve a specific post of a specific user.
- **POST /users/{userId}/posts**: Create a new post for a specific user.
- **PUT /users/{userId}/posts/{postId}**: Update an existing post of a specific user.
- **DELETE /users/{userId}/posts/{postId}**: Delete a post of a specific user.

## Configuration

- Database configurations can be modified in `application.properties` file.

## Contributing

Contributions are welcome! Please feel free to submit a pull request.
