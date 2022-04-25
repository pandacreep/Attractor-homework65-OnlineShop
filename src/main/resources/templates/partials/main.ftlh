<#macro renderWith title="Pandacreep onlineshopp" scripts=[] styles=[]>

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
        <meta id="_csrf_token" name="_csrf_token" content="${_csrf.token}"/>

        <title>${title}</title>

        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/main.css">
        <#list styles as styleSource>
            <link rel="stylesheet" href="${styleSource}">
        </#list>
    </head>
    <body>
    <header class="header">
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container container-fluid">
                <a class="d-flex justify-content-around navbar-brand" href="#">OnlineShop</a>
                <div class="d-flex justify-content-end collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/categories">Categories</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/products">Products</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/search-mvc">Search</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/basket">Basket</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/orders">Orders</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/register">Register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/logout-page">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <div class="container">
        <#nested>
    </div>

    <#list scripts as scriptSource>
        <script src="${scriptSource}"></script>
    </#list>

    </body>
    </html>

</#macro>