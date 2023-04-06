# Mastercard Spring Project
This is a Java program that checks if two cities are connected by a direct road or a indirect road.
- Direct road. There's a record in the City.txt: Boston, New York. Therefore, we know there's a connection between the origin city Boston and the destination city NewYork.
- Indirect road. See the three records from the City.txt: Boston-NewYork, Philadelphia-Newark, and Newark-Boston. Boston to Philadelphia is an indirect road.

## Getting Started
1. Clone the repository from https://github.com/z-git-code/MastercardSpringProject.git
2. See Prerequisites for necessary software.
3. Import the project into your favorite IDE.
4. Select your SQL database and change the configuration in the "application.properties" file .
5. Run the Spring Boot application.
6. Navigate to the url in browser: http://localhost:8080/connected?origin=Boston&destination=Newark.
At this point you should see the anwswer "yes".

### Prerequisites

* Maven
* Java
* MySQL
* IDE of your choice
