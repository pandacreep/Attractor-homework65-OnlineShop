'use strict';

const fetchItems = async (url) => {
    const data = await fetch(url, {cache: 'no-cache'});
    return data.json();
};

function getUrl(e) {
    const page = document.getElementById('searchButton').getAttribute('data-page');
    let sName = document.getElementById('searchName').value;
    let sDescription = document.getElementById('searchDescription').value;
    let sPriceFrom = document.getElementById('searchPriceFrom').value * 1;
    let sPriceTo = document.getElementById('searchPriceTo').value * 1;
    let sCategory = document.getElementById('searchCategory').value;

    let url = `${serverPath}/products/searchadvance`;
    url += `?name=${sName}`;
    url += `&description=${sDescription}`;
    url += `&priceFrom=${sPriceFrom}`;
    url += `&priceTo=${sPriceTo}`;
    url += `&category=${sCategory}`;
    url += `&page=${page}&size=4`;
    return url;
}

function setNextPageNumber() {
    let page = document.getElementById('searchButton').getAttribute('data-page');
    page = page * 1 + 1;
    document.getElementById('searchButton').setAttribute('data-page', page);
}

async function showResults(e) {
    e.stopPropagation();
    e.preventDefault();
    const url = getUrl(e);
    const dataReceived = await fetchItems(url);
    const list = document.getElementById('searchResult');
    for (let product of dataReceived) {
        list.append(itemTemplate(product));
    }
    addListenerToElements();
    setNextPageNumber();
    document.getElementById('load-next').hidden = false;
}

document.getElementById('searchButton')
    .addEventListener('submit', (e) => {
        const list = document.getElementById('searchResult');
        list.innerHTML = "";
        document.getElementById('searchButton').setAttribute('data-page', 0);
        showResults(e);
    });
document.getElementById('load-next-button')
    .addEventListener('click', (e) => {
        showResults(e);
    });
