# HotelReservation Project
![Alten Canadá](https://i.ibb.co/LQqTvS3/alten-Canada.png)

## Proposed Project
Develop an API to book a hotel room.
We assume the hotel has only one room available.To give a chance to everyone to book the room, the stay can’t be longer than 3 days and
can’t be reserved more than 30 days in advance. All reservations start at least the next day of booking. A “DAY’ in the hotel room starts from 00:00 to 23:59:59.
The API is insecure.

## Solution implemented
- Stack used for implementation
    - Java 11
    - Spring Boot with Maven
    - Postgres database
    
![Database](https://i.ibb.co/V9Whh8x/Captura-de-Tela-2021-05-06-a-s-14-11-21.png)

## Hotel System Design Architecture

![System Design](https://i.ibb.co/Jc0zQKr/Captura-de-Tela-2021-05-06-a-s-00-14-18.png)

## Concerns about the Project

1. API will be maintained by the hotel’s IT department.
   -  To facilitate the handling of the code by the Hotel's IT department, a CI / CD pipeline must be created, streamlining and automating the upload of new features and bug fixes.
   
2. The quality of service must be 99.99 to 100% => no downtime.
   - To maintain the quality of the proposed service we will use Azure's premium tier.
  The proposed infrastructure is simple, with a WebApp and a database replicated in two regions to improve uptime and at the edge of the application a load balance to manage requests.
  Ref -> https://docs.microsoft.com/en-us/azure/architecture/reference-architectures/app-service-web-app/multi-region

   ![Database](https://i.ibb.co/ryRVpsq/Captura-de-Tela-2021-05-07-a-s-11-21-20.png)
                 
                Calculating SLA  
                Availability Region 1 = Web app * DataBase SQL 
                = 99.99% * 99,99% 
                = 0.9999 * 0.9999 
                = 0.9998 
                = 99.98% 
                Availability Region 2 = Replica
                = 99.98%
                Availability in both region
                1-(1-Primary is up)) * (1-Secondary is up))
                = 1 - (1 - 99.98%) * (1 - 99.98%)
                = 1 - 0.02% * 0.02% 
                = 1 - 0.000004% 
                = %99.9996
                Availability in both region with API Management
                = API M. * Both region 
                = 99.9999% * 99.9996%
                = 0.9999
                = 99.99%
           

    
    ## Tous les shortcuts pour gagner du temps sont autorisés dans la mesure où c’est documenté. 
    ### @Annotations used in the code
    - @Getter and @Setter -> Code automation of gets and sets
    
# REST API

The REST API to the HotelReservation app is described below.

## Get list of Bookings

### Request

`GET /bookings`

    curl -i -H 'Accept: application/json' http://localhost:8080/bookings

### Response

    HTTP/1.1 200 OK
    Date: Thu, 05 April 2021 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {
        "id": "11204885-373e-4a02-b60f-83569ad9e5c4",
        "guest": {
            "id": "30992db7-38df-4ccf-b1e6-50f4f8ecff74",
            "email": "carlos@gmail.com",
        },
        "checkIn": "2021-05-11",
        "checkOut": "2021-05-13",
        "stayDays": 3
    }
    
## Get a Bookings by id

### Request

`GET /bookings/{id}`

    curl -i -H 'Accept: application/json' http://localhost:8080/bookings/11204885-373e-4a02-b60f-83569ad9e5c4

### Response

    HTTP/1.1 200 OK
    Date: Thu, 05 April 2021 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {
        "id": "11204885-373e-4a02-b60f-83569ad9e5c4",
        "guest": {
            "id": "30992db7-38df-4ccf-b1e6-50f4f8ecff74",
            "email": "carlos@gmail.com",
        },
        "checkIn": "2021-05-11",
        "checkOut": "2021-05-13",
        "stayDays": 3
    }

## Create a new Booking

### Request

`POST /bookings`

    curl -i -H 'Accept: application/json' http://localhost:8080/bookings

### Response

    HTTP/1.1 201 Created
    Date: Thu, 05 April 2021 12:36:30 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json
    Location: http://localhost:8080/booking/11204885-373e-4a02-b60f-83569ad9e5c4

    {
        "guestId":"{{guestId}}",
        "checkIn":"2021-05-11",
        "stayDays":3
    }
    
## Modify a Booking by id

### Request

`PATCH /bookings/{id}`

    curl -i -H 'Accept: application/json' http://localhost:8080/bookings/11204885-373e-4a02-b60f-83569ad9e5c4

### Response

    HTTP/1.1 201 Created
    Date: Thu, 05 April 2021 12:36:30 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json
    Location: http://localhost:8080/booking/11204885-373e-4a02-b60f-83569ad9e5c4

    {
        "checkIn" : "2021-05-11",
        "stayDays" : 2
    }

## Delete a Booking by id

### Request

`DELETE /bookings/11204885-373e-4a02-b60f-83569ad9e5c4`

    curl -i -H 'Accept: application/json' http://localhost:8080/bookings/11204885-373e-4a02-b60f-83569ad9e5c4

### Response

    HTTP/1.1 204 No Content
    Date: Thu, 05 April 2021 12:36:30 GMT
    Status: 204 No Content
    Connection: close
    Content-Type: application/json



## Create a new Guest

### Request

`POST /guests/`

    curl -i -H 'Accept: application/json' http://localhost:8080/guests

### Response

    HTTP/1.1 201 Created
    Date: Thu, 05 April 2021 12:36:30 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json

    {
        "email": "alten@canada.com",
        "password": "test"
    }

