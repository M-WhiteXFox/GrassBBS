package co.yiiu.grassbbs.controller.api;

import co.yiiu.grassbbs.config.AIConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController extends BaseApiController {

    private final RestTemplate restTemplate;
    private final AIConfig aiConfig;
    private final Logger logger = LoggerFactory.getLogger(AIController.class);

    @Autowired
    public AIController(AIConfig aiConfig) {
        this.restTemplate = new RestTemplate();
        this.aiConfig = aiConfig;
        logger.info("AI配置已加载: baseUrl={}, model={}", aiConfig.getBaseUrl(), aiConfig.getModel());
    }

    @PostMapping("/chat")
    public Object chat(@RequestBody Map<String, Object> requestBody) {
        try {
            String userMessage = (String) requestBody.get("message");
            String topicContent = (String) requestBody.get("topicContent");
            String combinedMessage = "帖子内容:\n" + topicContent + "\n用户提问:\n" + userMessage;

            if (userMessage == null || userMessage.trim().isEmpty()) {
                return error("消息不能为空");
            }

            logger.info("接收到AI聊天请求，消息内容: {}", combinedMessage);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + aiConfig.getApiKey());

            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("model", aiConfig.getModel());

            List<Map<String, String>> messages = new ArrayList<>();
            
            // 添加system prompt作为第一条消息
            if (aiConfig.getSystemPrompt() != null && !aiConfig.getSystemPrompt().trim().isEmpty()) {
                Map<String, String> systemMessage = new HashMap<>();
                systemMessage.put("role", "system");
                systemMessage.put("content", aiConfig.getSystemPrompt());
                messages.add(systemMessage);
                logger.debug("添加system prompt: {}", aiConfig.getSystemPrompt());
            }
            
            // 添加用户消息
            Map<String, String> message = new HashMap<>();
            message.put("role", "user");
            message.put("content", combinedMessage);
            messages.add(message);

            requestMap.put("messages", messages);
            requestMap.put("stream", false);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestMap, headers);

            try {
                ResponseEntity<Map> responseEntity = restTemplate.postForEntity(
                        aiConfig.getBaseUrl(), entity, Map.class);

                if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                    return error("AI服务响应异常: " + responseEntity.getStatusCode());
                }

                Map<String, Object> response = responseEntity.getBody();
                if (response == null) {
                    return error("AI服务返回空响应");
                }

                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                if (choices == null || choices.isEmpty()) {
                    return error("AI服务返回无效响应格式");
                }

                Map<String, Object> choice = choices.get(0);
                Map<String, Object> messageResponse = (Map<String, Object>) choice.get("message");
                if (messageResponse == null || !messageResponse.containsKey("content")) {
                    return error("AI服务返回无效消息格式");
                }

                String content = (String) messageResponse.get("content");
                if (content == null || content.trim().isEmpty()) {
                    return error("AI服务返回空消息内容");
                }

                logger.info("AI回复内容: {}", content);
                return success(content);

            } catch (HttpClientErrorException e) {
                logger.error("AI服务客户端错误: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
                return error("AI服务错误: " + e.getStatusCode() + " - " + e.getMessage());
            } catch (Exception e) {
                logger.error("调用AI服务失败", e);
                return error("AI服务连接失败: " + e.getMessage());
            }
        } catch (Exception e) {
            logger.error("AI聊天处理异常", e);
            return error("处理请求失败: " + e.getMessage());
        }
    }
}