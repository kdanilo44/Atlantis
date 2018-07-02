/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.communication;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author user
 */
@Stateless
@Path("message")
public class RequestResource {


    @EJB RequestSender sender;
 
    @EJB RequestListDeviceListener receiver;
    
   /* @GET
    @Path("sendlistdevice/{id}")
    public Response SendRequestListDevice(@PathParam("id") Long id) {
        this.requestSender.sendListDeviceMessage(id);
        return Response.ok().build();
    }*/
    
    @GET
    @Path("sendlistdevice/{id}")
    public String SendRequestListDevice(@PathParam("id") Long id) {
        sender.sendListDeviceMessage(id);
      //  receiver.receiveMessage();
        return receiver.receiveMessage();
    }
    
    
/*
    @GET
    @Path("sendrawmetric/{id}")
    public Response SendRequestRawMetric(@PathParam("id") Long id) {
        this.requestSender.sendRawMetricMessage(id);
        return Response.ok().build();
    }
    
    @GET
    @Path("sendcommand")
    public Response SendRequestCommand() {
        this.requestSender.sendCommandMessage();
        return Response.ok().build();
    }
    
        @GET
    @Path("sendnewaccount")
    public Response SendRequestNewAccount() {
        this.requestSender.sendNewAccountMessage();
        return Response.ok().build();
    }
    
    */

}
