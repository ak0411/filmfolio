
# FilmFolio API

Welcome to the FilmFolio API, the backend solution for managing film/movie data and user interactions!

## OVERVIEW
Coded with Spring Boot, FilmFolio API is a RESTful web service designed to handle film-related data and user interactions. It provides a set of endpoints that allow clients to retrieve film information, manage favorite films, post reviews, and more.


## Features

- **Film Catalog:** Retrieve a list of available films, including details like title, release year, and genre.
- **Favorite Films:** Allow users to add movies to their list of favorite films for easy access.
- **Film Reviews:** Enable users to post reviews and ratings for movies they've watched.
- **User Profiles:** Support basic user profile functionality for authentication and authorization purposes.


## Technologies

- **Backend:** Java with Spring Boot framework

- **Database:** PostgreSQL on Docker for data storage

- **Authentication:** Spring Security for user authentication

- **Data Access:** Spring Data JPA for database interaction


## API Reference

### Film Endpoints

<details>
  <summary><code>GET</code> <code><b>/api/v1/films</b></code> <code>Get all films</code></summary>
  
  #### Responses
  | Http Status     | Content-Type       | Description           |
  | :-------------- | :----------------  | :-------------------- |
  | `200 OK`        | `application/json` | Successful operation. |

</details>

<details>
  <summary><code>GET</code> <code><b>/api/v1/films/{id}</b></code> <code>Get film by id</code></summary>
  
  ##### Parameters
  | Name      | Type     | Description                       |
  | :-------- | :------- | :-------------------------------- |
  | `id`      | `string` | **Required** Id of film to fetch. |
  
  #### Responses
  | Http Status     | Content-Type       | Description               |
  | :-------------- | :----------------  | :------------------------ |
  | `200 OK`        | `application/json` | Successful operation.     |
  | `404 Not Found` | `application/json` | Film with ´id´ not found. |
</details>

<details>
  <summary><code>POST</code> <code><b>/api/v1/films/{id}/favorite</b></code> <code>Favorite film</code></summary>

   ##### Headers
  | Type            | Value            | Description                                             |
  | :-------------- | :--------------- | :-----------------------------------------------------  |
  | `Authorization` | `Bearer <token>` | **Required** Authentication token to identify the user. |

  ##### Parameters
  | Name      | Type     | Description                          |
  | :-------- | :------- | :----------------------------------  |
  | `id`      | `string` | **Required** Id of film to favorite. |
  
  #### Responses
  | Http Code       | Content-Type       | Description               |
  | :-------------  | :----------------  | :------------------------ |
  | `200 OK`        | `nosniff`          | Successful operation.     |
  | `403 Forbidden` | `nosniff`          | Unauthorized operation.   |
  | `404 Not Found` | `application/json` | Film with `id` not found. |

</details>

<details>
  <summary><code>POST</code> <code><b>/api/v1/films/{id}/unfavorite</b></code> <code>Unfavorite film</code></summary>

   ##### Headers
  | Type            | Value            | Description                                             |
  | :-------------- | :--------------- | :-----------------------------------------------------  |
  | `Authorization` | `Bearer <token>` | **Required** Authentication token to identify the user. |

  ##### Parameters
  | Name      | Type     | Description                            |
  | :-------- | :------- | :------------------------------------  |
  | `id`      | `string` | **Required** Id of film to unfavorite. |
  
  #### Responses
  | Http Code       | Content-Type       | Description               |
  | :-------------  | :----------------  | :------------------------ |
  | `200 OK`        | `nosniff`          | Successful operation.     |
  | `403 Forbidden` | `nosniff`          | Unauthorized operation.   |
  | `404 Not Found` | `application/json` | Film with `id` not found. |

</details>

