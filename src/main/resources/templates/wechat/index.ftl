<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../include/head.ftl"/>
    <title>huijava</title>
</head>

<body>

<div class="container-fluid" style="text-align: center">
    <div class="row-fluid">
        <div class="span12">
            <img alt="${wxUsersDTO.nickname!''}" src="${wxUsersDTO.headimgurl!''}" class="img-circle"/>
            <p>${wxUsersDTO.nickname !''}</p>
            <div class="row-fluid">
                <div class="span4">
                    <span class="label badge-success">已领红包:${redPacket.times}个</span>
                </div>
                <div class="span4">
                    <span class="label badge-warning">可领红包:${redPacket.maxTimes}个</span>
                </div>
                <div class="span4">
                    <span class="label badge-important">领取金额:${redPacket.sumMoney}</span>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <button class="btn btn-primary btn-block btn-large" type="button">领红包</button>
                </div>
                <div class="span4">
                    <button class="btn btn-info btn-block btn-large" type="button">我的信息</button>
                </div>
                <div class="span4">
                    <button class="btn btn-block btn-large btn-success" type="button">邀请</button>
                </div>
            </div>
            <div class="page-header">
                <h1>
                    实时获奖名单(人数:${})
                </h1>
            </div>
            <table class="table table-condensed table-hover table-bordered">
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