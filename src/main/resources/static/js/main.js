'use strict';

const serverPath = 'http://localhost:8000/api';
const customerKey = 'OnlineShopDmitriyPak-customer';
const basketKeyPrefix = 'OnlineShopDmitriyPak-basket-';

const getCurrentPage = () => {
    const loc = (typeof window.location !== 'string') ? window.location.search : window.location;
    const index = loc.indexOf('page=');
    return index === -1 ? 1 : parseInt(loc[index + 5]) + 1;
};

async function fetchAddItem(url) {
    const data = await fetch(url, {
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
            '_csrf': document.getElementById("_csrf_token").getAttribute("content")
        },
        cache: 'no-cache'});
    return data.json();
};

async function getRestData(url) {
    const data = await fetch(url, {cache: 'no-cache'});
    return data.json();
};

const itemTemplate = (product) => {
    const template = `
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
    `;
    const elem = document.createElement('div');
    elem.innerHTML = template.trim();

    return elem.children[0];
};

function handleBuyEvent() {
    return async (e) => {
        e.stopPropagation();
        e.preventDefault();
        const productId = e.target.getAttribute('data-product-id');
        if (customer == null) {
            console.log("Please login!");
        } else {
            const itemsPath = `${serverPath}/basket/${customer}/add/${productId}`;
            const data = await fetchAddItem(itemsPath);
            localStorage.setItem(basketKeyPrefix + customer, JSON.stringify(data));
            window.location.href = "/basket";
        }
    };
}

function addListenerToElements() {
    const buyElements = document.getElementsByClassName('buy');
    for (let i = 0; i < buyElements.length; i++) {
        if (!buyElements[i].classList.contains('hasHandler')) {
            buyElements[i].addEventListener('click', handleBuyEvent());
        }
        buyElements[i].classList.add('hasHandler');
    }
}