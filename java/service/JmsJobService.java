package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.JobPost;
import repository.JobRepository;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class JmsJobService {

    @Resource
    private ConnectionFactory connectionFactory;

    @Resource(name="jobPostQueue")
    private Queue jobPostsQueue;

    public String sendJmsJobPost(JobPost post) {
        try (final Connection connection = connectionFactory.createConnection();
             final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
             final MessageProducer producer = session.createProducer(jobPostsQueue)) {
            connection.start();
            ObjectMapper om = new ObjectMapper();
            String jsonUser = om.writeValueAsString(post);

            final Message jmsMessage = session.createTextMessage(jsonUser);
            producer.send(jmsMessage);

            return "Successfully sent Job Post!";
        } catch (final Exception e) {
            throw new RuntimeException("Caught exception from JMS when sending a message", e);
        }
    }

    public JobPost getJmsJobPost() {

        try (final Connection connection = connectionFactory.createConnection();
             final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
             final MessageConsumer messageConsumer = session.createConsumer(jobPostsQueue)) {

            connection.start();


            final Message jmsMessage = messageConsumer.receive(1000);

            if(jmsMessage == null) {
                return null;
            }

            TextMessage obj = (TextMessage) jmsMessage;
            ObjectMapper om = new ObjectMapper();

            JobPost jobPost = om.readValue(obj.getText(), JobPost.class);

            return jobPost;

        } catch (final Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Caught exception from JMS when receiving a message", e);
        }
    }


}
