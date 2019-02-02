<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../include/head.ftl"/>
    <title>我的信息</title>
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

                    <a href="/wechat/userInfo?openid=${wxUsersDTO.openid}" type="button"
                       class="btn btn-default btn-info">首页</a>
                    <a href="/wechat/myInfo" style="margin-left: 2px;margin-right: 2px;" type="button"
                       class="btn btn-warning">个人信息</a>
                    <a href="/wechat/invitation" type="button" class="btn btn-success">邀请</a>
                </div>
            </div>

            <div class="row clearfix" style="margin-top: 5px;">
                <div class="col-md-12 column">
                    <a href="/wechat/getRedPacket?getRedPacket=1" style="margin-left: 2px;margin-right: 2px;"
                       type="button" class="btn btn-warning">获取支付宝口令红包</a>
                </div>
            </div>

            <#if message??>
                <div style="color: red">
                    <p>${message}</p>
                </div>
            </#if>

            <div class="page-header">
                <h1>
                    我的红包(${sumMoney})
                </h1>
            </div>
            <table class="table table-condensed table-hover table-bordered"
                   style="word-break:break-all; word-wrap:break-all;">
                <thead>
                <tr>
                    <th>
                        红包口令
                    </th>
                    <th>
                        金额
                    </th>
                    <th>
                        领取时间
                    </th>
                    <th>
                        时间戳
                    </th>
                </tr>
                </thead>
                <tbody>

                <#if redPacketDetailsDTOList??>
                    <#list redPacketDetailsDTOList as redPacketDetailsDTO>
                        <tr>
                            <td>
                                ${redPacketDetailsDTO.password}
                            </td>
                            <td>
                                ${redPacketDetailsDTO.money}
                            </td>
                            <td>
                                ${redPacketDetailsDTO.createTime?string('yyyy-MM-dd HH:mm:ss')}
                            </td>
                            <td>
                                ${redPacketDetailsDTO.getTime}
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