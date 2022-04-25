<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3 text-center">Categories</h1>
    <ul>
        <#list items as category>
            <li>
                <a href="/categories/${category.id}">${category.name}</a>
            </li>
        </#list>
    </ul>

    <#include "partials/pagination.ftlh">

</@main.renderWith>