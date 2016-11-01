package com.aric.samples;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by dursun on 11/1/16.
 */
public class ConsumerGroupApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "myTestGroup");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("my_big_topic"));
            while (true) {
                ConsumerRecords<String, String> cr = consumer.poll(100L);
                cr.forEach(KafkaConsumerRecordUtil.consumeRecord(System.out::println));
            }
        }

    }
}
