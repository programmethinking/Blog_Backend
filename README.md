# TECHNIQUE STACKS
REST API’s using Spring Boot, Spring Security 6, JWT, Spring Data JPA, Hibernate, MySQL, Docker &amp; Deploy on AWS

# RESTAPI_BLOG PROJECT INDRODUCE
The architecture of the project is divided into three layers: the Controller layer (API layer), the Service layer (logic layer), and the Repository layer (persistence layer). The Controller layer uses Data Transfer Objects (DTOs) to transmit data, while the Service layer uses Entity objects to store data. This structure enhances data security.

The main functionality of the project revolves around CRUD operations. Posts and comments have a one-to-many and many-to-one relationship, meaning a single post can have multiple comments. Both posts and comments support CRUD operations.

Posts and categories have a many-to-many relationship. When creating or updating a post, you can specify its category (e.g., Java), and you can retrieve all posts in a specific category using the GET operation.

The project uses Spring Security 6 for user authentication, allowing users to log in and register. However, only authorized users can perform delete, put, and create operations on posts. Certain operations such as getById, getAll, login, register, and access to Swagger UI do not require authentication.

JWT is used for encrypting usernames and passwords into tokens, which are required for user login.

The project is documented using OpenAPI, allowing you to test it directly on Swagger UI.
