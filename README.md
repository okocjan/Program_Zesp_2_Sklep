# Program_Zesp_2_Sklep

### Prerequisites:

1. Docker
2. IntelliJ (Ultimate version is recommended)
3. Java 17
4. Maven

### How to start?

1. Download docker from https://www.docker.com
2. Download and Create default container for PostgreSQL
3. Create database called ```storedb``` on your docker container
4. Verify that port for database connection in ```src/main/resources/application.yml``` is same as in your docker container
5. Load maven project (Popup in IntelliJ should show up)
6. Run application by clicking green arrow in ```src/main/java/com/example/demo/DemoApplication.java```