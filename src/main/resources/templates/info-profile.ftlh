<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3">${header}</h1>
    <br>
    <h4>Registration data</h4>
    <p>Id: ${customer.getId()}</p>
    <p>Email: ${customer.getEmail()}</p>
    <p>Full name: ${customer.getFullname()}</p>
    <br>
    <p>Please <a class="as-button" href="/login">login</a> to purchase our products</p>

</@main.renderWith>