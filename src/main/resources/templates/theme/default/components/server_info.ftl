<div class="card">
    <div class="card-body pb-2">
        <div class="server-status mb-3">
            <h5 class="card-title">服务器状态</h5>
            <ul class="list-unstyled mb-0">
                <li><strong>在线人数：</strong> ${serverInfo.onlineCount}</li>
                <li><strong>在线玩家：</strong> ${serverInfo.onlinePlayers}</li>
            </ul>
        </div>

        <div class="text-center mt-3">
            <a href="https://whitelist-vue-ahb.pages.dev/" target="_blank" class="btn-apply-whitelist">
                <i class="fa fa-user-plus"></i> 申请加入白名单
            </a>
            <p></p>
        </div>

        <div class="top-players">
            <h5 class="card-title">玩家排名</h5>
            <ol class="pl-3 mb-0">
                <#list serverInfo.topTen as player>
                    <li>
                        <a href="javascript:void(0);"
                           class="player-name decorated-player"
                           data-qq="${player.qq?html}"
                           data-game-time="${player.gameTime}"
                           data-identity="${player.identity?html}"
                           data-last-online="${player.lastOnlineTime?if_exists?html}"
                           data-last-offline="${player.lastOfflineTime?if_exists?html}">
                            ${player.userName?html}
                        </a>
                    </li>
                </#list>
            </ol>
        </div>
    </div>

    <div class="card-footer pt-2 pb-2" style="background-color: white; font-size: 14px;">
        <div class="text-muted">点击玩家昵称查看详细信息</div>
    </div>
</div>

<!-- 玩家弹窗 -->
<div id="player-popup" class="player-popup">
    <div><strong>QQ：</strong><span id="popup-qq"></span></div>
    <div><strong>游戏时长：</strong><span id="popup-game-time"></span> 分钟</div>
    <div><strong>身份：</strong><span id="popup-identity"></span></div>
    <div><strong>最后上线：</strong><span id="popup-last-online"></span></div>
    <div><strong>最后离线：</strong><span id="popup-last-offline"></span></div>
</div>

<script>
    // 点击玩家名时显示弹出层
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

            // 设置初始不可见，参与布局
            popup.style.visibility = 'hidden';
            popup.style.display = 'block';

            requestAnimationFrame(() => {
                const popupWidth = popup.offsetWidth;
                const popupHeight = popup.offsetHeight;

                const offsetX = 15, offsetY = 15;
                const windowWidth = window.innerWidth;
                const windowHeight = window.innerHeight;

                const mouseX = event.clientX;
                const mouseY = event.clientY;

                let left = mouseX + offsetX;
                let top = mouseY + offsetY;

                // 如果超出右边或下边，改为左/上方显示
                if (left + popupWidth > windowWidth) {
                    left = mouseX - popupWidth - offsetX;
                }
                if (top + popupHeight > windowHeight) {
                    top = mouseY - popupHeight - offsetY;
                }

                // 最小限制，避免负数
                if (left < 0) left = 5;
                if (top < 0) top = 5;

                popup.style.left = left + 'px';
                popup.style.top = top + 'px';
                popup.style.visibility = 'visible';
                popup.style.display = 'block';
            });
        });
    });

    // 点击其他地方隐藏弹出层
    document.addEventListener('click', () => {
        const popup = document.getElementById('player-popup');
        popup.style.display = 'none';
    });
</script>
