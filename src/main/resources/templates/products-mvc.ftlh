<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3 text-center">Products MVC</h1>
    <div class="row flex-wrap">
        <#list items as product>
            <div class="col-3">
                <div class="card m-1">
                    <img src="/images/products/${product.image}" class="card-img-top" alt="${product.image}">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">
                            Id: ${product.id};
                            Category: ${product.category.name};
                            Price: ${product.price};
                            Description: ${product.description}<br>
                        </p>
                        <a href="/products/${product.id}" class="btn btn-primary">Details</a>
                        <a href="#" class="btn btn-primary">Buy</a>
                    </div>
                </div>
            </div>
        </#list>
    </div>

    <#include "partials/pagination.ftlh">

    <div class="mb-5">

    </div>

</@main.renderWith>