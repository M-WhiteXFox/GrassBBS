<div class="card">
    <div class="server-status">
        <h3>服务器状态</h3>
        <ul>
            <li><strong>在线人数：</strong> ${serverInfo.onlineCount}</li>
            <li><strong>在线玩家：</strong> ${serverInfo.onlinePlayers}</li>
            <li><strong>查询时间：</strong> ${serverInfo.queryTime}</li>
        </ul>
    </div>

    <div class="top-players">
        <h3>玩家排名</h3>
        <ol>
            <#list serverInfo.topTen as player>
                <li>
                    <a href="javascript:void(0);"
                       class="player-name"
                       data-qq="${player.qq?html}"
                       data-game-time="${player.gameTime}"
                       data-identity="${player.identity?html}"
                       data-last-online="${player.lastOnlineTime?if_exists?html}"
                       data-last-offline="${player.lastOfflineTime?if_exists?html}"
                    >
                        ${player.userName?html}
                    </a>
                </li>
            </#list>
        </ol>
    </div>

    <div id="player-popup" style="display:none; position:absolute; background:#fff; border:1px solid #ccc; padding:10px; box-shadow: 2px 2px 8px rgba(0,0,0,0.2); z-index:1000; min-width:180px;">
        <div><strong>QQ：</strong><span id="popup-qq"></span></div>
        <div><strong>游戏时长：</strong><span id="popup-game-time"></span> 分钟</div>
        <div><strong>身份：</strong><span id="popup-identity"></span></div>
        <div><strong>最后上线：</strong><span id="popup-last-online"></span></div>
        <div><strong>最后离线：</strong><span id="popup-last-offline"></span></div>
    </div>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const popup = document.getElementById('player-popup');

        document.querySelectorAll('.player-name').forEach(el => {
            el.addEventListener('click', function(event) {
                event.preventDefault();  // 防止a标签默认跳转
                event.stopPropagation(); // 阻止事件冒泡

                const qq = this.getAttribute('data-qq') || '无';
                const gameTime = this.getAttribute('data-game-time') || '0';
                const identity = this.getAttribute('data-identity') || '无';
                const lastOnline = this.getAttribute('data-last-online') || '无';
                const lastOffline = this.getAttribute('data-last-offline') || '无';

                document.getElementById('popup-qq').textContent = qq;
                document.getElementById('popup-game-time').textContent = gameTime;
                document.getElementById('popup-identity').textContent = identity;
                document.getElementById('popup-last-online').textContent = lastOnline;
                document.getElementById('popup-last-offline').textContent = lastOffline;

                // 弹窗位置跟随鼠标
                const mouseX = event.pageX;
                const mouseY = event.pageY;

                popup.style.left = mouseX + 10 + 'px';
                popup.style.top = mouseY + 10 + 'px';
                popup.style.display = 'block';
            });
        });

        // 点击其他区域关闭弹窗
        document.addEventListener('click', function() {
            popup.style.display = 'none';
        });
    });
</script>


<style>
    .server-status ul {
        list-style: none;
        padding: 0;
    }
    .top-players ol {
        padding-left: 20px;
    }
    .player-name {
        color: #007bff;
        cursor: pointer;
        text-decoration: underline;
    }
    .player-name:hover {
        color: #0056b3;
    }
</style>
