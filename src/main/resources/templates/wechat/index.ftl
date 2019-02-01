<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../include/head.ftl"/>
    <title>huijava</title>
</head>

<body>

<div class="container-fluid container" style="text-align: center">
    <div class="row-fluid row">
        <div class="span12">
            <img alt="${wxUsersDTO.nickname!''}" src="${wxUsersDTO.headimgurl!''}" class="img-circle"/>
            <p>${wxUsersDTO.nickname !''}</p>

            <div class="row clearfix">
                <div class="col-md-12 column">
                    <span class="label label-primary">已领红包:${redPacket.times}个</span>
                    <span style="margin-left: 2px;margin-right: 2px;"
                          class="label label-success">可领红包:${redPacket.maxTimes}个</span>
                    <span class="label label-info">领取金额:${redPacket.sumMoney}元</span>
                </div>
            </div>

            <div class="row clearfix" style="margin-top: 5px;">
                <div class="col-md-12 column">
                    <a href="/wechat/getRedPacket" type="button" class="btn btn-default btn-info">领红包</a>
                    <a href="/wechat/myInfo" style="margin-left: 2px;margin-right: 2px;" type="button"
                       class="btn btn-warning">我的信息</a>
                    <a href="/wechat/invitation" type="button" class="btn btn-success">邀请</a>
                </div>
            </div>

            <div class="page-header">
                <h1>
                    实时获奖名单(
                    <#if redPacketDTOList??>
                        ${redPacketDTOList?size}
                    <#else >
                        0
                    </#if>
                    )
                </h1>
            </div>
            <!-- style="word-break:break-all; word-wrap:break-all;" 表格文字自动换行 -->
            <table class="table table-condensed table-hover table-bordered"
                   style="word-break:break-all; word-wrap:break-all;">
                <thead>
                <tr>
                    <th>
                        昵称
                    </th>
                    <th>
                        领取金额
                    </th>
                    <th>
                        领取时间
                    </th>
                </tr>
                </thead>
                <tbody>
                <#if redPacketDTOList??>
                    <#list redPacketDTOList as redPacketDTO>
                        <tr>
                            <td>
                                ${redPacketDTO.nickname}
                            </td>
                            <td>
                                ${redPacketDTO.sumMoney}
                            </td>
                            <td>
                                ${redPacketDTO.getTime?string('yyyy-MM-dd HH:mm:ss')}
                            </td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<#include "../include/footer.ftl"/>

<#include "../include/base-js.ftl"/>
<script>

</script>
</body>

</html>