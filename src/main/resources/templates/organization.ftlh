<#import "components/macros.ftlh" as macros />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <@macros.header />
    <script src="<@macros.spring.url '/js/product.js' />"></script>
    <script src="<@macros.spring.url '/js/organization.js' />"></script>
    <title>Bank</title>
</head>
<body>
<@macros.navbar activeIndex=1 />
<h2>Organizations</h2>
<div class="table-responsive">
    <table class="table table-primary table-hover">
        <tr>
            <th></th>
            <th>#ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Country Code</th>
        </tr>
        <#list list as singleBank>
        <tr>
            <td onclick="getProducts(this, [<#list singleBank.products as prod>'${prod}',</#list>])">
                <button class="btn btn-primary">&downarrow;</button>
            </td>
            <td>${singleBank.innerId}</td>
            <td>${singleBank.name}</td>
            <td>${singleBank.address}</td>
            <td>${singleBank.countryCode}</td>
        </tr>
        <tr class="hidden-details">
            <td colspan="5">
                <h3>Products</h3>
                <table class="table table-striped table-info w-75">
                </table>
                </td>
        </tr>
    </#list>
    </table>

</div>
</body>
</html>