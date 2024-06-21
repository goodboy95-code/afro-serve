package ink.afro.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

public class AliyunSmsSenderUtils {
    private static final String ACCESS_KEY_ID = "1234567890";
    private static final String ACCESS_KEY_SECRET = "1234567890";

    /**
     *
     * @param phoneNumbers 电话号码
     * @param signName 签名
     * @param templateCode 短信模板CODE
     * @param templateParam 短信模板变量对应的实际值，JSON格式
     * @return SendSmsResponse
     */
    public static SendSmsResponse sendSms(String phoneNumbers, String signName, String templateCode, String templateParam) throws ClientException {
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        // 创建API请求并设置参数
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNumbers); // 接收短信的手机号码
        request.setSignName(signName); // 短信签名名称
        request.setTemplateCode(templateCode); // 短信模板CODE
        request.setTemplateParam(templateParam); // 短信模板变量对应的实际值，JSON格式

        // 发起请求并处理响应或异常
        return client.getAcsResponse(request);
    }
}
