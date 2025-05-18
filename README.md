Project 1: Online Store Web Application

Overview:
This is a web-based online store application built with Java 8 and Spring Boot 2.3.1.RELEASE. The front-end uses Thymeleaf, HTML, CSS, and JavaScript. It allows users to log in with their mobile number and a CAPTCHA, receive a login SMS (default code: 12345), and add products to their cart. Products are stored in Redis until checkout and then persisted in the database.

Technologies:

Java 8

Spring Boot 2.3.1.RELEASE

Maven

Thymeleaf

HTML/CSS/JS

Redis (for temporary cart data)

OkHttp Client (for communication with server)

Kavenegar (SMS service)

Ports: 7312

Server Communication:

Backend server endpoint is defined in application.yml:
