package ink.afro.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ink.afro.entity.other.Location;
import ink.afro.exception.type.ServiceException;
import ink.afro.utils.spring.SpringUtils;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

public class LocationUtils {
    private static final String API_ENDPOINT = "http://whois.pconline.com.cn/ipJson.jsp?json=true&ip=(ipAddress)";

    public static Location getLocation(String ipAddress){
        RestTemplate restTemplate = new RestTemplate();
        String url = API_ENDPOINT.replace("{ipAddress}", ipAddress);
        try {
            String forObject = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = SpringUtils.getBean(Jackson2ObjectMapperBuilder.class).build();
            return objectMapper.readValue(forObject,Location.class);
        } catch (JsonProcessingException e) {
            throw new ServiceException("Json 处理异常");
        }catch (Exception e) {
            throw new ServiceException("获取地理位置失败");
        }
    }
}
