<#import "/spring.ftl" as spring />
<#macro navbar activeIndex>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="container-fluid">
            <p class="navbar-brand">Central Stock Admin</p>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="<@spring.url '/admin/bank'/>" class="nav-link <#if activeIndex==0>disabled</#if>">Bank</a>
                    </li>
                    <li class="nav-item">
                        <a href="<@spring.url '/admin/organization'/>" class="nav-link <#if activeIndex==1>disabled</#if>">Organization</a>
                    </li>
                    <li class="nav-item">
                        <a href="<@spring.url '/admin/contract'/>" class="nav-link <#if activeIndex==2>disabled</#if>">Contract</a>
                    </li>
                    <li class="nav-item">
                        <a href="<@spring.url '/admin/product' />" class="nav-link <#if activeIndex==3>disabled</#if>">Product</a>
                    </li>
                    <li class="nav-item">

                    </li>
                </ul>
            </div>
            <div class="navbar-nav">
                <div class="nav-item text-nowrap">
                    <a class="nav-link px-3" href="<@spring.url '/logout'/>">Sign out</a>
                </div>
            </div>
        </div>
    </nav>
</#macro>

<#macro header>
    <link href="<@spring.url '/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<@spring.url '/css/styles.css' />" rel="stylesheet">
    <script src="<@spring.url '/js/index.js' />"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>
</#macro>