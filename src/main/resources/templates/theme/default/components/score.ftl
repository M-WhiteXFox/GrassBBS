<#macro score limit top100=false>
    <div class="card">
        <div class="card-header">
            积分榜
            <#if !top100>
                <span class="pull-right"><a href="/top100">Top100</a></span>
            </#if>
        </div>
        <table class="table">
            <#if top100>
                <tr>
                    <th>用户名</th>
                    <th>积分</th>
                </tr>
            </#if>
            <@tag_score limit=limit>
                <#list users as user>
                    <tr>
                        <td>
                            <#if user_index == 0>
                                🥇
                            <#elseif user_index == 1>
                                🥈
                            <#elseif user_index == 2>
                                🥉
                            </#if>
                            <a href="/user/${user.username}">${user.username}</a>
<#--                            <#if user_index == 0>-->
<#--                                <img src="/static/img/gold.png" alt="第一名" title="第一名" style="width:16px;height:16px;margin-left:5px;">-->
<#--                            <#elseif user_index == 1>-->
<#--                                <img src="/static/img/silver.png" alt="第二名" title="第二名" style="width:16px;height:16px;margin-left:5px;">-->
<#--                            <#elseif user_index == 2>-->
<#--                                <img src="/static/img/bronze.png" alt="第三名" title="第三名" style="width:16px;height:16px;margin-left:5px;">-->
<#--                            </#if>-->
                        </td>
                        <td>${user.score}</td>
                    </tr>
                </#list>
            </@tag_score>
        </table>
    </div>
</#macro>
