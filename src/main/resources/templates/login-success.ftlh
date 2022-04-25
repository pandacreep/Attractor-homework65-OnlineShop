<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=["js/login-success.js"]>

    <h1 class="my-3">Successful login</h1>
    <br id="customer-info" data-customer-email="${customer.getEmail()}">
    <h4>Registration data</h4>
    <p>Id: ${customer.getId()}</p>
    <p>Email: ${customer.getEmail()}</p>
    <p>Full name: ${customer.getFullname()}</p>
    <br>
    <h4>Now you can purchase our products</h4>

</@main.renderWith>