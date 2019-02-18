<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Tables - Ace Admin</title>

		<#include "../include/head.ftl" />
</head>

<body class="no-skin">

		<#include "../include/navbar.ftl"/>
<div class="main-container ace-save-state" id="main-container">

			<#include "../include/sidebar.ftl"/>
    <div class="main-content">
        <div class="main-content-inner">

        <#include "../include/breadcrumbs.ftl"/>
            <div class="page-content">

                <#include "../include/ace-settings-container.ftl"/>

                <div class="page-header">
                    <h1>
                        Tables
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            &amp; 博客列表
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">

                        <div class="row">
                            <div class="col-xs-12">

                                <div class="clearfix">
                                    <div class="pull-right tableTools-container"></div>
                                </div>
                                <div class="table-header">
                                    Results for "Latest Registered Domains"
                                </div>

                                <!-- div.table-responsive -->

                                <!-- div.dataTables_borderWrap -->
                                <table style="word-break:break-all; word-wrap:break-all;" id="dynamic-table"
                                       class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class="center">
                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace"/>
                                                <span class="lbl"></span>
                                            </label>
                                        </th>
                                        <th>标题</th>
                                        <th>作者</th>
                                        <th class="hidden-480">原文链接</th>
                                        <th>浏览数</th>
                                        <th>评论数</th>
                                        <th>被顶数</th>
                                        <th>被收藏</th>
                                        <th>
                                            <i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>
                                            创建时间
                                        </th>
                                        <th>
                                            原创标识
                                        </th>
                                        <th class="hidden-480">状态</th>

                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#if blogsList??>
                                        <#list blogsList as blogs>
                                    <tr>
                                        <td class="center">
                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace"/>
                                                <span class="lbl"></span>
                                            </label>
                                        </td>

                                        <td>
                                            <a href="#">${blogs.title!""}</a>
                                        </td>
                                        <td>${blogs.author!""}</td>
                                        <td>
                                            <a href="${blogs.originalUrl!""}"
                                               target="_blank">${blogs.originalUrl!""}</a>
                                        </td>
                                        <td>${blogs.viewCount!"0"}</td>
                                        <td>${blogs.commentCount!"0"}</td>
                                        <td>${blogs.topCount!"0"}</td>
                                        <td>${blogs.collectCount!"0"}</td>
                                        <td>
                                            ${blogs.createTime?string('yyyy-MM-dd HH:mm:ss')}
                                        </td>
                                        <td>
                                            ${blogs.original!""}
                                        </td>
                                        <td>
                                            <#if blogs.status==0>
                                                <button class="btn btn-sm btn-danger"
                                                        onclick="updateStatus(this,'${blogs.id}','1')">
                                                    正常
                                                </button>
                                            <#elseif blogs.status==1>
                                            <button class="btn btn-sm btn-success"
                                                    onclick="updateStatus(this,'${blogs.id}','0')">已被删除
                                            </button>
                                            <#elseif blogs.status==2>
                                            <button class="btn btn-sm btn-info"
                                                    onclick="updateStatus(this,'${blogs.id}','0')">待审核
                                            </button>
                                            <#else>
                                                未知
                                            </#if>

                                        </td>

                                        <td>
                                            <div class="hidden-sm hidden-xs action-buttons">
                                            <#--查看-->
                                                <a class="blue" href="#">
                                                    <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                                </a>
                                            <#--修改-->
                                                <a class="green" href="#">
                                                    <i class="ace-icon fa fa-pencil bigger-130"></i>
                                                </a>
                                            <#--删除-->
                                                <a class="red" href="#">
                                                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                                </a>
                                            </div>

                                            <div class="hidden-md hidden-lg">
                                                <div class="inline pos-rel">
                                                    <button class="btn btn-minier btn-yellow dropdown-toggle"
                                                            data-toggle="dropdown" data-position="auto">
                                                        <i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
                                                    </button>

                                                    <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                        <li>
                                                        <#--查看-->
                                                            <a href="#" class="tooltip-info" data-rel="tooltip"
                                                               title="View">
                                                                            <span class="blue">
                                                                                <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                                                            </span>
                                                            </a>
                                                        </li>

                                                        <li>
                                                        <#--修改-->
                                                            <a href="#" class="tooltip-success" data-rel="tooltip"
                                                               title="Edit">
                                                                            <span class="green">
                                                                                <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                                            </span>
                                                            </a>
                                                        </li>

                                                        <li>
                                                        <#--删除-->
                                                            <a href="#" class="tooltip-error" data-rel="tooltip"
                                                               title="Delete">
                                                                            <span class="red">
                                                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                                            </span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </td>

                                    </tr>
                                        </#list>
                                    </#if>
                                    </tbody>
                                </table>

                            </div>
                        </div>

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->


			<#include  "../include/footer.ftl"/>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="/assets/js/jquery-2.1.4.min.js"></script>
