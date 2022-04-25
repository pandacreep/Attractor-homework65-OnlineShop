<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=["/js/main.js", "/js/basket.js"]>

    <h1 class="my-3 text-center">Basket of client with email: ${email}</h1>
    <div id="info" data-customer-email="${email}"></div>
    <div id="basket">
        <p>You can add other products found in other menu</p>
        <p>You can <a class="as-button" href="" id="clearBasket">clear</a> your basket</p>
        <br>
        <table id="table">
            <tr>
                <th>ItemId</th>
                <th>Item Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
        </table>
        <br>
        <p>Total price: <b id="totalPrice"></b></p>
        <br>
    </div>

</@main.renderWith>