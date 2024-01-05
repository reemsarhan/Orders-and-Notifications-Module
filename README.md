# Order and Notifications Module

Welcome to the API Collection documentation. This collection provides a set of APIs to interact with different aspects of the system, including customer management, product information, order processing, shipping, notifications, and statistics.

## Customer API

### GET - View all customers
```
http://localhost:8080/api/customer/view
```

### POST - Create Account
```
http://localhost:8080/api/customer/create
```
This is a POST request, submitting data to an API via the request body. The request submits JSON data, and the data is reflected in the response.

**Body**
```json
{
    "name": "Name Example",
    "phonenumber": "0150000085",
    "region": 1,
    "address": "1 Example, State, Country",
    "username": "username",
    "password": "password",
    "balance": 1000000
}
```

### POST - Login
```
http://localhost:8080/api/customer/login
```
This is a POST request for customer login. The request submits JSON data, and a successful request typically returns a 200 OK or 201 Created response code.

**Body**
```json
{
    "username": "username",
    "password": "password"
}
```

## Product API

### GET - View all products
```
http://localhost:8080/api/products/all
```

### GET - View a single product by ID
```
http://localhost:8080/api/products/{productId}
```

## Order API

### GET - View all orders
```
http://localhost:8080/api/order/view/all
```

### POST - Make simple order
```
http://localhost:8080/api/order/make/simple
```
This is a POST request for making a simple order. The request submits JSON data, and a successful request typically returns a 200 OK or 201 Created response code.

**Request Headers**
- username
- password

**Body**
```json
{
    "Banana": 1,
    "Watermelon": 2,
    "Apple": 1
}
```

### POST - Cancel simple order
```
http://localhost:8080/api/order/cancel/simple/{username}/{orderId}
```
This is a POST request for canceling a simple order. The request submits JSON data, and a successful request typically returns a 200 OK or 201 Created response code.

### POST - Make compound order
```
http://localhost:8080/api/order/make/compound
```
This is a POST request for making a compound order. The request submits JSON data, and a successful request typically returns a 200 OK or 201 Created response code.

**Request Headers**
- username
- password

**Body**
```json
{
    "username": {
        "Banana": 1
    }
}
```

### POST - Cancel compound order
```
http://localhost:8080/api/order/cancel/compound/{username}/{orderId}
```
This is a POST request for canceling a compound order. The request submits JSON data, and a successful request typically returns a 200 OK or 201 Created response code.

## Shipping API

### POST - Confirm simple shipping
```
http://localhost:8080/api/shipping/confirmSimple/{orderId}
```
This is a POST request for confirming simple shipping. The request submits JSON data, and a successful request typically returns a 200 OK or 201 Created response code.

### POST - Cancel simple shipping
```
http://localhost:8080/api/shipping/cancel/simple/{username}/{shippingId}
```
This is a POST request for canceling simple shipping. The request submits JSON data, and a successful request typically returns a 200 OK or 201 Created response code.

### POST - Confirm compound shipping
```
http://localhost:8080/api/shipping/confirmCompound/{orderId}
```
This is a POST request for confirming compound shipping. The request submits JSON data, and a successful request typically returns a 200 OK or 201 Created response code.

### POST - Cancel compound shipping
```
http://localhost:8080/api/shipping/cancel/compound/{username}/{shippingId}
```
This is a POST request for canceling compound shipping. The request submits JSON data, and a successful request typically returns a 200 OK or 201 Created response code.

## Notification API

### GET - Send Notification
```
http://localhost:8080/api/notification/send
```

### GET - View notification
```
http://localhost:8080/api/notification/view
```

## Statistics API

### GET - Phone statistics
```
http://localhost:8080/api/stats/phone
```

### GET - Template statistics
```
http://localhost:8080/api/stats/template
```

Feel free to explore and integrate these APIs into your system. If you have any questions or issues, please refer to the documentation or contact our support team.
