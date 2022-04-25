'use strict';

const element = document.getElementById('info');
const email = element.getAttribute('data-customer-email');
localStorage.setItem(customerKey, email);
const customer = localStorage.getItem(customerKey);
document.getElementById('clearBasket').addEventListener('click', clearBasket);
userLogged();


async function userLogged() {
    document.getElementById('basket').hidden = false;
    document.getElementById('info').hidden = true;

    const url = `${serverPath}/basket/${customer}`;
    const basket = await getRestData(url);
    let products = basket.products;
    for (let i = 0; i < products.length; i++) {
        showProductInBasket(products, i);
        document.getElementById(`qtyIncrease-${products[i].id}`)
            .addEventListener('click', quantityIncrease);
        document.getElementById(`qtyDecrease-${products[i].id}`)
            .addEventListener('click', quantityDecrease);
        document.getElementById(`delete-${products[i].id}`)
            .addEventListener('click', handleDeleteEvent);
    }
    document.getElementById('totalPrice').innerText = basket.totalPrice;
    if (basket.totalPrice > 0) {
        showOrderButton();
    }

}

function showOrderButton() {
    const order = document.createElement('div');
    const html = `
        <p>
            To complete your purchase please
            <a class="as-button" href="/order/${customer}">make order</a>
        </p>
    `;
    order.innerHTML = html;
    document.getElementById('basket').append(order);
}

async function quantityDecrease(e) {
    e.stopPropagation();
    e.preventDefault();
    const productId = e.target.getAttribute('data-product-id');
    const url = `${serverPath}/basket/${customer}/qty-decrease/${productId}`;
    const data = await getRestData(url);
    localStorage.setItem(basketKeyPrefix + customer, JSON.stringify(data));
    window.location.href = "/basket";
}

async function quantityIncrease(e) {
    e.stopPropagation();
    e.preventDefault();
    const productId = e.target.getAttribute('data-product-id');
    const url = `${serverPath}/basket/${customer}/qty-increase/${productId}`;
    const data = await getRestData(url);
    localStorage.setItem(basketKeyPrefix + customer, JSON.stringify(data));
    window.location.href = "/basket";
}

async function handleDeleteEvent(e) {
    e.stopPropagation();
    e.preventDefault();
    const productId = e.target.getAttribute('data-product-id');
    const url = `${serverPath}/basket/${customer}/delete/${productId}`;
    const data = await getRestData(url);
    localStorage.setItem(basketKeyPrefix + customer, JSON.stringify(data));
    window.location.href = "/basket";
}

function showProductInBasket(products, i) {
    const productRow = document.createElement('tr');
    productRow.innerHTML = createProductHtml(products[i]);
    document.getElementById('table').append(productRow);
}

function createProductHtml(product) {
    return `
        <td>${product.id}</td>           
        <td>${product.name}</td>           
        <td>${product.price}</td>           
        <td>${product.quantity}</td>  
        <td>
            <a class="as-button" href="" data-product-id="${product.id}" id="qtyDecrease-${product.id}">
                -
            </a>
            <a class="as-button" href="" data-product-id="${product.id}" id="qtyIncrease-${product.id}">
                +
            </a>
            <a class="as-button" href="" data-product-id="${product.id}" id="delete-${product.id}">
                Delete
            </a>
        </td>
    `;
}

async function clearBasket(e) {
    e.stopPropagation();
    e.preventDefault();
    const url = `${serverPath}/basket/${customer}/clear`
    const data = await getRestData(url);
    localStorage.setItem(basketKeyPrefix + customer, JSON.stringify(data));
    window.location.reload();
}