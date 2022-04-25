<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3">Please login</h1>
    <form action="/login" method="post">
        <div>
            <#if error!false >
                <p style="color:red">Неверные имя пользователя или пароль !</p>
            </#if>
        </div>
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="row">
            <label class="flex flex-v-center">
                <p class="label-input-text mb-1">E-mail:</p>
                <input type="text" name="username" placeholder="your email"  value="${(form.email)!''}" >
                <#if errors?? >
                    <#list errors as fieldError>
                        <#if fieldError.field == 'email'>
                            <p class="text-danger my-0">${fieldError.defaultMessage!'-- no message --'}</p>
                        </#if>
                    </#list>
                </#if>
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <p class="label-input-text mb-1">Password:</p>
                <input type="password" name="password" placeholder="your password"  value="">
                <#if errors?? >
                    <#list errors as fieldError>
                        <#if fieldError.field == 'password'>
                            <p class="text-danger my-0">${fieldError.defaultMessage!'-- no message --'}</p>
                        </#if>
                    </#list>
                </#if>
            </label>
        </div>
        <div class="mt-3">
            <button class="btn btn-primary" type="submit">Login</button>
        </div>
    </form>

<#--    <form action="/login" method="post">-->

<#--        <div>-->
<#--            <#if error!false >-->
<#--                <p style="color:red">Неверные имя пользователя или пароль !</p>-->
<#--            </#if>-->
<#--        </div>-->

<#--        <#if _csrf??>-->
<#--            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>-->
<#--        </#if>-->

<#--        <div>-->
<#--            <label class="flex flex-v-center ">-->
<#--                <span class="label-input-text">Адрес почты:</span>-->
<#--                <input type="text" name="username" placeholder="ваш адрес почты"  value="${(dto.email)!''}" autofocus >-->
<#--            </label>-->
<#--        </div>-->
<#--        <div>-->
<#--            <label class="flex flex-v-center label-input-text">-->
<#--                <span class="label-input-text">Пароль:</span>-->
<#--                <input type="text" name="password" placeholder="пароль" value="">-->
<#--            </label>-->
<#--        </div>-->
<#--        <div>-->
<#--            <button type="submit">Войти!</button>-->
<#--        </div>-->
<#--    </form>-->

</@main.renderWith>