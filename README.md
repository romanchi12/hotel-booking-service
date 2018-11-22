## Test task. Create REST application Hotel booking

### With capabilities (each represented by separate endpoint):

1. View list of available rooms (room have a number, category, price, additional options like breakfast, cleaning with additional cost) for specified dates.

2. View rooms filtered by category.

3. Create user.

4. User can book the room for specified days.

5. User can view his booking.

6. User can get the total price of the booking (room for dates period + cost of additional options).

7. View all bookings for the hotel.
 
Tech stack: Java 8, Spring Boot, Spring MVC, Spring Data, Hibernate, H2, maven.

Authentication and authorization are not included in the task.

### Requirements:
1. Source code must be stored on GitHub with access to the repository.

2. The repository must contain file README.MD with instructions for launching the application and any other necessary documentation for the application (in English).

3. Running the application should not require a standalone application server (application should run on embedded tomcat server) or installation of any software except Java and maven. 

4. The project should contain SQL-script for creating database tables and filling them with data necessary to test application.

5. The project should be covered with unit tests.

6. Availability of UML class diagram is a plus.

 ### Evaluation criteria:
* functional correctness according to the technical requirements,

* readability, maintainability, and compliance of the code with OOP and SOLID principles,

* documentation for the application and  the code,

* any non-standard technical solutions,

* any additional features not specified in the technical requirements, but making the application more functional or convenient,

* task execution time.

## Project Configuration

The project contains SQL-script to filling the database tables with the test data needed to test the application. The file is located: ```\src\main\resources\data.sql```.

**Attention: the database tables are created automatically. Used H2 in-memory database**

**The UML class diagram**

