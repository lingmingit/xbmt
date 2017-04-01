/*'use strict';*/
var xbmtCommon = angular.module('xbmtCommon', []);



/****
   
    BaseURL【http://t.xbmt.net\\:8000】中的转义字符，  $resource 可以正常使用，$http不能使用
    
*************/
xbmtCommon.constant('APIURL', {
    BaseURL: 'http://127.0.0.1:8080', //接口api url地址
    BaseAPIImgURL:'http://127.0.0.1:8080' ,//接口中上传的图片url地址
    BaseImageURL: 'http://127.0.0.1:8080',//后台管理图片url地址
});


/***
 * 定义权限级别常量
 * pub:1 公开的 不需要登录
 * user:2 保护的 需要用户登录
 */
xbmtCommon.constant('ACCESS_LEVELS', {
    pub:1,
    user:2
});

