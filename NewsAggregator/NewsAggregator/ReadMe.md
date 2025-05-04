# News Aggregator
### by Jyothir Venkata Akhilesh Munagala



**News Aggregator** is a Spring Boot application that aggregates news articles from different sources using the [NewsAPI](https://newsapi.org/). The project includes user authentication, JWT-based security, a caching mechanism, and the ability for users to interact with news articles (marking them as read or favorite).

## Features

- **User Registration and Authentication**: JWT-based authentication for secure access.
- **News Aggregation**: Fetches top headlines and articles from NewsAPI.
- **Caching**: Caching of news articles to avoid redundant API calls for performance.
- **Read & Favorite News**: Allows users to mark articles as read or favorite.
- **User Preferences**: Users can set preferences for news categories and sources.

## Tech Stack

- **Java** 17
- **Spring Boot** 3.x
- **Spring Security** for user authentication
- **JJWT** for JWT token handling
- **H2 Database** for local storage (in-memory database)
- **Spring Data JPA** for database access
- **JUnit** for testing


### **Explanation of Project Directories and Files**

#### 1. **`Controller/`**
This package contains the REST controllers that expose the API endpoints to handle user requests.

- **`AuthController.java`**: Handles user registration, login, and token generation.
- **`NewsController.java`**: Manages news-related requests like fetching articles, marking articles as read/favorite, and fetching favorite/read articles.

#### 2. **`Entity/`**
This package holds the JPA entities that map to the database tables.

- **`User.java`**: Represents a user in the system. Contains fields like `username`, `password`, `roles`, and `preferences`.
- **`News.java`**: Represents a news article and stores attributes like `title`, `description`, `url`, and read/favorite status.

#### 3. **`Filter/`**
This package contains filters for the application. Filters are used to handle tasks like authentication before the request reaches the controller.

- **`JwtAuthFilter.java`**: Intercepts HTTP requests to extract the JWT token, validate it, and authenticate the user.

#### 4. **`Repository/`**
This package contains interfaces that extend Spring Data JPA's `JpaRepository`. These interfaces are responsible for interacting with the database to perform CRUD operations.

- **`NewsRepository.java`**: Provides database operations for the `News` entity, such as fetching articles based on read/favorite status.
- **`UserRepository.java`**: Provides database operations for the `User` entity, including fetching users by `username`.

#### 5. **`Service/`**
This package contains the business logic of the application. Services process data and handle the core functionality.

- **`NewsService.java`**: Fetches news articles from the NewsAPI, marks articles as read or favorite, and caches news articles using Redis.
- **`UserService.java`**: Manages user operations like registration, login, updating user roles, and preferences.

#### 6. **`Util/`**
This package contains utility classes that perform helper functions.

- **`JwtUtil.java`**: Handles the generation and parsing of JWT tokens. It provides methods to create tokens, extract the username from tokens, and check if the token is expired.

#### 7. **`TestRunner.java`**
A class used for manual testing. It is invoked on application startup to simulate registering and logging in a user, testing the basic flow of the application.

---
