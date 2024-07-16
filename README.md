## Course Registration Application Details

### User Stories

As any kind of user, I can:

-   [ ] login with my existing credentials //done

As a faculty member I can:

-   [ ] add new classes to the registration catalog //done
-   [ ] change the registration details for a class
-   [ ] remove a class from the registration catalog (this should unregister all registered students)

As a student, I can:

-   [ ] register a new account with the system (must be secured with a password) //done
-   [ ] view classes available for registration //done
-   [ ] register for an open and available class //done
-   [ ] cancel my registration for a class (if within window) //done
-   [ ] view the classes that I have registered for //done

## Minimum Features

All project concepts must display the following domain-independent features/requirements:

-   [ ] Basic validation of user input (e.g. no registration for classes outside of registration window.)
-   [ ] Unit tests for all business-logic classes
-   [ ] All exceptions are properly caught and handled
-   [ ] Proper use of OOP principles
-   [ ] Database is 3rd Normal Form Compliant
-   [ ] Referential integrity (e.g. if a class is removed from the catalog, no students should be registered for it)
-   [ ] Logging messages and exceptions to a file using a logger
-   [ ] Generation of basic design documents (e.g. relational diagram, class diagram, flows, etc.)

## Tech Stack

Regardless of the project concept you choose, you will be required to adhere to the following tech stack:

-   [ ] Java 8
-   [ ] Apache Maven
-   [ ] PostgreSQL
-   [ ] Git(hosted on GitHub)
-   [ ] HTTP/REST with Javalin

## Repo Instructions

-   Create a new repository within this organization team and (naming convention: `firstname_lastname_p0`)
-   Keep this repository up to date with frequent commits and pushes (follow best practices!)
-   It is recommended (though not required) that you implement some kind of branching strategy when implementing new features into your application

### Questions

- Will we need a User and Course models and services so that we can conduct all these actions in addition to the Faculty and Student ones?
- The tests that we are writing are purely for the Service layer, no Controller tests?

## Presentation & Deadlines

### Checkpoint (Friday July 12th 2024)

-   [ ] Data persisted to the database
-   [ ] Able to explain the functional code for all three layers of your API
-   [ ] Code posted to github organization in the appropriate repository, with commit history.

### P0 (Friday July 19th 2024)

-   [ ] finalized version of application must be pushed to personal repository within this organization by the presentation date
-   [ ] NO LONGER THAN 7min live demonstration of the implemented features through POSTMAN