package dev.micfro.javaapachehttpclientweatherapi;


import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class ApacheweatherAPI {

    public static void main(String[] args) {

        // GET request - READ
//        String zip = "04177";
//        fetchWeatherForecast(zip);

        // POST request - CREATE
        String postUrl = "https://jsonplaceholder.typicode.com/posts";
        String postJsonBody = "{" +
                "\"title\": \"Best Title\",\n" +
                "\"body\": \"Best Body\",\n" +
                "\"userId\": 1\n" +
                "}";
        sendPostRequest(postUrl, postJsonBody);

        // PUT request - UPDATE
        String putUrl = "https://jsonplaceholder.typicode.com/posts/1";
        String putJsonBody = "{\n" +
                "\"coord\": {\n" +
                "\"lon\": 12.3341,\n" +
                "\"lat\": 51.3254\n" +
                "},\n" +
                "\"weather\": [\n" +
                "{\n" +
                "\"id\": 803,\n" +
                "\"main\": \"Clouds\",\n" +
                "\"description\": \"broken clouds\",\n" +
                "\"icon\": \"04d\"\n" +
                "}\n" +
                "],\n" +
                "\"base\": \"stations\",\n" +
                "\"main\": {\n" +
                "\"temp\": 7.61,\n" +
                "\"feels_like\": 5.28,\n" +
                "\"temp_min\": 6.46,\n" +
                "\"temp_max\": 9.18,\n" +
                "\"pressure\": 1008,\n" +
                "\"humidity\": 81\n" +
                "},\n" +
                "\"visibility\": 10000,\n" +
                "\"wind\": {\n" +
                "\"speed\": 3.6,\n" +
                "\"deg\": 310\n" +
                "},\n" +
                "\"rain\": {\n" +
                "\"1h\": 0.1\n" +
                "},\n" +
                "\"clouds\": {\n" +
                "\"all\": 75\n" +
                "},\n" +
                "\"dt\": 1713342590,\n" +
                "\"sys\": {\n" +
                "\"type\": 2,\n" +
                "\"id\": 2000347,\n" +
                "\"country\": \"DE\",\n" +
                "\"sunrise\": 1713327011,\n" +
                "\"sunset\": 1713377382\n" +
                "},\n" +
                "\"timezone\": 7200,\n" +
                "\"id\": 0,\n" +
                "\"name\": \"Leipzigski Best City\",\n" +
                "\"cod\": 200\n" +
                "}";
        sendPutRequest(putUrl, putJsonBody);


        // DELETE request - DELETE
        String deleteUrl = "https://jsonplaceholder.typicode.com/posts/1";
        sendDeleteRequest(deleteUrl);

    }



    private static void fetchWeatherForecast(String theZip) {


        // Define the URL of the weather API endpoint
        String baseUrl = "http://api.openweathermap.org/data/2.5/weather";
        String apiKey = Config.getApiKey();
        String units = "metric";
        String zip = theZip;
        String Country = "de";
        String language = "de";
        String url = baseUrl + "?zip=" + zip + "," + Country + "&units=" + units + "&lang=" + language + "&appid=" + apiKey;

        CloseableHttpClient theHttpClient = null;

        try {
            theHttpClient = HttpClients.createDefault();
            HttpGet requestGET = new HttpGet(url);
            // Send the request
            String responseBody = theHttpClient.execute(requestGET, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));

            // Parse JSON response
            JSONObject json = new JSONObject(responseBody);
            System.out.println("Weather data for: " + json.getString("name"));
            System.out.println("Temperature: " + json.getJSONObject("main").getDouble("temp") + "°C");
            System.out.println("Feels Like: " + json.getJSONObject("main").getDouble("feels_like") + "°C");
            System.out.println("Humidity: " + json.getJSONObject("main").getInt("humidity") + "%");
            System.out.println("Wind Speed: " + json.getJSONObject("wind").getDouble("speed") + " m/s");
            System.out.println("Conditions: " + json.getJSONArray("weather").getJSONObject(0).getString("description"));

        } catch (IOException e) {
            System.err.println("Error occurred while retrieving weather data: " + e.getMessage());
        }
    }


    private static void sendPostRequest(String url, String jsonBody) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost requestPost = new HttpPost(url);
            requestPost.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
            // Send the POST request
            httpClient.execute(requestPost, httpResponse -> {
                System.out.println("Response status of POST: " + httpResponse.getStatusLine().getStatusCode());
                return EntityUtils.toString(httpResponse.getEntity());
            });
        } catch (IOException e) {
            System.err.println("Error occurred while sending POST request: " + e.getMessage());
        }
    }


    private static void sendPutRequest(String url, String jsonBody) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut requestPut = new HttpPut(url);
            requestPut.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

            // Send the PUT request
            httpClient.execute(requestPut, httpResponse -> {
                System.out.println("Response status of PUT: " + httpResponse.getStatusLine().getStatusCode());
                return EntityUtils.toString(httpResponse.getEntity());
            });

        } catch (IOException e) {
            System.err.println("Error occurred while sending PUT request: " + e.getMessage());
        }
    }

    private static void sendDeleteRequest(String url) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpDelete requestDelete = new HttpDelete(url);
            // Send the DELETE request
            httpClient.execute(requestDelete, httpResponse -> {
                System.out.println("Response status of DELETE: " + httpResponse.getStatusLine().getStatusCode());
                return EntityUtils.toString(httpResponse.getEntity());
            });
        } catch (IOException e) {
            System.err.println("Error occurred while sending DELETE request: " + e.getMessage());
        }
    }
}


