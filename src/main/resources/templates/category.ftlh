<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=["/js/main.js", "/js/category.js", "/js/buy.js"]>
    <h1 class="my-3 text-center" data-total-count="${totalCount}" data-category-id="${categoryId!1}" id="category-header">
        ${category.name}
    </h1>
    <div class="row flex-wrap" id="itemList">
        <#list items as product>
            <div class="col-3">
                <div class="card m-1">
                    <img src="/images/products/${product.image}" class="card-img-top" alt="${product.image}">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">
                            Price: ${product.price};
                            Description: ${product.description}<br>
                        </p>
                        <a href="/products/${product.id}" class="btn btn-primary">Details</a>
                        <a href="#" class="btn btn-primary buy" data-product-id="${product.id}">Buy</a>
                    </div>
                </div>
            </div>
        </#list>
    </div>

    <#include "partials/pagination-rest.ftlh">

</@main.renderWith>