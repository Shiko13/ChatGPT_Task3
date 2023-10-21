# Healthcare Management System

The Healthcare Management System is a web application that allows healthcare providers to manage patient records, schedule appointments, and prescribe medications. The system also integrates with Google Maps to provide directions to healthcare facilities.

## Features

- **Patient Records Management**: Create, read, update, and delete patient records.
- **Appointment Scheduling**: Schedule appointments for patients.
- **Medication Prescriptions**: Prescribe medications for patients.
- **Google Maps Integration**: Get directions to healthcare facilities.

## Prerequisites

Before running the application, make sure you have the following installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL Database](https://dev.mysql.com/downloads/)
- [Google Maps API Key](https://developers.google.com/maps/gmp-get-started)

## Setup

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/your/repo.git
    ```

2. Open the `application.properties` file located in `src/main/resources` and update the MySQL database connection details and your Google Maps API Key.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/chatgpt_task3
    spring.datasource.username=root
    spring.datasource.password=rootroot
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    ```

3. Build and run the application using Maven:

    ```bash
    mvn spring-boot:run
    ```

4. The application will start, and you can access it in your web browser at [http://localhost:8080](http://localhost:8080).

## API Endpoints

- **GET /patients**: Get a list of all patients.
- **GET /patients/{id}**: Get a patient by ID.
- **POST /patients**: Create a new patient.
- **PUT /patients/{id}**: Update a patient by ID.
- **DELETE /patients/{id}**: Delete a patient by ID.
- **GET /appointments**: Get a list of all appointments.
- **GET /appointments/{id}**: Get an appointment by ID.
- **POST /appointments**: Create a new appointment.
- **PUT /appointments/{id}**: Update an appointment by ID.
- **DELETE /appointments/{id}**: Delete an appointment by ID.
- **GET /medications**: Get a list of all medications.
- **GET /medications/{id}**: Get a medication by ID.
- **POST /medications**: Create a new medication.
- **PUT /medications/{id}**: Update a medication by ID.
- **DELETE /medications/{id}**: Delete a medication by ID.

## Google Maps Integration

The application provides a feature to get directions to healthcare facilities. To use this feature, select a healthcare facility and click the "Get Directions" button. You'll be redirected to a Google Maps page with directions.

## Contributing

Feel free to contribute to this project by submitting issues or pull requests.

## License

This project is licensed under the MIT License.

### Questions and answers
- Was it easy to complete the task using AI?
  Basically yes, but there were the problems with tests.
- How long did task take you to complete? (Please be honest, we need it to gather anonymized statistics)
  Basic app about 60 minutes, tests and coverage about 40 minutes
- Was the code ready to run after generation? What did you have to change to make it usable?
  Almost. I needed to add some properties for database connection, a few dependencies and getter/setter for entities.
- Which challenges did you face during completion of the task?
  The tests and Google API were most complicated part.
- Which specific prompts you learned as a good practice to complete the task?
  Short phrases with specific rules, code in ''', another prompts

