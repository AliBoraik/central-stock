<#import "components/macros.ftlh" as macros />
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <@macros.header />
    <title>Document</title>
</head>
<body>
<@macros.navbar activeIndex=2 />
<h2>Contracts</h2>
<div class="table-responsive">
    <table class="table table-striped table-bordered">
        <tr>
            <th>Contract ID</th>
            <th>Contract Buyer</th>
            <th>Contract Created At</th>
            <th>Contract Count</th>
            <th>Paid?</th>
            <th>Contract Payment Date</th>
            <th>Product</th>
        </tr>
        <#list contracts.content as contract>
            <tr>
                <td>${contract.contractId}</td>
                <td>${contract.buyer.name}</td>
                <td>${contract.createdAt}</td>
                <td>${contract.count}</td>
                <td>
                    <#if contract.isPaid>
                        <div class="green chip">
                            YES
                        </div>
                    <#else>
                        <div class="red chip">
                            NO
                        </div>
                    </#if>
                </td>
                <td>${contract.paymentDate!}</td>
                <td>${contract.productId}</td>
            </tr>
        </#list>
    </table>
</div>
<nav>
    <ul class="pagination pagination-lg justify-content-end">
        <li class="page-item <#if contracts.currentPage <= 1>disabled</#if>">
            <a class="page-link" href="<@macros.spring.url '/admin/contract?page=${contracts.currentPage-1}'/>"
               aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
            </a>
        </li>
        <#list 1..contracts.totalPages as i>
            <#if i == contracts.currentPage>
                <li class="page-item active">
                    <span class="page-link">
                        ${i}
                    </span>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link" href="<@macros.spring.url '/admin/contract?page=${i}'/>">${i}</a>
                </li>
            </#if>


        </#list>
        <li class="page-item <#if contracts.currentPage gte contracts.totalPages>disabled</#if>">
            <a class="page-link" href="<@macros.spring.url '/admin/contract?page=${contracts.currentPage+1}'/>"
               aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
            </a>
        </li>
    </ul>
</nav>
</body>
</html>