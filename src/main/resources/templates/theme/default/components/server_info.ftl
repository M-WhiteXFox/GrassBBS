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

<div id="player-popup" style="display:none; position:absolute; background:#fff; border:1px solid #ccc; padding:10px; box-shadow:0 2px 8px rgba(0,0,0,0.15); z-index:9999; min-width:200px; border-radius:4px; font-size:14px; color:#333;">
    <div><strong>QQ：</strong><span id="popup-qq"></span></div>
    <div><strong>游戏时长：</strong><span id="popup-game-time"></span> 分钟</div>
    <div><strong>身份：</strong><span id="popup-identity"></span></div>
    <div><strong>最后上线：</strong><span id="popup-last-online"></span></div>
    <div><strong>最后离线：</strong><span id="popup-last-offline"></span></div>
</div>

<script>
    document.querySelectorAll('.player-name').forEach(el => {
        el.addEventListener('click', function(event) {
            event.preventDefault();
            event.stopPropagation();

            const qq = this.getAttribute('data-qq') || '无';
            const gameTime = this.getAttribute('data-game-time') || '0';
            const identity = this.getAttribute('data-identity') || '无';
            const lastOnline = this.getAttribute('data-last-online') || '无';
            const lastOffline = this.getAttribute('data-last-offline') || '无';

            const popup = document.getElementById('player-popup');
            document.getElementById('popup-qq').textContent = qq;
            document.getElementById('popup-game-time').textContent = gameTime;
            document.getElementById('popup-identity').textContent = identity;
            document.getElementById('popup-last-online').textContent = lastOnline;
            document.getElementById('popup-last-offline').textContent = lastOffline;

            // 先设置成不可见但参与布局，才能正确测量尺寸
            popup.style.visibility = 'hidden';
            popup.style.display = 'block';

            // 等浏览器渲染后获取宽高
            requestAnimationFrame(() => {
                const popupWidth = popup.offsetWidth;
                const popupHeight = popup.offsetHeight;

                const offsetX = 15, offsetY = 15;
                const scrollX = window.pageXOffset || document.documentElement.scrollLeft;
                const scrollY = window.pageYOffset || document.documentElement.scrollTop;

                const mouseX = event.clientX + scrollX;
                const mouseY = event.clientY + scrollY;

                const windowWidth = document.documentElement.clientWidth + scrollX;
                const windowHeight = document.documentElement.clientHeight + scrollY;

                let left = mouseX + offsetX;
                let top = mouseY + offsetY;

                if (left + popupWidth > windowWidth) {
                    left = mouseX - popupWidth - offsetX;
                }
                if (top + popupHeight > windowHeight) {
                    top = mouseY - popupHeight - offsetY;
                }

                if (left < scrollX) left = scrollX + 5;
                if (top < scrollY) top = scrollY + 5;

                popup.style.left = left + 'px';
                popup.style.top = top + 'px';

                // 最后显示并恢复可见
                popup.style.visibility = 'visible';
                popup.style.display = 'block';
            });
        });
    });

</script>
