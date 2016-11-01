package com.aric.samples;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by dursun on 10/31/16.
 */
public class ConsumerAssignAppSample {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "test");

        Collection<TopicPartition> parts = new ArrayList<>();
        parts.add(new TopicPartition("my_topic", 0));
        parts.add(new TopicPartition("my_other_topic", 1));
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props)) {
            consumer.assign(parts);
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(10);
                consumerRecords.forEach(c -> {
                    System.out.printf("Topic: %s, Partition: %s, Offset: %s, Key: %s, Value: %s\n", c.topic(), c.partition(), c.offset(), c.key(), c.value());
                });
            }
        }
    }
}
