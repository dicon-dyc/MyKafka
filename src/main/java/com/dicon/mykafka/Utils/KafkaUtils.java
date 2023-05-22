package com.dicon.mykafka.Utils;

import com.dicon.mykafka.Configuration.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author dyc
 * @date 20230522
 */

@Component
public class KafkaUtils {

    @Autowired
    private KafkaConfig kafkaConfig;

    private KafkaUtils(){

    }


    /**
     * 发送消息(初步实现，暂未考虑key不为null的情况，因key为null因此分发策略为轮询)
     *
     * @param acks acks 默认为1
     * 0：生产者成功写入消息之后不会等到服务器返回信息，可能会因数据写入失败造成数据丢失，优点是吞吐量高。
     * 1：生产者成功写入消息之后会等待副本同步队列中的leader写入成功后返回写入成功响应，可能会导致系数据写入失败，优点
     *是不像0那样极端。
     * all/-1：生产者成功写入消息之后必须等到所有副本同步队列中的分区全部写入成功后返回写入成功响应。
     * @param retries retries 生产者从服务器收到的错误有可能是临时性的错误次数
     * @param batchSize batchSize 指定了一个批次可以使用的内存大小，按照字节数计算
     * @param lingerMs lingerMs 该参数指定了生产者在发送批次之前等待更多消息加入批次的时间，增加延迟，提高吞吐量
     * @param bufferMemory bufferMemory 该参数用来设置生产者内存缓冲区的大小，生产者用它缓冲要发送到服务器的消息
     * @param msg 需要发送的消息
     */
    public void sendMsg( @Nullable String acks, @Nullable String retries,
                        @Nullable String batchSize,@Nullable String lingerMs,@Nullable String bufferMemory,
                        String msg){


        Properties properties = new Properties();

        //配置BootstrapServer,可以在application-test中更改
        properties.put("bootstrap.servers",kafkaConfig.getBootStrapServer());

        //配置acks
        if (acks != null){
            properties.put("acks",acks);
        }

        //配置retrie
        if (retries != null){
            properties.put("retries",retries);
        }

        //配置batch.size
        if (batchSize != null){
            properties.put("batch.size",batchSize);
        }

        //配置linger.ms
        if (lingerMs != null){
            properties.put("linger.ms",lingerMs);
        }

        //配置buffer.memory
        if (bufferMemory != null){
            properties.put("buffer.memory",bufferMemory);
        }

        //key和value的序列化
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        Producer<String,String> producer = new KafkaProducer<String, String>(properties);

        producer.send(new ProducerRecord<>("test",msg));


    }



}
