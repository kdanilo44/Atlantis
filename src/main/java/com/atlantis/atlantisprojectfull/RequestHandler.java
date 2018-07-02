/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.atlantisprojectfull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author user
 */
@Stateless
@Path("request")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class RequestHandler {

    @GET
    @Path("getlistdevice/{id}")
    public String listDevice(@PathParam("id") String id) throws IOException {
        String jsonArticle = new String();
        try {
            URL urlObj = new URL("http://10.144.50.18:8000/User/Devices?id=" + id);
            HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())
            );
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                String line;
                while ((line = in.readLine()) != null) {
                    jsonArticle += line;
                }
            } else {
                System.out.println("JSON FAILED");
            }
            in.close();
            urlConnection.disconnect();
            return jsonArticle;
        } catch (MalformedURLException e) {
            System.out.println("Fail URL");
        } catch (Exception e) {
            System.out.println("JSON Failed");
        }
        return jsonArticle;
    }

    @GET
    @Path("getlistmetric/{qtt}/{id}")
    public String listRawMetric(@PathParam("qtt") String qtt, @PathParam("id") String id) throws IOException {
        String jsonArticle = new String();
        try {
            URL urlObj = new URL("http://10.144.50.18:8000/Device/Metrics?qtt=" + qtt + "&idDevice=" + id);
            HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())
            );
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                String line;
                while ((line = in.readLine()) != null) {
                    jsonArticle += line;
                }
            } else {
                System.out.println("JSON FAILED");
            }
            in.close();
            urlConnection.disconnect();
            return jsonArticle;
        } catch (MalformedURLException e) {
            System.out.println("fail URL");
        } catch (Exception e) {
            System.out.println("JSON FAIL");
        }
        return jsonArticle;
    }

    @POST
    @Path("command")
    @Consumes(MediaType.APPLICATION_JSON)

    public String sendCommand(String content) {

        /*     JsonObject json = Json.createObjectBuilder()
                .add("deviceType", "ledDevice")
                .build();*/
        HttpClient httpClient = new DefaultHttpClient();
        String response = "";
        try {
            HttpPost request = new HttpPost("http://10.144.50.18:8000/Command/Send");
            StringEntity params = new StringEntity(content);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            response = httpClient.execute(request).getStatusLine().toString();
// handle response here...
        } catch (Exception ex) {
            response = ex.toString();
        } finally {
            httpClient.getConnectionManager().closeExpiredConnections();
        }
        return response;
    }

    @POST
    @Path("newaccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public String sendNewAccount(String content) {

        /*   JsonObject json = Json.createObjectBuilder()
                .add("firstName", "Gurvan")
                .add("lastName", "Campion")
                .add("phone", "0102030405")
                .add("email", "gurvan.campion@viacesi.fr")
                .add("password", "toto12345Exia")
                .build();*/
        HttpClient httpClient = new DefaultHttpClient();
        String response = "";
        try {
            HttpPost request = new HttpPost("http://10.144.50.18:8000/User/Create");
            StringEntity params = new StringEntity(content);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            response = httpClient.execute(request).getStatusLine().toString();
// handle response here...
        } catch (Exception ex) {
            response = ex.toString();
            // handle exception here
        } finally {
            httpClient.getConnectionManager().closeExpiredConnections();
        }

        return response;

    }

}
