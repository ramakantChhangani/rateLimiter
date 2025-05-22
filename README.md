# rateLimiter
This is a personal project on creating a rate limiter using Redis, Springboot. HTML is used to show a button on index.html. For an API Key, if the limit exceeds rate-limit threshold MAX_REQUESTS then the request is throttled with a message.

# Tech Stack
1. Java Springboot - for backend
2. Redis - In memory database
3. HTML - for basic frontend


# Steps
-  Install Redis
-  Start redis server using redis-server command
-  Run the application
-  Visit http://localhost:8081/ or http://localhost:8081/index.html
-  You can change server.port in application.properties


# What does it do?
- It shows the algorithm chosen (currently from application.properties)

- Token Bucket Algorithm - 
   The current bucket size
   If it shows request is throttled, wait for 10 secs. It will reset back to bucket size according to Token Bucket Algorithm
