package ink.afro.utils;


import ink.afro.utils.spring.SpringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * servlet工具类
 *
 * @author goodboy95-code
 */
public class ServletUtils {
    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    public static Map<String, String> getParamMap(ServletRequest request) {
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : getParams(request).entrySet()) {
            params.put(entry.getKey(), StringUtils.arrayToCommaDelimitedString(entry.getValue()));
        }
        return params;
    }

    public static Map<String, String[]> getParams(ServletRequest request) {
        final Map<String, String[]> map = request.getParameterMap();
        return Collections.unmodifiableMap(map);
    }


    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param o        待渲染的对象
     */
    public static void renderObject(HttpServletResponse response, Object o) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            ObjectMapper objectMapper = SpringUtils.getBean(Jackson2ObjectMapperBuilder.class).build();
            String json = objectMapper.writeValueAsString(o);
            response.getWriter().print(json);
        } catch (IOException e) {
           throw  new RuntimeException(e);
        }
    }

}