![package domain](https://user-images.githubusercontent.com/22998193/48909518-28a12080-ee76-11e8-9fea-3f599de588da.png)


**Data model diagram**

![data-model-1](https://user-images.githubusercontent.com/22998193/48913239-7d499900-ee80-11e8-93d3-7ffb0ea59c32.png)

## Hotel Booking API Documentation

**Public API Methods**

**Room**
+ Endpoint: /rooms
+ Method: GET
+ Parameters:
    + (long  NULLABLE) category identification
    + (String  NULLABLE) from - start date of requested period
    + (String  NULLABLE) until - end date of the requested period
+ Example OK 200: <http://localhost:8080/rooms?categoryId=2>
```JSON
//JSON 
[
    {
        "id": 7,
        "categoryId": 2,
        "categoryDescription": "Double room",
        "number": 201,
        "optionList": [
            {
                "roomId": 7,
                "optionTypeId": 2,
                "optionTypeDescription": "Cleaning",
                "price": 100
            }
        ],
        "bookingList": [],
        "currentPrice": 200
    },
    {
        "id": 8,
        "categoryId": 2,
        "categoryDescription": "Double room",
        "number": 202,
        "optionList": [
            {
                "roomId": 8,
                "optionTypeId": 1,
                "optionTypeDescription": "Breakfast",
                "price": 200
            }
        ],
        "bookingList": [],
        "currentPrice": 200
    },
    {
        "id": 9,
        "categoryId": 2,
        "categoryDescription": "Double room",
        "number": 203,
        "optionList": [
            {
                "roomId": 9,
                "optionTypeId": 2,
                "optionTypeDescription": "Cleaning",
                "price": 200
            }
        ],
        "bookingList": [],
        "currentPrice": 300
    },
    {
        "id": 10,
        "categoryId": 2,
        "categoryDescription": "Double room",
        "number": 204,
        "optionList": [],
        "bookingList": [],
        "currentPrice": 500
    }
]
```
+ Example NOT_FOUND 404: <http://localhost:8080/rooms?categoryId=4&from=2018-11-10&until=2018-11-11>
```JSON
//JSON 
{
    "message": "Invalid categoryId 4"
}
```
+ Endpoint: /rooms/{roomId}
+ Method: GET
+ Parameters:
    + (long) roomId Room identification
+ Example 200 OK: <http://localhost:8080/rooms/1>
```JSON
//JSON 
{
    "id": 1,
    "categoryId": 1,
    "categoryDescription": "Single room",
    "number": 101,
    "optionList": [
        {
            "roomId": 1,
            "optionTypeId": 1,
            "optionTypeDescription": "Breakfast",
            "price": 100
        }
    ],
    "bookingList": [],
    "currentPrice": 200
}
```
+ Example 404 NotFound: <http://localhost:8080/rooms/33>
```JSON
//JSON 
{
    "message": "Not Found. Invalid roomId 33"
}
```
**Booking**

+ Endpoint: /bookings/{bookingId}
+ Method: GET
+ Parameters:
    + (long) bookingId - Booking identification
+ Example 200 OK : <http://localhost:8080/bookings/1>
```JSON
{
    "id": 1,
    "customerId": 1,
    "customerName": "Mykola",
    "roomId": 12,
    "roomCategoryId": 3,
    "roomCategoryDescription": "Quadriple room",
    "roomNumber": 206,
    "fromDate": "2019-01-01",
    "untilDate": "2019-05-01",
    "optionList": [
        {
            "optionTypeId": 1,
            "optionTypeDescription": "Breakfast",
            "price": 300
        }
    ],
    "roomPrice": 500,
    "summaryPrice": 800
}
```
+ Example 404 NotFound : <http://localhost:8080/bookings/5>
```JSON
//JSON
{
    "message": "Invalid bookingId 5"
}
```
+ Endpoint: /bookings/{bookingId}/price
+ Method: GET
+ Parameters: 
    + (long) bookingId booking identification
+ Example 200 OK : <http://localhost:8080/bookings/1/price>
```JSON
//JSON 
{
    "id": 1,
    "roomPrice": 500,
    "optionList": [
        {
            "optionTypeId": 1,
            "optionTypeDescription": "Breakfast",
            "price": 300
        }
    ],
    "summaryPrice": 800
}
```
+ Endpoint: /bookings
+ Method: GET
+ Parameters:
+ Example 200 OK : <http://localhost:8080/bookings>
```JSON
//JSON 
[
    {
        "id": 1,
        "customerId": 1,
        "customerName": "Mykola",
        "roomId": 12,
        "roomCategoryId": 3,
        "roomCategoryDescription": "Quadriple room",
        "roomNumber": 206,
        "fromDate": "2019-01-01",
        "untilDate": "2019-05-01",
        "optionList": [
            {
                "optionTypeId": 1,
                "optionTypeDescription": "Breakfast",
                "price": 300
            }
        ],
        "roomPrice": 500,
        "summaryPrice": 800
    },
    {
        "id": 2,
        "customerId": 2,
        "customerName": "Roman",
        "roomId": 12,
        "roomCategoryId": 3,
        "roomCategoryDescription": "Quadriple room",
        "roomNumber": 206,
        "fromDate": "2019-08-01",
        "untilDate": "2019-10-01",
        "optionList": [],
        "roomPrice": 500,
        "summaryPrice": 500
    }
]
```
+ Endpoint: /bookings
+ Method: POST
+ Parameters:
    + (long) customerId customer identification
    + (long) roomId room identification
    + (String) fromDate start Date of period
    + (String) untilDate end Date of period
    + (Array) optionList additional options contains (long) optionTypeId 
+ Example URL : <http://localhost:8080/bookings>
+ 200 OK Request : 
```JSON
//JSON 
{
	"customerId" : "2",
	"roomId" : "12",
	"fromDate" : "2019-06-10",
	"untilDate" : "2019-06-19",
	"optionList" : [
		{
			"optionTypeId":"1"
		}
	]
}
```
+ 200 OK Response :
```JSON
//JSON 
{
    "id": 3,
    "customerId": 2,
    "customerName": "Roman",
    "roomId": 12,
    "roomCategoryId": 3,
    "roomCategoryDescription": "Quadriple room",
    "roomNumber": 206,
    "fromDate": "2019-06-10",
    "untilDate": "2019-06-19",
    "optionList": [
        {
            "optionTypeId": 1,
            "optionTypeDescription": "Breakfast",
            "price": 300
        }
    ],
    "roomPrice": 500,
    "summaryPrice": 7200
}
```
+ 400 BadRequest Request :
```JSON
//JSON 
{
	"customerId" : "1",
	"roomId" : "12",
	"fromDate" : "2019-06-10",
	"untilDate" : "2019-06-19",
	"optionList" : [
		{
			"optionTypeId":"1"
		}
	]
}
```
+ 400 BadRequest Response :
```JSON
//JSON 
{
    "message": "Not available room for date interval 2019-06-10 2019-06-19"
}
```

**Customer**

+ Endpoint: /customer 
+ Method: POST
+ Parameters:
  + (String) name name of customer
+ Example URL : <http://localhost:8080/customers>
+ 200 OK Request :
```JSON
//JSON 
{
	"name":"Petro"
}
```
+ 200 OK Response :
```JSON
//JSON 
{
    "id": 3,
    "name": "Petro",
    "bookingList": []
}
```
+ Endpoint: /customer/{customerId}
+ Method: GET
+ Parameters:
  + (long) customerId customer identification
+ Example URL : <http://localhost:8080/customers/1>
+ 200 OK Response :
```JSON
//JSON 
{
    "id": 1,
    "name": "Mykola",
    "bookingList": [
        {
            "id": 1,
            "customerId": 1,
            "customerName": "Mykola",
            "roomId": 12,
            "roomCategoryId": 3,
            "roomCategoryDescription": "Quadriple room",
            "roomNumber": 206,
            "fromDate": "2019-01-01",
            "untilDate": "2019-05-01",
            "optionList": [
                {
                    "optionTypeId": 1,
                    "optionTypeDescription": "Breakfast",
                    "price": 300
                }
            ],
            "roomPrice": 500,
            "summaryPrice": 800
        }
    ]
}
```
+ Endpoint: /customer/{customerId}/bookings
+ Method: GET
+ Parameters:
  + (long) customerId customer identification
+ Example URL : <http://localhost:8080/customers/1/bookings>
+ 200 OK Response :
```JSON
//JSON 
[
    {
        "id": 1,
        "customerId": 1,
        "customerName": "Mykola",
        "roomId": 12,
        "roomCategoryId": 3,
        "roomCategoryDescription": "Quadriple room",
        "roomNumber": 206,
        "fromDate": "2019-01-01",
        "untilDate": "2019-05-01",
        "optionList": [
            {
                "optionTypeId": 1,
                "optionTypeDescription": "Breakfast",
                "price": 300
            }
        ],
        "roomPrice": 500,
        "summaryPrice": 800
    }
]
```

**Category**

+ Endpoint: /categories
+ Method: GET
+ Example URL : <http://localhost:8080/categories>
+ 200 OK Response :
```JSON
//JSON 
[
    {
        "id": 1,
        "description": "Single room"
    },
    {
        "id": 2,
        "description": "Double room"
    },
    {
        "id": 3,
        "description": "Quadriple room"
    }
]
```
+ Endpoint: /categories/{categoryId}
+ Method: GET
+ Parameters:
  + (long) categoryId category identification
+ Example URL : <http://localhost:8080/categories/1>
+ 200 OK Response :
```JSON
//JSON 
{
    "id": 1,
    "description": "Single room"
}
```

**Option**

+ Endpoint: /options
+ Method: GET
+ Example URL : <http://localhost:8080/options>
+ 200 OK Response :
```JSON
//JSON 
[
    {
        "id": 1,
        "description": "Breakfast"
    },
    {
        "id": 2,
        "description": "Cleaning"
    }
]
```
+ Endpoint: /options/{optionId}
+ Method: GET
+ Parameters:
  + (long) optionId option identification
+ Example URL : <http://localhost:8080/options/1>
+ 200 OK Response :
```JSON
//JSON 
{
    "id": 1,
    "description": "Breakfast"
}
```