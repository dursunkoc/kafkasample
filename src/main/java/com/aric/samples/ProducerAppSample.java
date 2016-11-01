package com.aric.samples;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Hello world!
 *
 */
public class ProducerAppSample {
    public static void main(final String[] args) {
        final Properties prop = new Properties();
        prop.put("bootstrap.servers", "localhost:9093, localhost:9092");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("batch.size", "10000");
        prop.put("max.block.ms", "10000");
        prop.put("linger.ms", "10000");

        try (final KafkaProducer<String, String> myProducer = new KafkaProducer<>(prop)) {
            for (int i = 0; i < 5; i++) {
                final ProducerRecord<String, String> record = new ProducerRecord<String, String>("my_topic",
                        Integer.toString(i), "My Message " + i);
                myProducer.send(record);
            }
        }

    }
}
