<script type="text/javascript" src="/js/base.js"></script>
<script>
    jQuery(function () {
        var pageTable1 = '${pageTable1!""}';
        var pageTable2 = '${pageTable2!""}';
        jQuery("#" + pageTable1).attr("class", "active open");
        jQuery("#" + pageTable2).attr("class", "active");
    });
</script>