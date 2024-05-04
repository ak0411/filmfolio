
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

### Films Endpoint

#### Retrieve a list of all available films

```http
  GET /api/v1/films
```

#### Retrieve a film

```http
  GET /api/v1/films/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of film to fetch.|

#### Create a film (Admin only)

```http
  POST /api/v1/films
```

| Header | Value     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <token>` | **Required**. Authentication token to identify the user. |

Example:
```json
{
  "title": "FILM TITLE",
  "year": 2022,
  "genre": ["ACTION", "ADVENTURE"]
}
```

| Field | Type     | Description                           |
| :-------- | :------- | :------------------------------------ |
| `title`   | `string` | **Required**. Title of the film.      |
| `year`    | `integer`| **Required**. Year of the film.       |
| `genre`   | `array`  | Genre(s) of the film.                 |

The `genre` field can be `null` or an array containing one or more of the following genre values:
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

#### Delete a film (Admin only)

```http
  DELETE /api/v1/films/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of film to delete.|

| Header | Value     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <token>` | **Required**. Authentication token to identify the user. |

#### Update a film (Admin only)

```http
  PUT /api/v1/films/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of film to update.|

| Header | Value     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <token>` | **Required**. Authentication token to identify the user. |

#### Favorite/unfavorite a film

```http
  POST /api/v1/films/${id}/favorite
```

```http
  POST /api/v1/films/${id}/unfavorite
```


| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of film to favorite/unfavorite |

| Header | Value     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <token>` | **Required**. Authentication token to identify the user. |

#### Review a film

```http
  POST /api/v1/films/${id}/review
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of film to review.|

| Header | Value     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <token>` | **Required**. Authentication token to identify the user. |

Example:
```json
{
  "text": "Good film, I like.",
  "rating": "EXCELLENT"
}
```

| Field | Type     | Description                           |
| :-------- | :------- | :------------------------------------ |
| `text`   | `string` | **Optinal**. review text.      |
| `rating`    | `string`| **Required**. Year of the film.       |

The `rating` field can contain an integer value between `0 - 4` or string values:
- TERRIBLE
- POOR
- AVERAGE
- GOOD
- EXCELLENT

### Authentication Endpoint

#### Sign Up

```http
  POST /api/v1/auth/signup
```

Example:
```json
{
  "name": "Bob Man",
  "username": "bob",
  "password": "bob123",
  "role": "USER"
}
```
| Field | Type     | Description                                |
| :-------- | :------- | :------------------------------------  |
| `name`   | `string` | **Optional**. Name of the account.      |
| `username`   | `string` | **Required**. A unique username.    |
| `password`    | `string`| **Required**. A password.           |
| `role`   | `string`  | **Required**. Role of the account `ADMIN` or `USER`. |

#### Sign In

```http
  POST /api/v1/auth/signin
```

Example:
```json
{
  "username": "bob",
  "password": "bob123"
}
```
| Field | Type     | Description                                |
| :-------- | :------- | :------------------------------------  |
| `username`   | `string` | **Required**. Username of an existing user. |
| `password`    | `string`| **Required**. Password of an existing user. |

### Users Endpoint

#### Retrieve a list of all users

```http
  GET /api/v1/users
```

#### Retrieve a specific user

```http
  GET /api/v1/users/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of user to fetch |

## Roadmap

- [ ] Building a frontend application to consume the API and provide a user-friendly interface.
- [ ] Enhancing user interaction features such as search, recommendations, and user profiles.

