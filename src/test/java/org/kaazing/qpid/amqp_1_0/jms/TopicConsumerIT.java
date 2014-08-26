/*
 * Copyright (c) 2007-2014, Kaazing Corporation. All rights reserved.
 */
package org.kaazing.qpid.amqp_1_0.jms;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;
import static javax.jms.Session.CLIENT_ACKNOWLEDGE;
import static javax.jms.Session.DUPS_OK_ACKNOWLEDGE;

import org.apache.qpid.amqp_1_0.jms.Connection;
import org.apache.qpid.amqp_1_0.jms.ConnectionFactory;
import org.apache.qpid.amqp_1_0.jms.MessageConsumer;
import org.apache.qpid.amqp_1_0.jms.Session;
import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.junit.Rule;
import org.junit.Test;
import org.kaazing.robot.junit.annotation.Robotic;
import org.kaazing.robot.junit.rules.RobotRule;

public class TopicConsumerIT {

    @Rule
    public RobotRule robot = new RobotRule().setScriptRoot("org/kaazing/robot/scripts/amqp_1_0/jms/topic/consumer");

    @Robotic(script = "create")
    @Test(timeout = 1000)
    public void shouldCreateNonDurableAutoAcknowledgeConsumer() throws Exception {
        ConnectionFactory factory = new ConnectionFactoryImpl("localhost", 5672, null, null, "clientID");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(session.createTopic("topic-A"));
        consumer.close();
        session.close();
        connection.close();
        robot.join();
    }

    @Robotic(script = "create")
    @Test(timeout = 1000)
    public void shouldCreateNonDurableClientAcknowledgeConsumer() throws Exception {
        ConnectionFactory factory = new ConnectionFactoryImpl("localhost", 5672, null, null, "clientID");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, CLIENT_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(session.createTopic("topic-A"));
        consumer.close();
        session.close();
        connection.close();
        robot.join();
    }

    @Robotic(script = "create")
    @Test(timeout = 1000)
    public void shouldCreateNonDurableDupsOkayAcknowledgeConsumer() throws Exception {
        ConnectionFactory factory = new ConnectionFactoryImpl("localhost", 5672, null, null, "clientID");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, DUPS_OK_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(session.createTopic("topic-A"));
        consumer.close();
        session.close();
        connection.close();
        robot.join();
    }

    @Robotic(script = "create.durable")
    @Test(timeout = -1000)
    public void shouldCreateDurableAutoAcknowledgeConsumer() throws Exception {
        ConnectionFactory factory = new ConnectionFactoryImpl("localhost", 5672, null, null, "clientID");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, CLIENT_ACKNOWLEDGE);
        MessageConsumer consumer = session.createDurableSubscriber(session.createTopic("topic://topic-A"), "durable-X");
        consumer.close();
        session.unsubscribe("durable-X");
        session.close();
        connection.close();
        robot.join();
    }

}
