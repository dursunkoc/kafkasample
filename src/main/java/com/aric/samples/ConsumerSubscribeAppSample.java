package com.aric.samples;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by dursun on 10/31/16.
 */
public class ConsumerSubscribeAppSample {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093, localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id","test");

        Collection<String> topx = new ArrayList<>();
        topx.add("my_topic");
        topx.add("my_other_topic");

        try(KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props)) {
            kafkaConsumer.subscribe(topx);
            while (true){
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(10);
                consumerRecords.forEach(KafkaConsumerRecordUtil.consumeRecord(System.out::println));
            }
        }


    }
}
