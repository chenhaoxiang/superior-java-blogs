$(document).ready(function () {
    $.goup({
        trigger: 400, //向下滚动多少像素后显示按钮
        containerColor: '#ccc',//按钮的颜色
        arrowColor: '#000', //中间箭头的颜色
        bottomOffset: 100, //距底部偏移量
        entryAnimation: "slide",//图标出现的方式
        goupSpeed: 'slow', //速度
        locationOffset: 25, //距右部偏移量
        title: '回到顶部', //鼠标悬浮在按钮时显示的标题
        titleAsText: true, //是否显示title
        titleAsTextClass: 'top-title' //title的样式名
    });
});