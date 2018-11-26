
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="/js/jquery.1.12.4.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="/bootstrap3.3.7/js/bootstrap.min.js"></script>
<!--回到顶部的2个js-->
<script type="text/javascript" src="/js/jquery.goup.js"></script>
<script type="text/javascript" src="/js/totop.js"></script>
<script>
    //设置导航栏按钮激活
    jQuery(function () {
        var pageTable1 = '${pageTable1!""}';
        jQuery("#" + pageTable1).attr("class", "active");
    });

    function getPath() {
        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht = curWwwPath.substring(0, pos);
        //获取带"/"的项目名，如：/uimcardprj
        //var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPaht);
    }
</script>