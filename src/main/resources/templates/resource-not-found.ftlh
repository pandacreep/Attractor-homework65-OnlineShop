<#import "partials/main.ftlh" as main />

<@main.renderWith>

    <h1 class="my-3">Error!</h1>
    <p>
        Resource ${resource} with id ${id} not found!
    </p>

</@main.renderWith>