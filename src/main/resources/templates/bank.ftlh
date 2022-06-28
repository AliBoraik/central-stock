<#import "components/macros.ftlh" as macros />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank</title>
    <@macros.header/>
</head>
<body>
<@macros.navbar activeIndex=0 />
<div class="container p-1">
    <h2>Banks</h2>
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
                    <td onclick="toggleShow(this)">
                        <div class="btn btn-primary">&downarrow;</div>
                    </td>
                    <td>${singleBank.innerId}</td>
                    <td>${singleBank.name}</td>
                    <td>${singleBank.address}</td>
                    <td>${singleBank.countryCode}</td>
                </tr>
                <tr class="hidden-details">
                    <td colspan="5">
                        <table class="table table-striped table-info w-25">
<#--                            <tr>-->
<#--                                <th>Account Country</th>-->
<#--                                <th>Account Balance</th>-->
<#--                            </tr>-->
                            <#if singleBank.accounts?keys?size == 0>
                                <div class="alert alert-info" role="alert">List of accounts is empty</div>
                            <#else>
                                <#list singleBank.accounts as accountCountry, accountBalance>
                                    <tr>
                                        <td>${accountCountry}</td>
                                        <td><span class="text-black-50">${accountBalance}</span></td>
                                    </tr>
                                </#list>
                            </#if>
                        </table>
                    </td>
                </tr>
            </#list>
        </table>
    </div>
</div>
</body>
</html>