<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3">Product detailed information</h1>
    <div class="row flex-wrap">
        <div class="col-3">
            <div class="card m-1">
                <img src="/images/products/${product.image}" class="card-img-top" alt="${product.image}">
            </div>
        </div>
    </div>
    <h5 class="card-title">${product.name}</h5>
    <p>Id: ${product.id}</p>
    <p>Price: ${product.price}</p>
    <p>Description: ${product.description}</p>
    </p>
    <a href="#" class="btn btn-primary">Buy</a>
    <div class="mb-5"></div>

</@main.renderWith>