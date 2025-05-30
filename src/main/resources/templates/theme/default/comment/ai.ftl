<#macro aiAssistant topicContent>
    <script src="/static/theme/default/js/ai.js"></script>
    <link rel="stylesheet" href="/static/theme/default/css/ai.css"/>
    <#-- 把帖子内容输出到 JS 可访问的地方 -->
    <#if topicContent?? && topicContent?has_content>
        <script>
            window.topicContent = `${topicContent?js_string}`;
        </script>
    <#else>
        <script>
            window.topicContent = null;
        </script>
    </#if>
    <!-- 弹窗 -->
    <div id="ai-popup" class="ai-popup-container">
        <div class="ai-popup-header">
            <span>AI 助手</span>
            <button class="ai-close-btn" onclick="closeAIPopup()">×</button>
        </div>
        <div class="ai-popup-content" id="ai-messages">
            <p>欢迎使用 AI 助手</p>
            <#if topicContent?? && topicContent?has_content>
                <p>你正在查看的帖子内容：</p>
                <div class="topic-preview">
                    ${topicContent?html}
                </div>
            </#if>
        </div>
        <div class="ai-input-area">
            <input id="ai-input" type="text" placeholder="请输入你的问题..." />
            <button onclick="sendAIQuestion()">发送</button>
        </div>
    </div>
    <!-- 图标按钮 -->
    <div id="ai-icon-btn" class="ai-icon-btn hidden">AI</div>
</#macro>