<details>
  <summary><code>POST</code> <code><b>/api/v1/films/{id}/review</b></code> <code>Review film</code></summary>

  ##### Headers
  | Type            | Value            | Description                                             |
  | :-------------- | :--------------- | :-----------------------------------------------------  |
  | `Authorization` | `Bearer <token>` | **Required** Authentication token to identify the user. |

  ##### Parameters
  | Name      | Type               | Description                        |
  | :-------- | :----------------- | :--------------------------------- |
  | `id`      | `string`           | **Required** Id of film to update. |
  | `body`    | `application/json` | **Required** Film object to add.   |
  
  #### Body 
  | Field    | Type     | Description                         |
  | :------- | :------- | :---------------------------------- |
  | `text`   | `string` | **Optional** Review text.           |
  | `rating` | `integer/string`| **Required**  Rate the film. |

  The `rating` field can contain an integer value between `0 - 4` or string values:
  - TERRIBLE
  - POOR
  - AVERAGE
  - GOOD
  - EXCELLENT
    
  Example body:
  ```json
  {
    "text": "Good film, I like.",
    "rating": "EXCELLENT"
  }
  ```
  
  #### Responses
  | Http Code       | Content-Type       | Description               |
  | :-------------  | :----------------  | :------------------------ |
  | `200 OK`        | `nosniff`          | Successful operation.     |
  | `403 Forbidden` | `nosniff`          | Unauthorized operation.   |
  | `404 Not Found` | `application/json` | Film with `id` not found. |

</details>

<details>
  <summary><code>POST</code> <code><b>/api/v1/films</b></code> <code>Create film (Admin only)</code></summary>

   ##### Headers
  | Type            | Value            | Description                                             |
  | :-------------- | :--------------- | :------------------------------------------------------ |
  | `Authorization` | `Bearer <token>` | **Required** Authentication token to identify the user. |
  
  ##### Parameters
  | Name      | Type               | Description                      |
  | :-------- | :----------------- | :------------------------------- |
  | `body`    | `application/json` | **Required** Film object to add. |

  #### Body 
  | Field   | Type     | Description                     |
  | :------ | :------- | :------------------------------ |
  | `title` | `string` | **Required** Title of the film. |
  | `year`  | `integer`| **Required** Year of the film.  |
  | `genre` | `array`  | Genre(s) of the film .          |

  The `genre` field can be `null`, or an array containing one or more of the following genre values:
  - ACTION
  - COMEDY
  - DRAMA
  - ROMANCE
  - ADVENTURE
  - SCIENCE_FICTION
  - FANTASY
  - HORROR
  - THRILLER
  - ANIMATION
  - MYSTERY

  Example body:
  ```json
  {
    "title": "FILM TITLE",
    "year": 2022,
    "genre": ["ACTION", "ADVENTURE"]
  }
  ```

  #### Responses
  | Http Status     | Content-Type       | Description                        |
  | :-------------- | :----------------  | :--------------------------------- |
  | `200 OK`        | `application/json` | Successful operation.              |
  | `403 Forbidden` | `application/json` | Unauthorized or malformed request. |

</details>

<details>
  <summary><code>DELETE</code> <code><b>/api/v1/films/{id}</b></code> <code>Delete film (Admin only)</code></summary>

   ##### Headers
  | Type            | Value            | Description                                             |
  | :-------------- | :--------------- | :-----------------------------------------------------  |
  | `Authorization` | `Bearer <token>` | **Required** Authentication token to identify the user. |

  ##### Parameters
  | Name      | Type     | Description                        |
  | :-------- | :------- | :--------------------------------  |
  | `id`      | `string` | **Required** Id of film to delete. |
  
  #### Responses
  | Http Code       | Content-Type       | Description             |
  | :-------------  | :----------------  | :---------------------  |
  | `200 OK`        | `application/json` | Successful operation.   |
  | `403 Forbidden` | `nosniff`          | Unauthorized operation. |

</details>

