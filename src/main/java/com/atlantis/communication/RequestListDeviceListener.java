/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.communication;

import java.util.Enumeration;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.*;

@Stateless
public class RequestListDeviceListener {

    @Resource(mappedName = "jms/JMSConnectionFactory")
    private ConnectionFactory connectionFactory;
 
    @Resource(mappedName = "jms/listDeviceReceiveQ")
    Queue listDeviceQ2;
  
    private String message;
 
    public String receiveMessage() {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueBrowser queueBrowser = session.createBrowser(listDeviceQ2);
            Enumeration enumeration = queueBrowser.getEnumeration();
            while (enumeration.hasMoreElements()) {
                TextMessage o = (TextMessage) enumeration.nextElement();
                System.out.println("Receive " + o.getText());
               return "Receive " + o.getText();
 
            }
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return "";
    }
}
