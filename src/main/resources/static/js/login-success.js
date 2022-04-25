'use strict';

const customerKey = 'OnlineShopDmitriyPak-customer';
const element = document.getElementById('customer-info');
const email = element.getAttribute('data-customer-email');
localStorage.setItem(customerKey, email);