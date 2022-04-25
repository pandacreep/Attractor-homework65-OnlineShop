<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3 text-center">Order creation for ${email}</h1>
    <form action="/addOrder" method="post" id="orderButton">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <input type="text" name="email" value="${email}" hidden>
                <span class="label-input-text mb-1">E-mail: </span>
                <input type="text" value="${email}" disabled>
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <input type="text" name="amount" value="${amount}" hidden>
                <span class="label-input-text mb-1">Total amount: </span>
                <input type="text" value="${amount}" disabled>
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <span class="label-input-text mb-1">Order date: </span>
                <input type="datetime-local" name="dateTime">
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <span class="label-input-text mb-1">Receiver name: </span>
                <input type="text" name="orderReceiverName">
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <span class="label-input-text mb-1">Receiver phone: </span>
                <input type="text" name="phoneNumber">
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <span class="label-input-text mb-1">Receiver address: </span>
                <input type="text" name="address">
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <span class="label-input-text mb-1">Payment type: </span>
                <input type="text" name="paymentType">
            </label>
        </div>
        <div class="mt-3">
            <button class="btn btn-primary" type="submit">Create order</button>
        </div>
    </form>
</@main.renderWith>