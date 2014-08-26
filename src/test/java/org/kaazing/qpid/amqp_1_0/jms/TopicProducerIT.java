/*
 * Copyright (c) 2007-2014, Kaazing Corporation. All rights reserved.
 */
package org.kaazing.qpid.amqp_1_0.jms;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;

import org.apache.qpid.amqp_1_0.jms.Connection;
import org.apache.qpid.amqp_1_0.jms.ConnectionFactory;
import org.apache.qpid.amqp_1_0.jms.MessageProducer;
import org.apache.qpid.amqp_1_0.jms.Session;
import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.junit.Rule;
import org.junit.Test;
import org.kaazing.robot.junit.annotation.Robotic;
import org.kaazing.robot.junit.rules.RobotRule;

public class TopicProducerIT {

    @Rule
    public RobotRule robot = new RobotRule().setScriptRoot("org/kaazing/robot/scripts/amqp_1_0/jms/topic/producer");

    @Robotic(script = "create.then.close")
    @Test(timeout = 1000)
    public void shouldCreateProducer() throws Exception {
        ConnectionFactory factory = new ConnectionFactoryImpl("localhost", 5672, null, null, "clientID");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(session.createTopic("topic://topic-A"));
        producer.close();
        session.close();
        connection.close();
        robot.join();
    }
}
