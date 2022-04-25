<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=["/js/main.js", "/js/search.js", "/js/buy.js"]>

    <h1 class="my-3">Search products</h1>
    <form id="searchButton" data-page="0">
        <div class="row mt-3">
            <div class="col-2 mx-1">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">Product name (partial):</p>
                    <input type="text" name="name" id="searchName">
                </label>
            </div>
            <div class="col-2 mx-1">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">Description: (partial)</p>
                    <input type="text" name="description" id="searchDescription">
                </label>
            </div>
            <div class="col-2 mx-1">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">Price from:</p>
                    <input type="number" name="priceFrom" value="0.0" id="searchPriceFrom">
                </label>
            </div>
            <div class="col-2 mx-1">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">Price to:</p>
                    <input type="number" name="priceTo" value="100000.0" id="searchPriceTo">
                </label>
            </div>
            <div class="col-2 mx-1">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">Category:</p>
                    <select name="category" id="searchCategory">
                        <option value="" hidden>Select category</option>
                        <option value="Phones">Phones</option>
                        <option value="Multimedia">Multimedia</option>
                        <option value="Software">Software</option>
                        <option value="Household">Household</option>
                    </select>
                </label>
            </div>
            <div class="col-1 pt-3">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
        </div>
        <#if errors?? >
            <#list errors as fieldError>
                <p class="text-danger my-0">${fieldError.defaultMessage!'-- no message --'}</p>
            </#list>
        </#if>
        <br>
        <div class="row flex-wrap" id="searchResult">
            <#list products as product>
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
        <br>
        <div class="load-next" id="load-next" hidden="true">
            <button class="btn btn-primary" type="submit" id="load-next-button">Load more</button>
        </div>
        <br>
    </form>

</@main.renderWith>