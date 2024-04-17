package dev.micfro.javaapachehttpclientweatherapi;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ApacheGithubAPI {
    public static void main(String[] args) {
        trendingRepositoriesGET("Java");
    }

    public static void trendingRepositoriesGET(String language) {

        // Define the URL
        String url = "https://api.github.com/search/repositories?q=language:" + language + "&sort=stars&order=desc";

        // CloseableHttpClient
        CloseableHttpClient theHttpClient = HttpClients.createDefault();

        try {
            // Define the GET request
            HttpGet requestGET = new HttpGet(url);

            // Send the GET request
            String theResponseJson = theHttpClient.execute(requestGET, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));

            // analyze the JSON response
            parseAndDisplay(theResponseJson);



        } catch (IOException e) {
            System.err.println("Error occurred while retrieving data: " + e.getMessage());
        }

}

    private static void parseAndDisplay(String jsonResponse) {
        JSONObject jsonObjectobj = new JSONObject(jsonResponse);
        JSONArray repos = jsonObjectobj.getJSONArray("items");

        for (int i = 0; i < repos.length(); i++) {
            JSONObject repo = repos.getJSONObject(i);

            System.out.println("Repository Name: " + repo.getString("name"));
            System.out.println("Description: " + repo.optString("description", "No description provided."));
            System.out.println("Stars: " + repo.getInt("stargazers_count"));
            System.out.println("Forks: " + repo.getInt("forks_count"));
            System.out.println("Watchers: " + repo.getInt("watchers_count"));
            System.out.println("URL: " + repo.getString("html_url"));
            System.out.println("-----------------------------------");
        }
    }
}



