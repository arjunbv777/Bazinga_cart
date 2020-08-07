# Bazinga_cart
Bazinga cart is a E commerce site for online shopping  

# Spring Boot
Spring JPA, Security, Rest, H2

# Frontend
React

# TO RUN
Just run the spring boot app the js content is auto reloaded form static folder location

In case if you caught with any error in front end then follow below steps else just jump to #Backend
# Frontend build(use letest node)
1. cd src/main/webapp/cart
2. npm install 
3. npm run build
4. Copy conent from build folder to /resources/static
    
    EX. copy /build/* /resources/static
    
5. Then copy html conent from 
    
   build/index.html to webapp/web-inf/jsp/index.jsp (only replace html content)

# Backend
6. Start spring boot app using gradle command
7. H2 db will be initilized after starting
8. go to http://localhost:8090/login
9. login using 
   1. u_name: user1 pass : 1234 
   2. u_name: user2 pass : pass 
    
   You can use both the users, Also use logout button and login again all the cart info is stored in h2 is processed.
   once you restart the apring boot app all saved cart and product details are reinitilized.
    
10. also you can view the h2 db content using below url

    http://localhost:8090/h2-console
    
11.start using.....


