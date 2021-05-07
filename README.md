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
   -  To facilitate the handling of the code by the Hotel's IT department, a CI / CD pipeline must be created, streamlining and automating the upload of new features
   and bug fixes.
   
2. The quality of service must be 99.99 to 100% => no downtime.
   - to maintain the quality of the proposed service we will use Azure's premium tier.
  The resulting infrastructure is for an application with a medium scale request quantity, so the SLA calculation will not take into account the service scale 
  and a load balance
     ![Database](https://i.ibb.co/ryRVpsq/Captura-de-Tela-2021-05-07-a-s-11-21-20.png)
   - Calculating SLA <br /> 
    Availability Region 1 = Web app * DataBase SQL <br />
    = 99.99% * 99,99% <br />
    = 0.9999 * 0.9999 <br />
    = 0.9998 <br />
    = 99.98 % <br />
    Availability Region 2 = Replica <br />
    = 99.98 % <br />
    Availability in both region <br />
    1-(1-Primary is up)) * (1-Secondary is up)) <br />
    = 1 - (1 - %99.98) * (1 - %99.98)<br />
    = 1 - %0.02 * %0.02 <br />
    = 1 - %0.000004 <br />
    = %99.9996 <br />
    Availability in both region with API Management <br />
    = API M. * Both region <br />
    = 99.9999% * 99.9996% <br />
    = 0.9999 <br />
    = 99.99% <br />
    
    ## Tous les shortcuts pour gagner du temps sont autorisés dans la mesure où c’est documenté. 
    ### @Annotations used in the code
    - @Getter and @Setter -> Code automation of gets and sets
    
