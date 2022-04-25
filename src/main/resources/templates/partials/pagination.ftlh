<div>
    <#if hasPrev>
        <a id="loadPrev" href="${prevPageLink!''}">&lt;&lt;&lt;Previous page</a>
    <#else>
        <span id="loadPrev">Previous page</span>
    </#if>

    <#if hasNext>
        <a id="loadNext" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize!4}">Next page&gt;&gt;&gt;</a>
    <#else>
        <span>Next page</span>
    </#if>
</div>