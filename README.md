# RowingBG_Achievements Server

This is a course project for "**Modern Java Technologies**". It creates backend as an **API** for Rowing Management System using Spring Boot.

## Project Overview

The **RowingBG_Achievements Server** is a **backend** application developed using **Java** and **Spring Boot**.

It is designed to manage various aspects of rowing achievements, including rowers, their ID cards, users and their roles.

## Technologies Used

- **Java** 17
- **Spring Boot**
- **Maven**
- **PostgreSQL**
- **JPA/Hibernate**
- **Spring Security**
- **Postman** [(see collection here)](https://www.postman.com/aerospace-astronomer-53398759/workspace/rowingmanagementws/collection/26566947-c4581f1b-5d53-4c96-944f-cc6fd4597c9b?action=share&creator=26566947&active-environment=26566947-2c5ae71d-0e16-45b6-9fd8-e3d358a93c6b)


## Features

- User registration and authentication
- Management of rowers and their ID cards
- Role-based access control

## API Endpoints

The API endpoints for this project are documented and can be tested using Postman. You can access the Postman collection for this project at the following URL:
[Postman Collection](https://www.postman.com/aerospace-astronomer-53398759/workspace/rowingmanagementws/collection/26566947-c4581f1b-5d53-4c96-944f-cc6fd4597c9b?action=share&creator=26566947&active-environment=26566947-2c5ae71d-0e16-45b6-9fd8-e3d358a93c6b)

Here is a brief overview of the available endpoints:
- `POST /auth/login`: Authenticate a user.
- `POST /auth/register`: Register a new user.
- `GET /auth/roles`: Get all roles.
- `POST /rowers`: Create a new rower.
- `GET /rowers/{id}`: Get a rower by ID.
- `GET /rowers`: Get all rowers.
- `GET /rowers/byYearOfBirth/{yearOfBirth}`: Get all rowers by year of birth.
- `PUT /rowers/update/{id}`: Update a rower by ID.
- `DELETE /rowers/delete/{id}`: Delete a rower by ID.
- `DELETE /rowers/delete`: Delete a rower by ID (using request param).
- `GET /rowersIDCards`: Get all rower ID cards.
- `GET /rowersIDCards/{rowerID}`: Get all rower ID cards by rower ID.
- `POST /rowersIDCards`: Create a new rower ID card.
- `PUT /rowersIDCards/update`: Update a rower ID card by card number.
- `DELETE /rowersIDCards/delete/{id}`: Delete a rower ID card by ID.
- `DELETE /rowersIDCards/delete`: Delete a rower ID card by card number (using request param).
- `GET /users`: Get all users.

Please refer to the [Postman Collection](https://www.postman.com/aerospace-astronomer-53398759/workspace/rowingmanagementws/collection/26566947-c4581f1b-5d53-4c96-944f-cc6fd4597c9b?action=share&creator=26566947&active-environment=26566947-2c5ae71d-0e16-45b6-9fd8-e3d358a93c6b)
for more details on the request and response formats for each endpoint.

## Setup and Installation

1. **Clone the Repository**: First, clone this repository to your local machine using `git clone <https://github.com/Bzahov98/achievementsBG.server/>`.

2. **Install Dependencies**: This project uses Maven as a build tool. You can install the necessary dependencies by running `mvn install` in the project root directory.

3. **Configure Database**: This project uses a PostgreSQL database. You need to create a database and update the `application.properties` file with your database name, username, and password.

4. **Run Application Server**

5. **Default Port**: By default, the application will start on port **3000**. If you wish to change the port, you can do so by updating the **`server.port`** property in the **`application.properties`** file.

6. **Accessing the API**: Once the application is running, you can access the API endpoints through **`http://localhost:<port>/api/v1`**. Replace `<port>` with the port number you have configured.

[//]: <> (7. **API Documentation**: The API endpoints are documented using Swagger. You can access the API documentation at `http://localhost:<port>/swagger-ui.html`.)

[//]: <> (8. **Testing**: This project includes unit tests and integration tests. You can run the tests using the command `mvn test`. )

7. **Security**:
    * The application uses JWT for authentication. To access protected routes (all excluding /auth/ ones) you need to include the JWT token in the `Authorization` header of your requests.
    * In Postman if you log in successfully JWT authentication token will be added to all protected routes automatically.
    * With each restart of the server you need to log in again!

8. **Postman Collection**: You can import the Postman collection from the provided link to test the API endpoints. [(see collection here)](https://www.postman.com/aerospace-astronomer-53398759/workspace/rowingmanagementws/collection/26566947-c4581f1b-5d53-4c96-944f-cc6fd4597c9b?action=share&creator=26566947&active-environment=26566947-2c5ae71d-0e16-45b6-9fd8-e3d358a93c6b)
