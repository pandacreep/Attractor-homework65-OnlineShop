<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=["/js/main.js", "/js/products.js", "/js/buy.js"]>

    <h1 class="my-3 text-center">Products</h1>
    <div class="row flex-wrap" id="itemList">
        <#list items as product>
            <div class="col-3">
                <div class="card m-1">
                    <img src="/images/products/${product.image}" class="card-img-top" alt="${product.image}">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">
                            Id: ${product.id};
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

    <#include "partials/pagination.ftlh">

    <div class="mb-5" id="end"></div>

</@main.renderWith>