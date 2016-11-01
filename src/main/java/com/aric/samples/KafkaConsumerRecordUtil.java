package com.aric.samples;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.function.Consumer;

/**
 * Created by dursun on 11/1/16.
 */
public class KafkaConsumerRecordUtil {
    private KafkaConsumerRecordUtil() {
    }

    public static Consumer<ConsumerRecord<String, String>> consumeRecord(Consumer f){
        return (c)->{
            f.accept(String.format("Topic: %s, Partition: %s, Offset: %s, Key: %s, Value: %s", c.topic(), c.partition(), c.offset(), c.key(), c.value()));
        };
    }
}
