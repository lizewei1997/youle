package com.offcn.listen;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.offcn.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsListen {

    @Autowired
    private SmsUtil smsUtil;

    @JmsListener(destination = "offcn_sms")
    public void sendSms(Map<String, String> map) {
        try {

            System.out.println(map.get("phone") + ">>>" + map.get("code") + ">>>" + map.get("sign") + ">>>" + map.get("templateCode"));

            SendSmsResponse smsResponse = smsUtil.sendSms(map.get("phone"), map.get("code"), map.get("sign"), map.get("templateCode"));

            System.out.println("发送状态code：" + smsResponse.getCode());
            System.out.println("发送结果的消息：" + smsResponse.getMessage());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
