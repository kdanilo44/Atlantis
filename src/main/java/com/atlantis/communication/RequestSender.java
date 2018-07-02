/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.communication;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author user
 */
@Stateless
public class RequestSender {

    @Resource(mappedName = "jms/JMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/listDeviceReceiveQ")
    Queue listDeviceQ2;

    @Resource(lookup = "java:global/queue/rawMetricQueue")
    Queue rawMetricqueue;

    @Resource(lookup = "java:global/queue/commandQueue")
    Queue commandqueue;

    @Resource(lookup = "java:global/queue/newAccountQueue")
    Queue newAccountqueue;
    
    /*   public void sendListDeviceMessage(Long id) {
        ctx.createProducer().send(listDevicequeue, String.valueOf(id));
    }*/
    public void sendListDeviceMessage(Long id) {
      /*  MessageProducer messageProducer;
        TextMessage textMessage;
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(listDeviceQ2);
            textMessage = session.createTextMessage();
            System.out.println("message 1 sent");
            textMessage.setText("je suis un message" + id);
            messageProducer.send(textMessage);

            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }*/
    }
/*
    public void sendRawMetricMessage(long id) {
        ctx.createProducer().send(listDevicequeue, String.valueOf(id));
    }

    public void sendCommandMessage() {
        ctx.createProducer().send(listDevicequeue, "command");
    }

    public void sendNewAccountMessage() {
        ctx.createProducer().send(listDevicequeue, "newAccount");
    }
*/
}