<details>
  <summary><code>PUT</code> <code><b>/api/v1/films/{id}</b></code> <code>Update film (Admin only)</code></summary>

   ##### Headers
  | Type            | Value            | Description                                             |
  | :-------------- | :--------------- | :-----------------------------------------------------  |
  | `Authorization` | `Bearer <token>` | **Required** Authentication token to identify the user. |

  ##### Parameters
  | Name      | Type               | Description                        |
  | :-------- | :----------------- | :--------------------------------- |
  | `id`      | `string`           | **Required** Id of film to update. |
  | `body`    | `application/json` | **Required** Film object to add.   |

  #### Body 
  | Field   | Type     | Description                     |
  | :------ | :------- | :------------------------------ |
  | `title` | `string` | **Required** Title of the film. |
  | `year`  | `integer`| **Required** Year of the film.  |
  | `genre` | `array`  | Genre(s) of the film.           |

  The `genre` field can be `null`, or an array containing one or more of the following genre values:
  - ACTION
  - COMEDY
  - DRAMA
  - ROMANCE
  - ADVENTURE
  - SCIENCE_FICTION
  - FANTASY
  - HORROR
  - THRILLER
  - ANIMATION
  - MYSTERY

  Example body:
  ```json
  {
    "title": "FILM TITLE",
    "year": 2022,
    "genre": ["ACTION", "ADVENTURE"]
  }
  ```
  
  #### Responses
  | Http Code       | Content-Type       | Description             |
  | :-------------  | :----------------  | :---------------------- |
  | `200 OK`        | `application/json` | Successful operation.   |
  | `403 Forbidden` | `nosniff`          | Unauthorized operation. |

</details>

### Authentication Endpoints

<details>
  <summary><code>POST</code> <code><b>/api/v1/auth/signup</b></code> <code>Sign up</code></summary>

  ##### Parameters
  | Name      | Type               | Description                        |
  | :-------- | :----------------- | :--------------------------------- |
  | `body`    | `application/json` | **Required** Film object to add.   |

  #### Body 
  | Field      | Type     | Description                                         |
  | :--------- | :------- | :-------------------------------------------------  |
  | `name`     | `string` | **Optional** Name of the account.                   |
  | `username` | `string` | **Required** A unique username.                     |
  | `password` | `string` | **Required** A password.                            |
  | `role`     | `string` | **Required** Role of the account `ADMIN` or `USER`. |

  Example body:
  ```json
  {
    "name": "Bob Man",
    "username": "bob",
    "password": "bob123",
    "role": "USER"
  }
  ```
  
  #### Responses
  | Http Code       | Content-Type | Description                                 |
  | :-------------  | :----------  | :-----------------------------------------  |
  | `201 Created`   | `nosniff`    | Successful operation.                       |
  | `403 Forbidden` | `nosniff`    | Malformated body or username already exist. |

</details>

<details>
  <summary><code>POST</code> <code><b>/api/v1/auth/signin</b></code> <code>Sign in</code></summary>

  ##### Parameters
  | Name      | Type               | Description                        |
  | :-------- | :----------------- | :--------------------------------- |
  | `body`    | `application/json` | **Required** Film object to add.   |

  #### Body 
  | Field      | Type     | Description                     |
  | :--------- | :------- | :------------------------------ |
  | `username` | `string` | **Required** A unique username. |
  | `password` | `string` | **Required** A password.        |

  Example body:
  ```json
  {
    "username": "bob",
    "password": "bob123"
  }
  ```
  
  #### Responses
  | Http Code       | Content-Type       | Description                      |
  | :-------------  | :----------------- | :------------------------------  |
  | `200 OK`        | `application/json` | Successful operation.            |
  | `403 Forbidden` | `nosniff`          | Unauthorized or malformated body |

</details>

### User Endpoints

<details>
  <summary><code>GET</code> <code><b>/api/v1/users</b></code> <code>Get all users</code></summary>
  
  #### Responses
  | Http Status     | Content-Type       | Description           |
  | :-------------- | :----------------  | :-------------------- |
  | `200 OK`        | `application/json` | Successful operation. |

</details>

<details>
  <summary><code>GET</code> <code><b>/api/v1/users/{id}</b></code> <code>Get user by id</code></summary>
  
  ##### Parameters
  | Name      | Type     | Description                       |
  | :-------- | :------- | :-------------------------------- |
  | `id`      | `string` | **Required** Id of user to fetch. |
  
  #### Responses
  | Http Status     | Content-Type       | Description               |
  | :-------------- | :----------------  | :------------------------ |
  | `200 OK`        | `application/json` | Successful operation.     |
  | `404 Not Found` | `application/json` | User with ´id´ not found. |
</details>


## Roadmap

- [ ] Building a frontend application to consume the API and provide a user-friendly interface.
- [ ] Enhancing user interaction features such as search, recommendations, and user profiles.

