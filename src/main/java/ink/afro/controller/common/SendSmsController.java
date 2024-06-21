package ink.afro.controller.common;

import ink.afro.constant.Constants;
import ink.afro.entity.responseData.AxiosData;
import ink.afro.exception.type.ServiceException;
import ink.afro.utils.AliyunSmsSenderUtils;
import ink.afro.utils.RedisUtils;
import cn.hutool.core.lang.UUID;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("sms")
public class SendSmsController {

    private final RedisUtils<String> redisUtils;

    @Autowired
    public SendSmsController(RedisUtils<String> redisUtils) {
        this.redisUtils = redisUtils;
    }

    //发送短信
    @GetMapping("/send")
    public ResponseEntity<AxiosData> sendSms(String phoneNumber) {
        String signName = "阿里云短信测试";
        String templateCode = "SMS_154950909";
        String smsCode = RandomStringUtils.randomNumeric(6);
        String templateParam = "{code:" + smsCode + "}";
        String uuid = UUID.randomUUID().toString(true);
        try {
            SendSmsResponse response = AliyunSmsSenderUtils.sendSms(phoneNumber, signName, templateCode, templateParam);
            if (ObjectUtils.nullSafeEquals(response.getCode(), "OK")) {
                redisUtils.setCacheObject(Constants.SMS_CODE + uuid, smsCode, 30, TimeUnit.MINUTES);
                AxiosData axiosData = AxiosData.setData();
                axiosData.put("uuid", uuid);
                axiosData.put("msg", "短信发送成功");
                return ResponseEntity.ok(axiosData);
            } else {
                throw new ServiceException("短信发送失败");
            }
        } catch (ClientException e) {
            throw new ServiceException("短信发送失败");
        }
    }
}