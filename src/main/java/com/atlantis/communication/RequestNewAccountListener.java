/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.communication;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

@JMSDestinationDefinition(
        name = "java:global/queue/newAccountQueue",
        interfaceName = "javax.jms.Queue",
        destinationName = "newAccountQueue"
)
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "java:global/queue/newAccountQueue")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class RequestNewAccountListener implements MessageListener {

    public RequestNewAccountListener() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Message received: " + message.getBody(String.class));
        } catch (JMSException ex) {
            Logger.getLogger(RequestNewAccountListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
