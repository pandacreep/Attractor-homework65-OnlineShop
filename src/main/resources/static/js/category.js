'use strict';

const categoryHeader = document.getElementById('category-header');
const categoryId = categoryHeader.getAttribute('data-category-id');
const totalNumberOfItems = categoryHeader.getAttribute('data-total-count');
const pageSize = document.getElementById('loadNext').getAttribute('data-default-page-size');
let numberOfPages = Math.ceil(totalNumberOfItems / pageSize);

const fetchItems = async (page, size) => {
    const itemsPath = `${serverPath}/categories/${categoryId}/products?page=${page}&size=${size}`;
    const data = await fetch(itemsPath, {cache: 'no-cache'});
    return data.json();
};

const loadNextItemsGenerator = (loadNextElement, page) => {
    return async (event) => {
        event.preventDefault();
        event.stopPropagation();
        const defaultPageSize = loadNextElement.getAttribute('data-default-page-size');
        const data = await fetchItems(page, defaultPageSize);
        if (data.length === 0) {
            return;
        }

        const list = document.getElementById('itemList');
        list.innerHTML = "";
        for (let product of data) {
            list.append(itemTemplate(product));
        }

        const buyElements = document.getElementsByClassName('buy');
        for (let i = 0; i < buyElements.length; i++) {
            buyElements[i].addEventListener('click', handleBuyEvent());
        }

        console.log('page: ' + page);
        console.log('numberOfPages: ' + numberOfPages);

        if (page < numberOfPages - 1) {
            loadNextElement.hidden = false;
            loadNextElement.addEventListener('click',
                loadNextItemsGenerator(loadNextElement, page + 1), {once: true});
        } else {
            loadNextElement.hidden = true;
            loadNextElement.addEventListener('click',
                loadNextItemsGenerator(loadNextElement, page), {once: true});
        }
        if (page > 0) {
            loadPrevElement.hidden = false;
            loadPrevElement.addEventListener('click',
                loadNextItemsGenerator(loadNextElement, page - 1), {once: true});
        } else {
            loadPrevElement.hidden = true;
            loadPrevElement.addEventListener('click',
                loadNextItemsGenerator(loadNextElement, page), {once: true});
        }

        window.scrollTo(0, document.body.scrollHeight);
    };
};

const loadNextElement = document.getElementById('loadNext');
const loadPrevElement = document.getElementById('loadPrev');
loadPrevElement.hidden = true;
if (loadNextElement !== null) {
    loadNextElement.addEventListener('click',
        loadNextItemsGenerator(loadNextElement, getCurrentPage()), {once: true});
}

