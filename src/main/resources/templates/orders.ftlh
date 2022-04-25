<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3 text-center">Orders of client with email: ${email}</h1>
    <div id="info" data-customer-email="${email}"></div>
    <div>
        <table id="table">
            <tr>
                <th>Order id</th>
                <th>Email</th>
                <th>Date/Time</th>
                <th>Receiver of order</th>
                <th>Phone of receiver</th>
                <th>Address</th>
                <th>Payment type</th>
                <th>Amount</th>
            </tr>
            <#list orders as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.email}</td>
                <td>${order.dateTime}</td>
                <td>${order.orderReceiverName}</td>
                <td>${order.phoneNumber}</td>
                <td>${order.address}</td>
                <td>${order.paymentType}</td>
                <td>${order.amount}</td>
            </tr>
            </#list>
        </table>
        <br>
    </div>

</@main.renderWith>