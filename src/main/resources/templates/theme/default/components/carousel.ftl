<#macro carousel images=[] showIndicators=true showControls=true>
    <#if images?? && images?size gt 0>
        <div id="mainCarousel" class="carousel slide mb-3" data-ride="carousel">
            <#if showIndicators>
                <ol class="carousel-indicators">
                    <#list images as image>
                        <li data-target="#mainCarousel"
                            data-slide-to="${image?index}"
                            class="${(image?is_first)?then('active','')}"></li>
                    </#list>
                </ol>
            </#if>

            <div class="carousel-inner">
                <#list images as image>
                    <div class="carousel-item ${(image?is_first)?then('active','')}">
                        <a href="${image.webUrl!}">
                            <img src="${image.imageUrl!}"
                                 class="d-block w-100 carousel-img"
                                 alt="${image.title!}">
                        </a>
                    </div>
                </#list>
            </div>

            <#if showControls && images?size gt 1>
                <a class="carousel-control-prev" href="#mainCarousel" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#mainCarousel" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </#if>
        </div>
    </#if>
</#macro>