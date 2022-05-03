# Web service API

The web service provide access to our application via RESTful API. Both customer and shop owner can use the API to access the data but only shop owner can modify the data.

## Common info about this API

- This API always accept the parameters via the JSON and respond in JSON format, so you don't need to fill the header with the content type.
- If API is successful, the response status code is 200 with JSON data. Else, the response status code will apply by the situation.
- We have policy that no one cannot editing the purchase history data so the purchase history has only get information, cannot be modified.

## Authentication

Some API endpoint require authentication. You must get the authentication token from the system first before you can access the API that require authentication.

## API list

### Get list of books in database

Return all available books in database. No authentication is required.

This endpoint also support query parameters. If query parameters get filled, it will return the result of query instead.

If the result is blank, it will return a blank list `[]`.

See more info on response data format in [book object structure](#book).

    GET /api/book

#### Data parameters

Note that the data parameters are optional. You can omit the header parameters if you don't want to query some parameters.

| name        | type   | description                                       |
|-------------|--------|---------------------------------------------------|
| `title`     | string | The title of the book                             |
| `author`    | string | The author of the book                            |
| `genre`     | string | The genre of the book                             |
| `subgenre`  | string | The subgenre of the book                          |
| `height`    | int    | The number of pages of the book                   |
| `publisher` | string | The publisher of the book                         |
| `price`     | real   | The price of the book                             |

#### HTTP response status code

- 200: Success
- 503: Service unavailable

#### Example usage via `curl`

```shell
# Without query
curl -X GET https://book-shop.com/api/book
# With query
curl -X GET -d '{"genre":"tech"}' https://book-shop.com/api/book
```

#### Example response

Status code 200:

```json
[
  {
    "id": 1,
    "title": "Fundamentals of Wavelets",
    "author": "Goswami, Jaideva",
    "genre": "tech",
    "subgenre": "signal_processing",
    "height": 228,
    "publisher": "Wiley",
    "price": 8.92
  }, {...}, {...}
]
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```

### Get the book via book's ID

Return the `Book` object that match the book's ID. No authentication is required.

See more info on response data format in [book object structure](#book).

    GET /api/book/{book_id}

#### Path parameters

- `book_id`: The book's ID

#### HTTP response status code

- 200: Success
- 404: Book not found
- 503: Service unavailable

#### Example usage via `curl`

```shell
curl -X GET https://book-shop.com/api/book/1
```

#### Example response

Status code 200:

```json
{
    "id": 1,
    "title": "Fundamentals of Wavelets",
    "author": "Goswami, Jaideva",
    "genre": "tech",
    "subgenre": "signal_processing",
    "height": 228,
    "publisher": "Wiley",
    "price": 8.92
}
```

Status code 404:

```json
{
  "message": "Book not found"
}
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```

### Add a new book

Add a new book to the database. This endpoint require authentication with token authentication. The token permission must be shop owner or staff.

    POST /api/book

#### Data parameters

In this endpoint, you must fill all the data parameters below:

| name        | type   | description                                  |
|-------------|--------|----------------------------------------------|
| `token`     | string | The shop owner or staff authentication token |
| `title`     | string | The title of the book                        |
| `author`    | string | The author of the book                       |
| `genre`     | string | The genre of the book                        |
| `subgenre`  | string | The subgenre of the book                     |
| `height`    | int    | The number of pages of the book              |
| `publisher` | string | The publisher of the book                    |
| `price`     | real   | The price of the book                        |

#### HTTP response status code

- 200: Success
- 403: Forbidden
- 503: Service unavailable

#### Example usage via `curl`

```shell
curl -X POST -d '{"token":"shop-owner-token","title":"Fundamentals of Wavelets","author":"Goswami, Jaideva","genre":"tech","subgenre":"signal_processing","height":228,"publisher":"Wiley","price":8.92}' https://book-shop.com/api/book
```

#### Example response

Status code 200:

```json
{
  "message": "Book added successfully"
}
```

Status code 403:

```json
{
  "message": "You don't have permission to do this"
}
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```

### Update a book via book's ID

Update the book information in database using book's ID to determine a book and update using the information given in data parameters into the designated book. This endpoint require authentication with token authentication. The token permission must be shop owner or staff.

    PATCH /api/book

#### Data parameters

Required parameters:

| name    | type   | description                                  |
|---------|--------|----------------------------------------------|
| `token` | string | The shop owner or staff authentication token |
| `id`    | int    | The book's ID                                |

Optional parameters (information that will be updated):

| name        | type   | description                                       |
|-------------|--------|---------------------------------------------------|
| `title`     | string | The title of the book                             |
| `author`    | string | The author of the book                            |
| `genre`     | string | The genre of the book                             |
| `subgenre`  | string | The subgenre of the book                          |
| `height`    | int    | The number of pages of the book                   |
| `publisher` | string | The publisher of the book                         |
| `price`     | real   | The price of the book                             |

#### HTTP response status code

- 200: Success
- 403: Forbidden
- 503: Service unavailable

#### Example usage via `curl`

```shell
curl -X PATCH -d '{"token":"shop-owner-token","id": 1,"title":"peppy adventure"}' https://book-shop.com/api/book
```

#### Example response

Status code 200:

```json
{
  "message": "Book updated successfully"
}
```

Status code 403:

```json
{
  "message": "You don't have permission to do this"
}
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```

### Delete a book via book's ID

Delete a book from the database using book's ID to determine a book and delete it. This endpoint require authentication with token authentication. The token permission must be shop owner or staff.

    DELETE /api/book

#### Data parameters

All the data parameters are required:

| name    | type   | description                                  |
|---------|--------|----------------------------------------------|
| `token` | string | The shop owner or staff authentication token |
| `id`    | int    | The book's ID                                |

#### HTTP response status code

- 200: Success
- 403: Forbidden
- 503: Service unavailable

#### Example usage via `curl`

```shell
curl -X DELETE -d '{"token":"shop-owner-token","id": 1}' https://book-shop.com/api/book
```

#### Example response

Status code 200:

```json
{
  "message": "Book deleted successfully"
}
```

Status code 403:

```json
{
  "message": "You don't have permission to do this"
}
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```

### Get list of all users in database

Return all users in the database. This endpoint require authentication with token authentication. The token permission must be shop owner or staff.

If the list result is blank, it will return a blank list `[]`.

See more info on response data format in [user object structure](#user).

    GET /api/user

#### HTTP response status code

- 200: Success
- 403: Forbidden
- 503: Service unavailable

#### Example usage via `curl`

```shell
# Without query
curl -X GET https://book-shop.com/api/user
# With query
curl -X GET -d '{"username":"Kasumi"}' https://book-shop.com/api/user
```

#### Example response

Status code 200:

```json
[
  {
    "id": 1,
    "username": "Kasumi"
  }, {...}, {...},
]
```

Status code 403:

```json
{
  "message": "You don't have permission to do this"
}
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```

### Get the user with query

Return the `user` object that match the query parameters. This endpoint only allow token authentication. The token permission must be shop owner or staff.

See more info on response data format in [user object structure](#user).

    GET /api/user

#### Data parameters

At least one of the data parameters is required:

| name       | type   | description                                       |
|------------|--------|---------------------------------------------------|
| `id`       | int    | The unique identifier of the user in the database |
| `username` | string | The username of the user                          |

#### HTTP response status code

- 200: Success
- 403: Forbidden
- 404: User not found
- 503: Service unavailable

#### Example usage via `curl`

```shell
curl -X GET -d '{"id": 1}' https://book-shop.com/api/user
```

#### Example response

Status code 200:

```json
{
    "id": 1,
    "username": "Kasumi"
}
```

Status code 403:

```json
{
  "message": "You don't have permission to do this"
}
```

Status code 404:

```json
{
  "message": "User not found"
}
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```

### Add a new user

Add a new user to the database. This endpoint require authentication with token authentication. The token permission must be shop owner or staff.

    POST /api/book

#### Data parameters

In this endpoint, you must fill all the data parameters below:

| name        | type   | description                                       |
|-------------|--------|---------------------------------------------------|
| `username`  | string | The username of the user                          |

#### HTTP response status code

- 200: Success
- 403: Forbidden
- 503: Service unavailable

#### Example usage via `curl`

```shell
curl -X POST -d '{"username":"Yeew"}' https://book-shop.com/api/user
```

#### Example response

Status code 200:

```json
{
  "message": "User added successfully"
}
```

Status code 403:

```json
{
  "message": "You don't have permission to do this"
}
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```

### Update a user via user's ID

Update the user information in database using user's ID to determine user and update using the information given in data parameters into the designated book. This endpoint require authentication with token authentication. The token permission must be shop owner or staff.

    PATCH /api/user

#### Data parameters

All the data parameters are required since the user's information only have username.

| name       | type   | description                                       |
|------------|--------|---------------------------------------------------|
| `token`    | string | The shop owner or staff authentication token      |
| `id`       | int    | The unique identifier of the user in the database |
| `username` | string | The username of the user                          |

#### HTTP response status code

- 200: Success
- 403: Forbidden
- 503: Service unavailable

#### Example usage via `curl`

```shell
curl -X PATCH -d '{"token":"shop-owner-token","id": 1,"username": "peepyp"}' https://book-shop.com/api/user
```

#### Example response

Status code 200:

```json
{
  "message": "User updated successfully"
}
```

Status code 403:

```json
{
  "message": "You don't have permission to do this"
}
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```

### Delete a user via user's ID

Delete a user from the database using user's ID to determine user and delete it. This endpoint require authentication with token authentication. The token permission must be shop owner or staff.

    DELETE /api/user

#### Data parameters

All the data parameters are required:

| name       | type   | description                                       |
|------------|--------|---------------------------------------------------|
| `token`    | string | The shop owner or staff authentication token      |
| `id`       | int    | The unique identifier of the user in the database |

#### HTTP response status code

- 200: Success
- 403: Forbidden
- 503: Service unavailable

#### Example usage via `curl`

```shell
curl -X DELETE -d '{"token":"shop-owner-token","id": 1}' https://book-shop.com/api/user
```

#### Example response

Status code 200:

```json
{
  "message": "User deleted successfully"
}
```

Status code 403:

```json
{
  "message": "You don't have permission to do this"
}
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```
### Get list of purchase history in database

Return all available purchase history in database. This endpoint require authentication with token authentication. The token permission must be shop owner or staff.

This endpoint also support query parameters. If query parameters get filled, it will return the result of query instead.

If the result is blank, it will return a blank list `[]`.

See more info on response data format in [book object structure](#book).

    GET /api/purchase

#### Data parameters

Required parameters:

| name       | type   | description                                       |
|------------|--------|---------------------------------------------------|
| `token`    | string | The shop owner or staff authentication token      |

Note that the data parameters are optional. You can omit the header parameters if you don't want to query some parameters.

| name             | type   | description                                       |
|------------------|--------|---------------------------------------------------|
| `book_id`        | int    | The unique identifier of the book in the database |
| `book_title`     | string | The title of the book                             |
| `book_author`    | string | The author of the book                            |
| `book_genre`     | string | The genre of the book                             |
| `book_subgenre`  | string | The subgenre of the book                          |
| `book_height`    | int    | The number of pages of the book                   |
| `book_publisher` | string | The publisher of the book                         |
| `book_price`     | real   | The price of the book                             |
| `user_id`        | int    | The unique identifier of the user in the database |
| `username`       | string | The user's username who purchased the book        |



#### HTTP response status code

- 200: Success
- 503: Service unavailable

#### Example usage via `curl`

```shell
# Without query
curl -X GET https://book-shop.com/api/purchase
# With query
curl -X GET -d '{"user_id": 1}' https://book-shop.com/api/purchase
```

#### Example response

Status code 200:

```json
[
  {
    "id": 1,
    "buyer": {
        "id": 1,
        "username": "Kasumi"
    },
    "book": {
        "id": 1,
        "title": "Fundamentals of Wavelets",
        "author": "Goswami, Jaideva",
        "genre": "tech",
        "subgenre": "signal_processing",
        "height": 228,
        "publisher": "Wiley",
        "price": 8.92
    }
  }, {...}, {...},
]
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```

### Add a new purchase history

Add a new purchase history to the database. This endpoint require authentication with token authentication. The token permission must be shop owner or staff.

    POST /api/purchase

#### Data parameters

| name       | type   | description                                  |
|------------|--------|----------------------------------------------|
| `token`    | string | The shop owner or staff authentication token |
| `buyer_id` | int    | The user's ID who buy the book               |
| `book_id`  | int    | The book's ID                                |

#### HTTP response status code

- 200: Success
- 403: Forbidden
- 503: Service unavailable

#### Example usage via `curl`

```shell
curl -X POST -d '{"token":"shop-owner-token", "buyer_id": 1, "book_id": 1' https://book-shop.com/api/purchase
```

#### Example response

Status code 200:

```json
{
  "message": "History added successfully"
}
```

Status code 403:

```json
{
  "message": "You don't have permission to do this"
}
```

Status code 503:

```json
{
  "message": "Service unavailable"
}
```

--------------------------------------------

## Objects structure

### `book`

The components that represents a book in the shop's database. It contains the following attributes:

-------------------------------
| name        | type   | description                                       |
|-------------|--------|---------------------------------------------------|
| `id`        | int    | the unique identifier of the book in the database |
| `title`     | string | the title of the book                             |
| `author`    | string | the author of the book                            |
| `genre`     | string | the genre of the book                             |
| `subgenre`  | string | the subgenre of the book                          |
| `height`    | int    | the number of pages of the book                   |
| `publisher` | string | the publisher of the book                         |
| `price`     | real   | the price of the book                             |

### `user`

The components that represents a user in the shop's database that is a customer. It contains the following attributes:

| name        | type   | description                                       |
|-------------|--------|---------------------------------------------------|
| `id`        | int    | the unique identifier of the user in the database |
| `username`  | string | the username of the user                          |

### `history`

The components that represents a purchase history in the shop's database. It contains the following attributes:

| name    | type          | description                                          |
|---------|---------------|------------------------------------------------------|
| `id`    | int           | the unique identifier of the history in the database |
| `buyer` | [user](#user) | the user information who buy the book                |
| `book`  | [book](#book) | the book's information                               |