'use strict';

const fetchItems = async (page, size) => {
    const itemsPath = `${serverPath}/products?page=${page}&size=${size}`;
    const data = await fetch(itemsPath, {cache: 'no-cache'});
    return data.json();
};

const loadNextItemsGenerator = (loadNextElement, page) => {
    return async (event) => {
        event.preventDefault();
        event.stopPropagation();

        const defaultPageSize = loadNextElement.getAttribute('data-default-page-size');
        const data = await fetchItems(page, defaultPageSize);
        loadNextElement.hidden = data.length === 0;
        if (data.length === 0) {
            return;
        }

        const list = document.getElementById('itemList');
        for (let product of data) {
            list.append(itemTemplate(product));
        }
        addListenerToElements();

        loadNextElement.addEventListener('click',
            loadNextItemsGenerator(loadNextElement, page + 1), {once: true});
        window.scrollTo(0, document.body.scrollHeight);
    };
};

document.getElementById('loadPrev').hidden = true;
const loadNextElement = document.getElementById('loadNext');
if (loadNextElement !== null) {
    loadNextElement.innerText = "Load more products";
    loadNextElement.addEventListener('click',
        loadNextItemsGenerator(loadNextElement, getCurrentPage()), {once: true});
}