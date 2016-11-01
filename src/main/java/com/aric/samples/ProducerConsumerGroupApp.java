package com.aric.samples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by dursun on 11/1/16.
 */
public class ProducerConsumerGroupApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093, localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try(KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props)){
            for (int i = 0; i < 40; i++) {
                ProducerRecord<String,String> r = new ProducerRecord<>("my_big_topic","Key-"+i,"Value-"+i+"-");
                kafkaProducer.send(r);
            }
        }
    }
}
