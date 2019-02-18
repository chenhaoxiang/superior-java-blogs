<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#bs-example-navbar-collapse-1"><span
                class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                class="icon-bar"></span><span class="icon-bar"></span></button>
        <a class="navbar-brand" href="/">HJ</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li id="indexLi">
                <a href="/">首页</a>
            </li>
            <li id="blogsLi">
                <a href="/blogs/0/1">博客</a>
            </li id="tagsLi">
            <li>
                <a href="#">标签云</a>
            </li>
            <li id="blogsRankLi">
                <a href="/blogs-rank/view_count">排行</a>
            </li>
            <li>
                <a href="#">问答</a>
            </li>
            <li>
                <a href="#">商城</a>
            </li>
            <li>
                <a href="#">招聘</a>
            </li>
        <#--<li class="dropdown">-->
        <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong-->
        <#--class="caret"></strong></a>-->
        <#--<ul class="dropdown-menu">-->
        <#--<li>-->
        <#--<a href="#">Action</a>-->
        <#--</li>-->
        <#--<li>-->
        <#--<a href="#">Another action</a>-->
        <#--</li>-->
        <#--<li>-->
        <#--<a href="#">Something else here</a>-->
        <#--</li>-->
        <#--<li class="divider">-->
        <#--</li>-->
        <#--<li>-->
        <#--<a href="#">Separated link</a>-->
        <#--</li>-->
        <#--<li class="divider">-->
        <#--</li>-->
        <#--<li>-->
        <#--<a href="#">One more separated link</a>-->
        <#--</li>-->
        <#--</ul>-->
        <#--</li>-->
        </ul>
        <form class="navbar-form navbar-left" action="/blogs/search/0" role="search">
            <div class="form-group">
                <input name="searchTitle" type="text" id="search-text" value="${searchTitle!""}" class="form-control"/>
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="/login">登录</a>
            </li>
            <li>
                <a href="/register">注册即</a>
            </li>
        <#--<li>-->
        <#--<a href="#">注册</a>-->
        <#--</li>-->
        <#--<li class="dropdown">-->
        <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">个人中心<strong-->
        <#--class="caret"></strong></a>-->
        <#--<ul class="dropdown-menu">-->
        <#--<li>-->
        <#--<a href="#">Action</a>-->
        <#--</li>-->
        <#--<li>-->
        <#--<a href="#">Another action</a>-->
        <#--</li>-->
        <#--<li>-->
        <#--<a href="#">Something else here</a>-->
        <#--</li>-->
        <#--<li class="divider">-->
        <#--</li>-->
        <#--<li>-->
        <#--<a href="#">Separated link</a>-->
        <#--</li>-->
        <#--</ul>-->
        <#--</li>-->
        </ul>
    </div>

</nav>
