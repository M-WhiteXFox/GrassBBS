<#macro topics page tags=true>
    <#list page.records as topic>
        <div class="media">
            <a href="/user/${topic.username!}" class="mr-3"><img src="${topic.avatar!}" class="avatar" alt=""></a>
            <div class="media-body topic-main">
                <div class="topic-meta-left">
                    <div class="title">
                        <a href="/topic/${topic.id}">${topic.title!?html}</a>
                    </div>
                    <div class="gray">
                        <#--<#if (topic.up - topic.down) &gt; 0>
                          <i class="fa fa-chevron-up"></i>
                          <span>${topic.up - topic.down}</span>
                          <span>•</span>
                        </#if>-->
                        <#if topic.top == true>
                            <span class="badge badge-info">置顶</span>
                            <span>·</span>
                        <#elseif topic.good == true>
                            <span class="badge badge-info">精华</span>
                            <span>·</span>
                        </#if>
                        <span><a href="/user/${topic.username!}">${topic.username!}</a></span>
                        <span class="hidden-sm hidden-xs">·</span>
                        <span>${model.formatDate(topic.inTime)}</span>
                        <#if tags && topic.tags??>
                            <span>·</span>
                            <#list topic.tags as tag>
                                <a href="/topic/tag/${tag.name}"><span class="badge badge-info">${tag.name}</span></a>
                            </#list>
                        </#if>
                    </div>
                </div>
                <div class="gray topic-meta-right">
                    <div class="hidden-sm hidden-xs topic-item"><a href="/topic/${topic.id}">${topic.commentCount!0} 评论</a></div>
                    <div class="hidden-sm hidden-xs topic-item">${topic.view!0} 浏览</div>
                </div>
            </div>
        </div>
        <#if topic?has_next>
            <div class="divide mt-3 mb-3"></div>
        </#if>
    </#list>
</#macro>