<!-- <![endif]-->

<!--[if IE]>
<script src="/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="/assets/js/jquery.dataTables.min.js"></script>
<script src="/assets/js/jquery.dataTables.bootstrap.min.js"></script>
<script src="/assets/js/dataTables.buttons.min.js"></script>
<script src="/assets/js/buttons.flash.min.js"></script>
<script src="/assets/js/buttons.html5.min.js"></script>
<script src="/assets/js/buttons.print.min.js"></script>
<script src="/assets/js/buttons.colVis.min.js"></script>
<script src="/assets/js/dataTables.select.min.js"></script>

<!-- ace scripts -->
<script src="/assets/js/ace-elements.min.js"></script>
<script src="/assets/js/ace.min.js"></script>

<#include  "../include/base-js.ftl"/>

<!-- inline scripts related to this page -->
<script type="text/javascript" src="/js/admin/blog-insert.js"></script>
<#--<script type="text/javascript" src="/js/admin/blog-list.js"/>-->
<script>
    var updateStatusUrl = "/admin/blogs/update/status";

    function updateStatus(obj, id, status) {
        var par = [id, obj];
        ajaxPost(updateStatusUrl, "id=" + id + "&status=" + status, updateStatusReturn, false, par);
    }

    var cleanLocalStorage = '${cleanLocalStorage!'0'}';

    /**
     * 清除保存的博客数据
     */
    function cleanLocalStorageUtil() {
        if (cleanLocalStorage == '1') {
            //清除博客的缓存
            cleanMarkDownRemoveItem();
        }
    }

    function updateStatusReturn(response, par) {
        var id = par[0];
        var obj = par[1];
        console.log("response=", response);
        if (response.code == 200) {
            //成功
            $(obj).removeClass();
            if (response.data == 1) {
                $(obj).addClass("btn btn-sm btn-success");
                $(obj).attr("onclick", "updateStatus(this,'" + id + "','0')");
                $(obj).text("已被删除");
            } else if (response.data == 0) {
                $(obj).addClass("btn btn-sm btn-danger");
                $(obj).attr("onclick", "updateStatus(this,'" + id + "','1')");
                $(obj).text("正常");
            } else if (response.data == 2) {
                $(obj).addClass("btn btn-sm btn-info");
                $(obj).attr("onclick", "updateStatus(this,'" + id + "','0')");
                $(obj).text("待审核");
            }
        } else {
            confirm("", "", "请求提示", "请求失败，异常信息:" + response)
        }
    }

    jQuery(function (jQuery) {
        cleanLocalStorageUtil();
        //initiate dataTables plugin
        var myTable =
                jQuery('#dynamic-table').DataTable({
                    bAutoWidth: false,
                    //注意列数和表格的列数对上！！！！
                    "aoColumns": [
                        {"bSortable": false},
                        null, null, null, null, null, null, null, null, null, null,
                        {"bSortable": false}
                    ],
                    "aaSorting": [],

                    //"bProcessing": true,
                    //"bServerSide": true,
                    //"sAjaxSource": "http://127.0.0.1/table.php"	,

                    //,
                    //"sScrollY": "200px",
                    //"bPaginate": false,

                    //"sScrollX": "100%",
                    //"sScrollXInner": "120%",
                    //"bScrollCollapse": true,
                    //Note: if you are applying horizontal scrolling (sScrollX) on a ".table-bordered"
                    //you may want to wrap the table inside a "div.dataTables_borderWrap" element

                    //"iDisplayLength": 50


                    select: {
                        style: 'multi'
                    }
                });


        $.fn.dataTable.Buttons.defaults.dom.container.className = 'dt-buttons btn-overlap btn-group btn-overlap';

        new $.fn.dataTable.Buttons(myTable, {
            buttons: [
                {
                    "extend": "colvis",
                    "text": "<i class='fa fa-search bigger-110 blue'></i> <span class='hidden'>Show/hide columns</span>",
                    "className": "btn btn-white btn-primary btn-bold",
                    columns: ':not(:first):not(:last)'
                },
                {
                    "extend": "copy",
                    "text": "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>Copy to clipboard</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "csv",
                    "text": "<i class='fa fa-database bigger-110 orange'></i> <span class='hidden'>Export to CSV</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "excel",
                    "text": "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>Export to Excel</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "pdf",
                    "text": "<i class='fa fa-file-pdf-o bigger-110 red'></i> <span class='hidden'>Export to PDF</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "print",
                    "text": "<i class='fa fa-print bigger-110 grey'></i> <span class='hidden'>Print</span>",
                    "className": "btn btn-white btn-primary btn-bold",
                    autoPrint: false,
                    message: 'This print was produced using the Print button for DataTables'
                }
            ]
        });
        myTable.buttons().container().appendTo($('.tableTools-container'));

        //style the message box
        var defaultCopyAction = myTable.button(1).action();
        myTable.button(1).action(function (e, dt, button, config) {
            defaultCopyAction(e, dt, button, config);
            $('.dt-button-info').addClass('gritter-item-wrapper gritter-info gritter-center white');
        });


        var defaultColvisAction = myTable.button(0).action();
        myTable.button(0).action(function (e, dt, button, config) {

            defaultColvisAction(e, dt, button, config);


            if ($('.dt-button-collection > .dropdown-menu').length == 0) {
                $('.dt-button-collection')
                        .wrapInner('<ul class="dropdown-menu dropdown-light dropdown-caret dropdown-caret" />')
                        .find('a').attr('href', '#').wrap("<li />")
            }
            $('.dt-button-collection').appendTo('.tableTools-container .dt-buttons')
        });

        ////

        setTimeout(function () {
            $($('.tableTools-container')).find('a.dt-button').each(function () {
                var div = $(this).find(' > div').first();
                if (div.length == 1) div.tooltip({container: 'body', title: div.parent().text()});
                else $(this).tooltip({container: 'body', title: $(this).text()});
            });
        }, 500);

        myTable.on('select', function (e, dt, type, index) {
            if (type === 'row') {
                $(myTable.row(index).node()).find('input:checkbox').prop('checked', true);
            }
        });
        myTable.on('deselect', function (e, dt, type, index) {
            if (type === 'row') {
                $(myTable.row(index).node()).find('input:checkbox').prop('checked', false);
            }
        });

        /////////////////////////////////
        //table checkboxes
        $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);

        //select/deselect all rows according to table header checkbox
        $('#dynamic-table > thead > tr > th input[type=checkbox], #dynamic-table_wrapper input[type=checkbox]').eq(0).on('click', function () {
            var th_checked = this.checked;//checkbox inside "TH" table header

            $('#dynamic-table').find('tbody > tr').each(function () {
                var row = this;
                if (th_checked) myTable.row(row).select();
                else myTable.row(row).deselect();
            });
        });

        //select/deselect a row when the checkbox is checked/unchecked
        $('#dynamic-table').on('click', 'td input[type=checkbox]', function () {
            var row = $(this).closest('tr').get(0);
            if (this.checked) myTable.row(row).deselect();
            else myTable.row(row).select();
        });


        $(document).on('click', '#dynamic-table .dropdown-toggle', function (e) {
            e.stopImmediatePropagation();
            e.stopPropagation();
            e.preventDefault();
        });


        //And for the first simple table, which doesn't have TableTools or dataTables
        //select/deselect all rows according to table header checkbox
        var active_class = 'active';
        $('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function () {
            var th_checked = this.checked;//checkbox inside "TH" table header

            $(this).closest('table').find('tbody > tr').each(function () {
                var row = this;
                if (th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
                else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
            });
        });

        //select/deselect a row when the checkbox is checked/unchecked
        $('#simple-table').on('click', 'td input[type=checkbox]', function () {
            var $row = $(this).closest('tr');
            if ($row.is('.detail-row ')) return;
            if (this.checked) $row.addClass(active_class);
            else $row.removeClass(active_class);
        });


        /********************************/
        //add tooltip for small view action buttons in dropdown menu
        $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});

        //tooltip placement on right or left
        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('table')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            //var w2 = $source.width();

            if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
            return 'left';
        }


        /***************/
        $('.show-details-btn').on('click', function (e) {
            e.preventDefault();
            $(this).closest('tr').next().toggleClass('open');
            $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
        });
        /***************/


        /**
         //add horizontal scrollbars to a simple table
         $('#simple-table').css({'width':'2000px', 'max-width': 'none'}).wrap('<div style="width: 1000px;" />').parent().ace_scroll(
         {
           horizontal: true,
           styleClass: 'scroll-top scroll-dark scroll-visible',//show the scrollbars on top(default is bottom)
           size: 2000,
           mouseWheelLock: true
         }
         ).css('padding-top', '12px');
         */


    })

</script>
</body>
</html>
