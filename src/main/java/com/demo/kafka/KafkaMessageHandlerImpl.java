package com.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

/**
 * The type Kafka message handler.
 */
public class KafkaMessageHandlerImpl implements KafkaMessageHandler{

    /**
     * The Log.
     */
    static Logger log = Logger.getLogger(KafkaMessageHandlerImpl.class.getName());

    @Override
    public void processMessage(String topicName, ConsumerRecord<String, String> message) throws Exception {
        String position = message.partition() + "-" + message.offset();
        String source = KafkaMessageHandlerImpl.class.getName();
        JSONObject obj = MessageHelper.getMessageLogEntryJSON(source, topicName,message.key(),message.value());
        log.info(obj.toJSONString());
    }
}
