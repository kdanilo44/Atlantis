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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * REST Web Service
 *
 * @author user
 */
@Stateless
@Path("resources")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CalcTempMetricResource {

    @Inject
    CalcTempMetricManager calcTempMetricManager;

    /*
    @GET
    @Path("getM/{id}")
    public JsonObject findById(@PathParam("id") Long id) {
        CalcTempMetric calcTempMetric = this.calcTempMetricManager.findById(id);
        return calcTempMetric.toJson();
    }*/

    @GET
    @Path("getMD/{id}")
    public JsonObject findByIdDeviceDay(@PathParam("id") String id_device) {
        List<CalcTempMetric> calcTempMetricList = this.calcTempMetricManager.findByNameDeviceDay(id_device);
        JsonObject jsonholder = toJsonCalcDay(calcTempMetricList);
        return jsonholder;
    }

    @GET
    @Path("getMW/{id}")
    public JsonObject findByIdDeviceWeekly(@PathParam("id") String id_device) {
        List<CalcTempMetric> calcTempMetricList = this.calcTempMetricManager.findByNameDeviceWeek(id_device);
        JsonObject jsonholder = toJsonCalcWeek(calcTempMetricList);
        return jsonholder;
    }

    @GET
    @Path("getMM/{id}")
    public JsonObject findByIdDeviceMonth(@PathParam("id") String id_device) {
        List<CalcTempMetric> calcTempMetricList = this.calcTempMetricManager.findByNameDeviceMonth(id_device);
        JsonObject jsonholder = toJsonCalcMonth(calcTempMetricList);
        return jsonholder;
    }

    @POST
    @Path("setM")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(String content) {
        StringReader reader = new StringReader(content);
        CalcTempMetric calcTempMetric = new CalcTempMetric();

        try (JsonReader jreader = Json.createReader(reader)) {
            JsonObject saveInfo = jreader.readObject();
            calcTempMetric.setId_device(saveInfo.getString("id_device"));
            calcTempMetric.setDate_temp(new Date());
            calcTempMetric.setMin_temp((float) saveInfo.getJsonNumber("min_temp").doubleValue());
            calcTempMetric.setMax_temp((float) saveInfo.getJsonNumber("max_temp").doubleValue());
            calcTempMetric.setAverage_temp((float) saveInfo.getJsonNumber("average_temp").doubleValue());
            calcTempMetric.setType_calc(saveInfo.getJsonNumber("type_calc").intValue());
        }

        this.calcTempMetricManager.create(calcTempMetric);

        return Response.ok().build();
    }

    public JsonObject toJsonCalcDay(List<CalcTempMetric> calcTempMetricList) {
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("yyyy-MM-dd");

        JsonObject jo = Json.createObjectBuilder()
                .add("days", Json.createArrayBuilder()
                        .add(formater.format(calcTempMetricList.get(0).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(1).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(2).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(3).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(4).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(5).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(6).getDate_temp())))
                .add("min", Json.createArrayBuilder()
                        .add(calcTempMetricList.get(0).getMin_temp())
                        .add(calcTempMetricList.get(1).getMin_temp())
                        .add(calcTempMetricList.get(2).getMin_temp())
                        .add(calcTempMetricList.get(3).getMin_temp())
                        .add(calcTempMetricList.get(4).getMin_temp())
                        .add(calcTempMetricList.get(5).getMin_temp())
                        .add(calcTempMetricList.get(6).getMin_temp()))
                .add("max", Json.createArrayBuilder()
                        .add(calcTempMetricList.get(0).getMax_temp())
                        .add(calcTempMetricList.get(1).getMax_temp())
                        .add(calcTempMetricList.get(2).getMax_temp())
                        .add(calcTempMetricList.get(3).getMax_temp())
                        .add(calcTempMetricList.get(4).getMax_temp())
                        .add(calcTempMetricList.get(5).getMax_temp())
                        .add(calcTempMetricList.get(6).getMax_temp()))
                .add("average", Json.createArrayBuilder()
                        .add(calcTempMetricList.get(0).getAverage_temp())
                        .add(calcTempMetricList.get(1).getAverage_temp())
                        .add(calcTempMetricList.get(2).getAverage_temp())
                        .add(calcTempMetricList.get(3).getAverage_temp())
                        .add(calcTempMetricList.get(4).getAverage_temp())
                        .add(calcTempMetricList.get(5).getAverage_temp())
                        .add(calcTempMetricList.get(6).getAverage_temp()))
                .build();
        return jo;
    }
    
    
    public JsonObject toJsonCalcWeek(List<CalcTempMetric> calcTempMetricList) {
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("yyyy-MM-dd");

        JsonObject jo = Json.createObjectBuilder()
                .add("weeks", Json.createArrayBuilder()
                        .add(formater.format(calcTempMetricList.get(0).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(1).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(2).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(3).getDate_temp())))
                .add("min", Json.createArrayBuilder()
                        .add(calcTempMetricList.get(0).getMin_temp())
                        .add(calcTempMetricList.get(1).getMin_temp())
                        .add(calcTempMetricList.get(2).getMin_temp())
                        .add(calcTempMetricList.get(3).getMin_temp()))
                .add("max", Json.createArrayBuilder()
                        .add(calcTempMetricList.get(0).getMax_temp())
                        .add(calcTempMetricList.get(1).getMax_temp())
                        .add(calcTempMetricList.get(2).getMax_temp())
                        .add(calcTempMetricList.get(3).getMax_temp()))
                .add("average", Json.createArrayBuilder()
                        .add(calcTempMetricList.get(0).getAverage_temp())
                        .add(calcTempMetricList.get(1).getAverage_temp())
                        .add(calcTempMetricList.get(2).getAverage_temp())
                        .add(calcTempMetricList.get(3).getAverage_temp()))
                .build();
        return jo;
    }
    
    
    public JsonObject toJsonCalcMonth(List<CalcTempMetric> calcTempMetricList) {
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("yyyy-MM-dd");

        JsonObject jo = Json.createObjectBuilder()
                .add("months", Json.createArrayBuilder()
                        .add(formater.format(calcTempMetricList.get(0).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(1).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(2).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(3).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(4).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(5).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(6).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(7).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(8).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(9).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(10).getDate_temp()))
                        .add(formater.format(calcTempMetricList.get(11).getDate_temp())))
                .add("min", Json.createArrayBuilder()
                        .add(calcTempMetricList.get(0).getMin_temp())
                        .add(calcTempMetricList.get(1).getMin_temp())
                        .add(calcTempMetricList.get(2).getMin_temp())
                        .add(calcTempMetricList.get(3).getMin_temp())
                        .add(calcTempMetricList.get(4).getMin_temp())
                        .add(calcTempMetricList.get(5).getMin_temp())
                        .add(calcTempMetricList.get(6).getMin_temp())
                        .add(calcTempMetricList.get(7).getMin_temp())
                        .add(calcTempMetricList.get(8).getMin_temp())
                        .add(calcTempMetricList.get(9).getMin_temp())
                        .add(calcTempMetricList.get(10).getMin_temp())
                        .add(calcTempMetricList.get(11).getMin_temp()))
                .add("max", Json.createArrayBuilder()
                        .add(calcTempMetricList.get(0).getMax_temp())
                        .add(calcTempMetricList.get(1).getMax_temp())
                        .add(calcTempMetricList.get(2).getMax_temp())
                        .add(calcTempMetricList.get(3).getMax_temp())
                        .add(calcTempMetricList.get(4).getMax_temp())
                        .add(calcTempMetricList.get(5).getMax_temp())
                        .add(calcTempMetricList.get(6).getMax_temp())
                        .add(calcTempMetricList.get(7).getMax_temp())
                        .add(calcTempMetricList.get(8).getMax_temp())
                        .add(calcTempMetricList.get(9).getMax_temp())
                        .add(calcTempMetricList.get(10).getMax_temp())
                        .add(calcTempMetricList.get(11).getMax_temp()))
                .add("average", Json.createArrayBuilder()
                        .add(calcTempMetricList.get(0).getAverage_temp())
                        .add(calcTempMetricList.get(1).getAverage_temp())
                        .add(calcTempMetricList.get(2).getAverage_temp())
                        .add(calcTempMetricList.get(3).getAverage_temp())
                        .add(calcTempMetricList.get(4).getAverage_temp())
                        .add(calcTempMetricList.get(5).getAverage_temp())
                        .add(calcTempMetricList.get(6).getAverage_temp())
                        .add(calcTempMetricList.get(7).getAverage_temp())
                        .add(calcTempMetricList.get(8).getAverage_temp())
                        .add(calcTempMetricList.get(9).getAverage_temp())
                        .add(calcTempMetricList.get(10).getAverage_temp())
                        .add(calcTempMetricList.get(11).getAverage_temp()))
                .build();
        return jo;
    }

    /*  @GET
    @Path("getlistdevice/{id}")
    public String listDevice(@PathParam("id") Long id) throws IOException {
        String jsonArticle = new String();
        try {
            URL urlObj = new URL("http://localhost:12080/AtlantisProjectRestful/resources/getM/"+id);
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
            System.out.println("aaaaaaa");
        } catch (Exception e) {
            System.out.println("JSON bbbbbbbbbbbbb");
        }
        return jsonArticle;
    }*/
}
