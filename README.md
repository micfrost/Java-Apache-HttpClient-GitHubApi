# Java Apache HttpClient Weather API

This project demonstrates the use of Apache HttpClient to perform HTTP operations (`GET`, `POST`, `PUT`, and `DELETE`). It interacts with a fake online REST API, JSONPlaceholder, to simulate creating, retrieving, updating, and deleting posts. Additionally, it includes an example of fetching weather data from the OpenWeatherMap API.


## Features

- Retrieve weather data for a specific ZIP code.
- Parse JSON response to extract and display weather details.
- Configurable API key through a properties file.

## Prerequisites

- Java 11 or higher
- Maven

## Setup

1. Clone this repository to your local machine.
2. Obtain an API key from [OpenWeatherMap](https://openweathermap.org/api).
3. Create a `config.properties` file in `src/main/resources/` with your API key:

### Properties
api_key=YOUR_API_KEY_HERE
Replace YOUR_API_KEY_HERE with the actual API key you obtained from OpenWeatherMap.

### Libraries Used
Apache HttpClient 4.5.14
JSON.org
Ensure you have the necessary dependencies in your pom.xml file to include these libraries.

## Author
Created by Michal Frost


![Screenshot from 2024-04-17 11-27-15.png](src%2Fmain%2Fresources%2Fimages%2FScreenshot%20from%202024-04-17%2011-27-15.png)