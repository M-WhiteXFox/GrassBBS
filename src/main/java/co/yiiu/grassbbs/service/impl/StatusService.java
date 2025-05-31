package co.yiiu.grassbbs.service.impl;

import co.yiiu.grassbbs.model.ServerInfo;
import co.yiiu.grassbbs.model.PlayerInfo;
import co.yiiu.grassbbs.service.IStatusService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatusService implements IStatusService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ServerInfo getServerInfo() {
        String apiUrl = "https://tgml.top:20001/prod-api/api/v1/aggregateQuery";

        try {
            String responseStr = restTemplate.getForObject(apiUrl, String.class);

            JsonNode root = objectMapper.readTree(responseStr);
            JsonNode dataNode = root.path("data");
            JsonNode kwnuNode = dataNode.path("Kwnu");

            int onlineCount = kwnuNode.path("在线人数").asInt();
            String onlinePlayers = kwnuNode.path("在线玩家").asText();
            String queryTime = dataNode.path("查询时间").asText();

            ServerInfo info = new ServerInfo();
            info.setOnlineCount(onlineCount);
            info.setOnlinePlayers(onlinePlayers);
            info.setQueryTime(queryTime);

            JsonNode topTenArray = dataNode.path("topTen");
            List<PlayerInfo> topTenList = new ArrayList<>();
            for (JsonNode playerNode : topTenArray) {
                PlayerInfo player = new PlayerInfo();
                player.setId(playerNode.path("id").asInt());
                player.setUserName(playerNode.path("userName").asText());
                player.setQq(playerNode.path("qq").asText());
                player.setIdentity(playerNode.path("identity").asText());
                player.setLastOnlineTime(playerNode.path("lastOnlineTime").asText(null));
                player.setLastOfflineTime(playerNode.path("lastOfflineTime").asText(null));
                player.setGameTime(playerNode.path("gameTime").asInt());

                topTenList.add(player);
            }
            info.setTopTen(topTenList);

            return info;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}