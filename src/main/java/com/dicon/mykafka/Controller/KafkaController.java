package com.dicon.mykafka.Controller;

import com.alibaba.fastjson.JSONObject;
import com.dicon.mykafka.Utils.KafkaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class KafkaController {

    @Autowired
    private KafkaUtils kafkaUtils;

    /**
     * 发送消息至kafka
     *
     *
     *
     * @param sendMsgParam 发送消息的参数
     */

    @RequestMapping(value = "/MyKafka/sendMsg",method = RequestMethod.POST)
    public void sendMsg(@RequestBody String sendMsgParam){

        JSONObject jsonObject = JSONObject.parseObject(sendMsgParam);

        String acks = jsonObject.getString("acks");

        String retries = jsonObject.getString("retries");

        String batchSize = jsonObject.getString("batchSize");

        String lingerMs = jsonObject.getString("lingerMs");

        String bufferMemory = jsonObject.getString("bufferMemory");

        String msg = jsonObject.getString("msg");

        kafkaUtils.sendMsg(acks,retries,batchSize,lingerMs,bufferMemory,msg);

    }

}
