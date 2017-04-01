/*'use strict';*/
var xbmtServices = angular.module('xbmt.services', []);





/*******
用户相关操作service定义
******/
xbmtServices.factory('UserService', ['$resource', 'APIURL' , function ($resource , APIURL) {
   var url = APIURL.BaseURL+APIURL.UserAPI+"/:cmd";
   //console.log("url="+url);
   return $resource(url, {}, {
       getToken:{method:'GET' , params:{cmd:'login'}} , 
       login: {method:'POST', params:{cmd:'login'}},
   });
}]);


/*******
 *  自己实现 token
  用户相关操作service定义
******/
xbmtServices.factory('UserService3', ['$http' , '$log', 'APIURL' , 'MD5Service' , function ($http ,$log , APIURL , MD5Service) {
	
   //获取用户信息
   var getUser = function(){
	   var url = APIURL.BaseURL+"/api/auth3/get_login_user";
	  return $http.get(url);
   };
   
   /***
    * 用户登录方法
    */
   var login = function(param){
	   var url = APIURL.BaseURL+"/api/auth3/login";
	   $log.debug("url" , url);
	   $log.debug("user.token" , param.token);
	   //头信息
	   var config = {
	   }; 
	   return $http.post(url , param, config);  
   };
   
   return{
	   getUser: getUser , 
	   login : login
   };
}]);




/*******
 *  使用HTTP 摘要认证
用户相关操作service定义
******/
xbmtServices.factory('UserService2', ['$http' , '$log', 'APIURL' , 'MD5Service' , function ($http ,$log , APIURL , MD5Service) {
	
   var realmName = "Contacts Realm via Digest Authentication";
   var qop = "auth";
   var opaque = "";
   
   var getToken = function(){
	   var url = APIURL.BaseURL+"/api/auth2/getToken";
	   $log.debug("url" , url);
	   //Digest realm="iptel.org", qop="auth,auth-int",nonce="dcd98b7102dd2f0e8b11d0f600bfb0c093", opaque="", algorithm=MD5
	   var digest = 'Digest realm="Contacts Realm via Digest Authentication", qop="auth,auth-int",nonce="dcd98b7102dd2f0e8b11d0f600bfb0c093", opaque="", algorithm=MD5';
	   var config = {
	           headers:{'Authorization': digest}
	   }; 
	   return $http.get(url);  
   };
   
   //获取用户信息
   var getUser = function(){
	   var url = APIURL.BaseURL+"/api/auth2/get_login_user";
	  return $http.get(url);
   };
   var login = function(user){
	   var url = APIURL.BaseURL+"/api/auth2/login";
	   $log.debug("url" , url);
	   $log.debug("user.token" , user.token);
	   //Digest realm="iptel.org", qop="auth,auth-int",nonce="dcd98b7102dd2f0e8b11d0f600bfb0c093", opaque="", algorithm=MD5
	   
	   var nc = "00000001";
	   var cnonce = "xx";
	   var method = "POST";
	   var uri = "/api/auth2/login";
	   
//	   var responseId = getResponse(user.token , user.UserName, realmName, user.Password ,  nc , cnonce , qop , method , uri);
//	   var digest = getDigestStr(user.UserName, realmName ,  user.token , uri , qop , nc , cnonce, responseId , opaque);
	   
//	   var config = {
//	           headers:{'Authorization': digest}
//	   }; 
	   var config = {};
	   return $http.post(url , user , config);  
   };
   /***
    * Digest username="user", realm="Contacts Realm via Digest Authentication", nonce="MTIwMDAwOmM1ZjBmNDc1M2RhYTFhYWYwZTI3NjdhNDBlMDY2ZjRi", uri="/xbmt-api/api/j_spring_security_check", qop=auth, nc=00000001, cnonce="xx", response="2d860fb85b04c86c68b655efbd4505c7", opaque=""
    */
   var getDigestStr = function(userName, realmName ,  nonce , uri , qop , nc , cnonce, responseId , opaque){
	   var digestStr = 'Digest username="'+userName+'",';
	   digestStr += 'realm="'+realmName+'",';
	   digestStr += 'nonce="'+nonce+'",';
	   digestStr += 'uri="'+uri+'",';
	   digestStr += 'qop='+qop+',';
	   digestStr += 'nc='+nc+',';
	   digestStr += 'cnonce="'+cnonce+'",';
	   digestStr += 'response="'+responseId+'",';
	   digestStr += 'opaque="'+opaque+'"';
	   $log.debug("digestStr=",digestStr);
	   return digestStr;
   };
   
   var getResponse = function(nonce , userName, realmName, password ,  noncecount , cnonce , qop , method , uri){
	   //  md5_pwd = MD5Service.getMD5Value(md5_pwd+token);
	   var HA1 = MD5Service.getMD5Value(userName+":"+realmName+":"+password);
	   var HD = nonce+":"+noncecount+":"+cnonce+":"+qop;
	   var HA2 = MD5Service.getMD5Value(method + ":" + uri);
	   var temp =  MD5Service.getMD5Value(HA1+":"+HD+":"+HA2);
   	   $log.debug("HA1=",HA1);
   	   $log.debug("HD=",HD);
   	   $log.debug("HA2",HA2);
       $log.debug("Response=",temp);//Response=505a7b89ff95fd5578f9869ee10cf4f9
      return temp;
   };
   
   return{
	   getToken: getToken , 
	   getUser: getUser ,
	   login : login 
   };
}]);



/*******
  测试数据请求service定义
******/
xbmtServices.factory('TestListService2', ['$resource', 'APIURL' , function ($resource , APIURL) {
   var url = APIURL.BaseURL+"/api/auth2/:cmd";
   //console.log("url="+url);
   return $resource(url, {}, {
       list:{method:'GET' , params:{cmd:'list'}},
       list2:{method:'GET' , params:{cmd:'list2'}}
   });
}]);


/***
 * 测试  提交json数据
 */
xbmtServices.factory('TestJsonService' , ['$http' , 'APIURL'  , function($http , APIURL ){
     var baseUrl = APIURL.BaseURL+APIURL.UserAPI;
     
       // url = "http://127.0.0.1:9090/api/follow/set";  undefined
    //post 提交传递json对象 需要的头类型 信息  'application/json;charset=utf-8'
    var config = { 
                headers:{'Content-Type': 'application/json;charset=utf-8'}
    }; 
     var postSubmit = function(cmd  , paramsStr){
            var url = baseUrl + cmd;
            console.log("url="+url);//data : paramsStr , | params:paramsStr ,
            return $http({
                method:'POST' , 
                url : url,
                data: paramsStr ,
                transformRequest : [function(data) {
                    return angular.isObject(data) && String(data) !== '[object File]' ? DataTransformService.getJsonObjToFormData(data) : data;
                }]
            });
     };

     return {
        testJsonSubmit: function(paramsObjectArray){
        	console.log("json");
        	console.log(paramsObjectArray);
           var url = baseUrl + "/register";//, config
           return $http.post(url , paramsObjectArray, config , function(data){
        	   
           });
        },
        postSubmit:postSubmit

     };

}]);




/*****
 * 用户身份认证 service 定义
 */
xbmtServices.factory('Auth', ['$cookieStore' , '$rootScope' , '$cookies', 'ACCESS_LEVELS', 
	 function ($cookieStore , $rootScope , $cookies , ACCESS_LEVELS) {
	 var _user = $cookieStore.get('user');
	 if(!_user){
	     _user = {token:null , authKey: null};
	 };
	 //设置cookie
	 var setUser = function(user , flag){
	     if(!user.role || user.role < 0) {
	         user.role = ACCESS_LEVELS.pub;
	     }
	     _user = user;
	     $cookieStore.put('user', _user);
	     $rootScope.isLogin = flag;
	 };
	 //设置 cookie http摘要认证信息
	 var _authDigestInfo = $cookieStore.get('authDigestInfo');
	 var setAuthDigestInfo = function(authDigestInfo){
		 _authDigestInfo = authDigestInfo;
		 $cookieStore.put('authDigestInfo', authDigestInfo);
	 };
	 //获取摘要认证信息
	 var getAuthDigestInfo = function(){
		 return _authDigestInfo;
	 };
	 
	 return {
	     isAuthorized:function(lvl){
	         return _user ? (_user.role >= lvl) : false;
	     },
	     setUser:setUser,
	     isLoggedIn:function(){
	         if(_user){
	        	// console.log("_user="+_user+" \t _user.id="+_user.id);
	             return _user.id  ? true : false;
	         }
	         return false;
	     },
	     //用户对象
	     getUser:function(){
	         return _user;
	     },
	     //用户id
	     getId:function(){
	         return _user ? _user.id:null;
	     },
	     //用户手机号
	     getMobile:function(){
	         return _user ? _user.mobile:null;
	     },
	     //邀请码
	     getInvitationCode: function(){
	    	 return _user ? _user.promocode:null;
	     },
	     
	     setToken:function(token , flag){
	         //console.log('_user='+_user);
	          if(!_user){
	             _user = {token:null , authKey: null};
	          };
	         _user.token = token;
	         setUser(_user , flag);
	     },
	     getToken:function(){
	         return _user ? _user.token:'';
	     },
	     setAuthKey:function(authKey , flag){
	         _user.authKey = authKey;
	         setUser(_user , flag);
	     },
	     getAuthKey:function(){
	         return _user ? _user.authKey:'';
	     },
	     //注销用户信息、登录状态
	     logoutUser:function() {
	         $cookieStore.remove('user');
	         _user = null;
	         $rootScope.isLogin = false;
	     },
	     //注销摘要认证信息
	     logoutAuthDigest:function() {
	         $cookieStore.remove('authDigestInfo');
	     },
	     //注销所有信息：用户信息、摘要认证信息、登录状态
	     logoutAll: function(){
	    	 $cookieStore.remove('user');
	         $cookieStore.remove('authDigestInfo');
	         _user = null;
	         _authDigestInfo = null;
	         $rootScope.isLogin = false;
	     },
	     setAuthDigestInfo: setAuthDigestInfo,
	     getAuthDigestInfo: getAuthDigestInfo
	 };
}]);




/*********
 用户拦截器 service 3定义  自定义token算法实现
*************/
xbmtServices.factory('UserInterceptor3', ['$q', '$rootScope' , '$location' , '$log', 'APIURL', 'Auth', 
     function ($q, $rootScope, $location , $log , APIURL , Auth) {
 //登录URL地址
 var userLoginURL = APIURL.UserLogin;
 //判断请求URL 地址是否为登录
 var isLoginURL = function(requestURL){
     var index = requestURL.indexOf(userLoginURL);
     if(index != -1){
         return true;
     }else{
         return false;
     }
 };

 return {
     request:function(config){
         var token = Auth.getToken();
         var method = config.method;
         //第一种方法：增加header config.headers['X-Auth-Token'] = authToken;  测试通过，个人觉得加入头中最为合适，但存在缺点不支持跨域
         if(token && token != null){
             config.headers['X-Auth-Token'] = token;
         }
         //第二种方法：增加URL参数  config.url = config.url+"?token="+token; ，测试通过，但是URL中追加参数感觉不是最优，支持跨域访问 
        /* if(token && token != null){
        	 var url = config.url;
             var index = url.indexOf("api");//是api接口才增加token 参数
             if(index != -1){
            	 var index = url.indexOf("?");
                 if(index == -1){
                	 config.url = config.url+"?token="+token;
                 }else{
                	 config.url = config.url+"&token="+token;
                 }
             }
         }*/
         return config;
     },
     response: function(response){
         var requestURL = response.config.url;
         var method = response.config.method;
         //$log.debug("reponse11=",response);
         //当服务器响应状态 为200 ，但是数据响应状态 为401时，跳转登录页面
         if(response.status == 200){
        	 if(typeof(response.data) == 'object'){
        		 if(response.data.code == 401){
        			 $log.debug("response.data.code====401");
        			 $rootScope.$broadcast('auth:loginRequired');
        			 //当后台返回未授权时，需要注销当前登录用户，重新登录
                     Auth.logoutUser();
        		 }
        	 }
         }
         return response;
     },
     responseError: function (response) {
         console.log("response.status="+response.status);
         switch(response.status) {
         	 case 200:
         		 //此处不会进，因为响应错误的时候 才会经 非200的错误
         		$log.debug("responseError.reponse=",response);
         		 break;
             case 401:
                 if (response.config.url != '/api/auth3/login'){
                     $rootScope.$broadcast('auth:loginRequired');
                 }
                 //当后台返回未授权时，需要注销当前登录用户，重新登录
                 Auth.logoutUser();
                 break;
             case 403:
                 $rootScope.$broadcast('auth:forbidden');
                 break;
             case 404:
                 $rootScope.$broadcast('page:notFound');
                 break;
             case 500:
                 $rootScope.$broadcast('server:error');
                 break;
         };
         /*var data = response.data;
         // 判断错误码，如果是未登录
         if(data["errorCode"] == "500999"){
             // 清空用户本地token存储的信息，如果
             $rootScope.$user = {token:""};
             // 全局事件，方便其他view获取该事件，并给以相应的提示或处理
             $rootScope.$emit("userIntercepted","notLogin",response);
         }
         // 如果是登录超时
         if(data["errorCode"] == "500998"){
             $rootScope.$emit("userIntercepted","sessionOut",response);
         }*/
         return $q.reject(response);
     }
 };
}]);





/***
 * http 摘要认证 客户端处理Service
 */
xbmtServices.factory('AuthDigestService' , ['$log' , 'APIURL' , 'Auth' ,  'MD5Service'  , function($log , APIURL , Auth , MD5Service ){
	 /***
	    *   拼接 摘要认证字符串
	    * Digest username="user", realm="Contacts Realm via Digest Authentication", 
	    * nonce="MTIwMDAwOmM1ZjBmNDc1M2RhYTFhYWYwZTI3NjdhNDBlMDY2ZjRi", 
	    * uri="/xbmt-api/api/j_spring_security_check", qop=auth, nc=00000001,
	    * cnonce="xx", response="2d860fb85b04c86c68b655efbd4505c7", opaque=""
	    */
	   var getDigestStr = function(userName, realmName ,  nonce , uri , qop , nc , cnonce, responseId , opaque){
		   if(cnonce == null){
			   cnonce = "";
		   }
		   if(opaque == null){
			   opaque = "";
		   }
		   var digestStr = 'Digest username="'+userName+'",';
		   digestStr += 'realm="'+realmName+'",';
		   digestStr += 'nonce="'+nonce+'",';
		   digestStr += 'uri="'+uri+'",';
		   digestStr += 'qop='+qop+',';
		   digestStr += 'nc='+nc+',';
		   digestStr += 'cnonce="'+cnonce+'",';
		   digestStr += 'response="'+responseId+'",';
		   digestStr += 'opaque="'+opaque+'"';
		   $log.debug("digestStr=",digestStr);
		   return digestStr;
	   };
	   
	   /**
	    * 计算签名值
	    * @param nonce(类似 token) 的值也是一个字符串，如果不严格，可以随机生成一个就行，注意它是个GUID，即唯一的、不重复的。
	    *    目前= Base64.encode（失效毫秒数：MD5(失效毫秒数：计算签名固定key)）
	    * @param userName 用户名
	    * @param realmName realm的值是一个简单的字符串，而rfc2617上写的是一个email类型的字符串，我看这个没有必要意义，所以我就也简单地写了个email形式的字符串
	    * @param password 登录用户密码，数据库用户密码，如果是加密的，则需要加密后才可以使用
	    * @param noncecount nc 重新发送认证次数 计数器 字符串  ,8位  如： 00000001 、00000002、00000003
	    * @param cnonce 固定字符串，目前没有使用
	    * @param qop 认证方式， 目前 auth 
	    * @param  method 是指“GET”/"POST"，即http头中指定的获取资源的方式
	    * @param  rui 客户端截取URL 获取URL 即可
	    */
	   var getResponse = function(nonce , userName, realmName, password ,  noncecount , cnonce , qop , method , uri){
		   if(cnonce == null){
			   cnonce = "";
		   }
		   //  md5_pwd = MD5Service.getMD5Value(md5_pwd+token);
		   var HA1 = MD5Service.getMD5Value(userName+":"+realmName+":"+password);
		   var HD = nonce+":"+noncecount+":"+cnonce+":"+qop;
		   var HA2 = MD5Service.getMD5Value(method + ":" + uri);
		   var temp =  MD5Service.getMD5Value(HA1+":"+HD+":"+HA2);
	   	   $log.debug("HA1=",HA1);
	   	   $log.debug("HD=",HD);
	   	   $log.debug("HA2",HA2);
	       $log.debug("Response=",temp);//Response=505a7b89ff95fd5578f9869ee10cf4f9
	      return temp;
	   };
	 //登录URL地址
	   var userLoginURL = "/api/auth2/login";//登录地址
	   //判断请求URL 地址是否为登录
	   var isLoginURL = function(requestURL){
	       var index = requestURL.indexOf(userLoginURL);
	       if(index != -1){
	           return true;
	       }else{
	           return false;
	       }
	   };
	   //获取请求URI 
	   var getRequestURI = function(url){
		   var index = url.indexOf("/api");
		   var uri = url.substring(index);
		   $log.debug("uri=" , uri);
		   return uri;
	   };
	   var loginIndex = 1;//登录计数器
	return {
		/***
		 * 计算签名值
		 * @param userName 用户名
		 * @param password 用户密码
		 * @param noncecount 计数器 , 如：00000001
		 * @param uri  请求uri地址
		 * @param method 请求方法 如：GET、POST
		 * @returns
		 */
		getAuthDigestStr1: function(userName , password , noncecount , uri , method){
			if(userName == null || userName == ""){
				var user = Auth.getUser();
				if(user && user != null){
					userName = user['username'];
					password = user['password'];
				}
				noncecount = "00000001";//第一次
			}
			//获取 摘要认证信息 authDigestInfo['authDigestInfo']
			var authDigestInfo = Auth.getAuthDigestInfo();
			$log.debug("getAuthDigestStr.authDigestInfo" , authDigestInfo);
			//计算签名值
			var responseId = getResponse(authDigestInfo['nonce'] , userName , authDigestInfo['realmName'] , password , noncecount , authDigestInfo['cnonce'] , authDigestInfo['qop'] , method , uri);
			//拼接 认证摘要字符串
			return getDigestStr(userName , authDigestInfo['realmName'] , authDigestInfo['nonce'] , uri , authDigestInfo['qop'] , noncecount , authDigestInfo['cnonce'] , responseId , authDigestInfo['opaque']);
		},
		/**
		 * 传入 请求 config对象
		 * @param config
		 * @returns
		 */
		getAuthDigestStr: function(config){
			 var url = config.url;
			//获取 客户端 cookie的摘要认证信息
			 var authDigestInfo = Auth.getAuthDigestInfo();
			 //判断是否登录请求
			var isLoginReq = isLoginURL(url);
			if(!isLoginReq){//非登录 就需要增加摘要信息
				//1、首先判断当前用户是否登录，如果未登录 直接返回
				if(!Auth.isLoggedIn()){
					return;
				}
				//$log.debug("config=" , config);
		         //$log.debug("request.authDigestInfo=" , authDigestInfo);
				if(!authDigestInfo || authDigestInfo == null){
					return;
				}
			}
			
			 var userName = "";
        	 var password = "";
        	 var method = config.method;
        	 var noncecount = "";
        	
        	var uri = getRequestURI(url);
			var user = Auth.getUser();//获取客户端 cookie中的用户信息
			if(user && user != null){
				userName = user['username'];
				password = user['password'];
			}
        	if(isLoginReq){//判断是否为登录接口  用户名 密码 获取请求data中的数据
        		 userName = config.data['username'];
            	 password = config.data['password'];
            	 noncecount = "0000000"+loginIndex;//总共共8位，后面需要根据index 的长度进行0的补位
            	 loginIndex++;
            	 $log.debug("userName=" , userName);
            	 $log.debug("password=" , password);
        	}
        	  //计算签名值
 			var responseId = getResponse(authDigestInfo['nonce'] , userName , authDigestInfo['realmName'] , password , noncecount , authDigestInfo['cnonce'] , authDigestInfo['qop'] , method , uri);
 			//拼接 摘要认证字符串
 	        var authDigestStr =  getDigestStr(userName , authDigestInfo['realmName'] , authDigestInfo['nonce'] , uri , authDigestInfo['qop'] , noncecount , authDigestInfo['cnonce'] , responseId , authDigestInfo['opaque']);
 	        config.headers[authDigestInfo['reqAuthHeaderKey']] = authDigestStr;
 	        $log.debug("authDigestStr==" , authDigestStr);
         }
	};
}]);



/*********
 用户拦截器 service 2 定义  HTTP摘要认证  'AuthDigestService', 
*************/
xbmtServices.factory('UserInterceptor2', ['$q', '$rootScope' , '$location' , '$log', 'APIURL', 'Auth', 'AuthDigestService',
                                          function ($q, $rootScope, $location , $log , APIURL , Auth , AuthDigestService ) {

 var loginIndex = 1;//登录计数器
 var getTokenURL = "/api/auth2/getToken";//获取token url地址
 //登录URL地址
 var userLoginURL = "/api/auth2/login";//登录地址
 //判断请求URL 地址是否为登录
 var isLoginURL = function(requestURL){
     var index = requestURL.indexOf(userLoginURL);
     if(index != -1){
         return true;
     }else{
         return false;
     }
 };
 //获取token 头信息
 var getTokenHeader = function(response){
	// $log.debug("reponse22=",response);
	 var requestURL = response.config.url;
	 //当服务器响应状态 为200 ，但是数据响应状态 为401时，并且是 /api/auth2/getToken
	 var index = requestURL.indexOf(getTokenURL);
     if(response.status == 200 && response.data){
    	 if(index != -1 || response.data['code'] == 401){//当时请求 token 或  服务器端返回 401时，需要重写设置摘要认证信息
    		 //WWW-Authenticate:
        	 //$log.debug(" response.config.headers=" , response.config.headers);
        	 //$log.debug(" response.data.entity=" , response.data.entity);
        	 //设置认证摘要信息
        	 Auth.setAuthDigestInfo(response.data.entity);
        	 var authHeader = response.headers(response.data.entity['authHeaderName']);
        	// $log.debug("authHeader[authHeaderName]=" , authHeader);
        	 $log.debug("response.authDigestInfo=" , Auth.getAuthDigestInfo());
        	 
        	 //当后台返回未授权时，需要注销当前登录用户，重新登录
             Auth.logoutUser();//注销登录用户信息 调整登录页面
        	 $rootScope.$broadcast('auth:loginRequired');
    	 }
     }
 };

 return {
     request:function(config){
    	 var url = config.url;
    	 var index = url.indexOf("/api");
    	 //过滤接口 api 才增加摘要头 , 否则前台 页面 都会进入该请求
    	 if(index != -1){
    		//增加摘要认证头信息
        	 AuthDigestService.getAuthDigestStr(config);
        	 $log.debug("UserInterceptor2.request.config=" , config);
    	 }
         return config;
     },
     response: function(response){
         var requestURL = response.config.url;
         var method = response.config.method;
         //$log.debug("reponse11=",response);
         getTokenHeader(response); //获取token 头信息
         return response;
     },
     responseError: function (response) {
         console.log("response.status="+response.status);
         switch(response.status) {
         	 case 200:
         		 //此处不会进，因为响应错误的时候 才会经 非200的错误
         		$log.debug("responseError.reponse=",response);
         		 break;
             case 401:
                 if (response.config.url != '/api/auth2/login'){
                     $rootScope.$broadcast('auth:loginRequired');
                 }
                 //当后台返回未授权时，需要注销当前登录用户，重新登录
                 Auth.logoutUser();
                 break;
             case 403:
                 $rootScope.$broadcast('auth:forbidden');
                 break;
             case 404:
                 $rootScope.$broadcast('page:notFound');
                 break;
             case 500:
                 $rootScope.$broadcast('server:error');
                 break;
         };
         /*var data = response.data;
         // 判断错误码，如果是未登录
         if(data["errorCode"] == "500999"){
             // 清空用户本地token存储的信息，如果
             $rootScope.$user = {token:""};
             // 全局事件，方便其他view获取该事件，并给以相应的提示或处理
             $rootScope.$emit("userIntercepted","notLogin",response);
         }
         // 如果是登录超时
         if(data["errorCode"] == "500998"){
             $rootScope.$emit("userIntercepted","sessionOut",response);
         }*/
         return $q.reject(response);
     }
 };
}]);



/*********
 用户拦截器 service定义
*************/
xbmtServices.factory('UserInterceptor', ['$q', '$rootScope' , '$location', 'APIURL', 'Auth', 
     function ($q, $rootScope, $location , APIURL , Auth) {
 //登录URL地址
 var userLoginURL = APIURL.UserLogin;
 //判断请求URL 地址是否为登录
 var isLoginURL = function(requestURL){
     var index = requestURL.indexOf(userLoginURL);
     if(index != -1){
         return true;
     }else{
         return false;
     }
 };

 return {
     request:function(config){
         var token = Auth.getToken();
         var method = config.method;
        // console.log("request.token="+token+" \t config.url="+config.url+" \t mehtod="+method);
         if(isLoginURL(config.url) && token){
            // config.headers["token"] = token;//第一种解决方法：由于跨域问题，添加的token头信息不正确【测试不正确】
            //第二种方法：在登录URL地址最后增加 token参数，如：xxx?token=xxxx
            config.url = config.url+"?token="+token;
           // console.log("config.url ="+config.url);
         }
         return config;
     },
     response: function(response){
         var requestURL = response.config.url;
         var method = response.config.method;
        //console.log("response.config.url="+requestURL+" \t method="+method);
         if(isLoginURL(requestURL)) {
             if(method == 'GET'){//获取服务器端返回token
                 var token = response.data.entity;
                // console.log("response.data.token="+token);
                Auth.setToken(response.data.entity , false);
             }else if(method == 'POST'){//正式登录操作
                 var headers = response.headers();
                 console.log("response.headers().authkey="+headers["authkey"]);
                //获取登录成功后的身份识别码 
                var authKey = response.config.headers["authkey"];
                console.log("response.config.headers[authkey]="+authKey);
                //Auth.setAuthKey(authKey , false);
             }
         }
          return response;
     },
     responseError: function (response) {
         console.log("response.status="+response.status);
         switch(response.status) {
             case 401:
                 if (response.config.url != '/api/login'){
                     $rootScope.$broadcast('auth:loginRequired');
                 }
                 //当后台返回未授权时，需要注销当前登录用户，重新登录
                 Auth.logoutUser();
                 break;
             case 403:
                 $rootScope.$broadcast('auth:forbidden');
                 break;
             case 404:
                 $rootScope.$broadcast('page:notFound');
                 break;
             case 500:
                 $rootScope.$broadcast('server:error');
                 break;
         };
         /*var data = response.data;
         // 判断错误码，如果是未登录
         if(data["errorCode"] == "500999"){
             // 清空用户本地token存储的信息，如果
             $rootScope.$user = {token:""};
             // 全局事件，方便其他view获取该事件，并给以相应的提示或处理
             $rootScope.$emit("userIntercepted","notLogin",response);
         }
         // 如果是登录超时
         if(data["errorCode"] == "500998"){
             $rootScope.$emit("userIntercepted","sessionOut",response);
         }*/
         return $q.reject(response);
     }
 };
}]);






/******
MD5加密 service定义
*****/
xbmtServices.factory('MD5Service' , [function(){
var md5 = function(s){function L(k,d){return(k<<d)|(k>>>(32-d));}function K(G,k){var I,d,F,H,x;F=(G&2147483648);H=(k&2147483648);I=(G&1073741824);d=(k&1073741824);x=(G&1073741823)+(k&1073741823);if(I&d){return(x^2147483648^F^H);}if(I|d){if(x&1073741824){return(x^3221225472^F^H);}else{return(x^1073741824^F^H);}}else{return(x^F^H);}}function r(d,F,k){return(d&F)|((~d)&k);}function q(d,F,k){return(d&k)|(F&(~k));}function p(d,F,k){return(d^F^k);}function n(d,F,k){return(F^(d|(~k)));}function u(G,F,aa,Z,k,H,I){G=K(G,K(K(r(F,aa,Z),k),I));return K(L(G,H),F);}function f(G,F,aa,Z,k,H,I){G=K(G,K(K(q(F,aa,Z),k),I));return K(L(G,H),F);}function D(G,F,aa,Z,k,H,I){G=K(G,K(K(p(F,aa,Z),k),I));return K(L(G,H),F);}function t(G,F,aa,Z,k,H,I){G=K(G,K(K(n(F,aa,Z),k),I));return K(L(G,H),F);}function e(G){var Z;var F=G.length;var x=F+8;var k=(x-(x%64))/64;var I=(k+1)*16;var aa=Array(I-1);var d=0;var H=0;while(H<F){Z=(H-(H%4))/4;d=(H%4)*8;aa[Z]=(aa[Z]|(G.charCodeAt(H)<<d));H++;}Z=(H-(H%4))/4;d=(H%4)*8;aa[Z]=aa[Z]|(128<<d);aa[I-2]=F<<3;aa[I-1]=F>>>29;return aa;}function B(x){var k="",F="",G,d;for(d=0;d<=3;d++){G=(x>>>(d*8))&255;F="0"+G.toString(16);k=k+F.substr(F.length-2,2);}return k;}function J(k){k=k.replace(/rn/g,"n");var d="";for(var F=0;F<k.length;F++){var x=k.charCodeAt(F);if(x<128){d+=String.fromCharCode(x);}else{if((x>127)&&(x<2048)){d+=String.fromCharCode((x>>6)|192);d+=String.fromCharCode((x&63)|128);}else{d+=String.fromCharCode((x>>12)|224);d+=String.fromCharCode(((x>>6)&63)|128);d+=String.fromCharCode((x&63)|128);}}}return d;}var C=Array();var P,h,E,v,g,Y,X,W,V;var S=7,Q=12,N=17,M=22;var A=5,z=9,y=14,w=20;var o=4,m=11,l=16,j=23;var U=6,T=10,R=15,O=21;s=J(s);C=e(s);Y=1732584193;X=4023233417;W=2562383102;V=271733878;for(P=0;P<C.length;P+=16){h=Y;E=X;v=W;g=V;Y=u(Y,X,W,V,C[P+0],S,3614090360);V=u(V,Y,X,W,C[P+1],Q,3905402710);W=u(W,V,Y,X,C[P+2],N,606105819);X=u(X,W,V,Y,C[P+3],M,3250441966);Y=u(Y,X,W,V,C[P+4],S,4118548399);V=u(V,Y,X,W,C[P+5],Q,1200080426);W=u(W,V,Y,X,C[P+6],N,2821735955);X=u(X,W,V,Y,C[P+7],M,4249261313);Y=u(Y,X,W,V,C[P+8],S,1770035416);V=u(V,Y,X,W,C[P+9],Q,2336552879);W=u(W,V,Y,X,C[P+10],N,4294925233);X=u(X,W,V,Y,C[P+11],M,2304563134);Y=u(Y,X,W,V,C[P+12],S,1804603682);V=u(V,Y,X,W,C[P+13],Q,4254626195);W=u(W,V,Y,X,C[P+14],N,2792965006);X=u(X,W,V,Y,C[P+15],M,1236535329);Y=f(Y,X,W,V,C[P+1],A,4129170786);V=f(V,Y,X,W,C[P+6],z,3225465664);W=f(W,V,Y,X,C[P+11],y,643717713);X=f(X,W,V,Y,C[P+0],w,3921069994);Y=f(Y,X,W,V,C[P+5],A,3593408605);V=f(V,Y,X,W,C[P+10],z,38016083);W=f(W,V,Y,X,C[P+15],y,3634488961);X=f(X,W,V,Y,C[P+4],w,3889429448);Y=f(Y,X,W,V,C[P+9],A,568446438);V=f(V,Y,X,W,C[P+14],z,3275163606);W=f(W,V,Y,X,C[P+3],y,4107603335);X=f(X,W,V,Y,C[P+8],w,1163531501);Y=f(Y,X,W,V,C[P+13],A,2850285829);V=f(V,Y,X,W,C[P+2],z,4243563512);W=f(W,V,Y,X,C[P+7],y,1735328473);X=f(X,W,V,Y,C[P+12],w,2368359562);Y=D(Y,X,W,V,C[P+5],o,4294588738);V=D(V,Y,X,W,C[P+8],m,2272392833);W=D(W,V,Y,X,C[P+11],l,1839030562);X=D(X,W,V,Y,C[P+14],j,4259657740);Y=D(Y,X,W,V,C[P+1],o,2763975236);V=D(V,Y,X,W,C[P+4],m,1272893353);W=D(W,V,Y,X,C[P+7],l,4139469664);X=D(X,W,V,Y,C[P+10],j,3200236656);Y=D(Y,X,W,V,C[P+13],o,681279174);V=D(V,Y,X,W,C[P+0],m,3936430074);W=D(W,V,Y,X,C[P+3],l,3572445317);X=D(X,W,V,Y,C[P+6],j,76029189);Y=D(Y,X,W,V,C[P+9],o,3654602809);V=D(V,Y,X,W,C[P+12],m,3873151461);W=D(W,V,Y,X,C[P+15],l,530742520);X=D(X,W,V,Y,C[P+2],j,3299628645);Y=t(Y,X,W,V,C[P+0],U,4096336452);V=t(V,Y,X,W,C[P+7],T,1126891415);W=t(W,V,Y,X,C[P+14],R,2878612391);X=t(X,W,V,Y,C[P+5],O,4237533241);Y=t(Y,X,W,V,C[P+12],U,1700485571);V=t(V,Y,X,W,C[P+3],T,2399980690);W=t(W,V,Y,X,C[P+10],R,4293915773);X=t(X,W,V,Y,C[P+1],O,2240044497);Y=t(Y,X,W,V,C[P+8],U,1873313359);V=t(V,Y,X,W,C[P+15],T,4264355552);W=t(W,V,Y,X,C[P+6],R,2734768916);X=t(X,W,V,Y,C[P+13],O,1309151649);Y=t(Y,X,W,V,C[P+4],U,4149444226);V=t(V,Y,X,W,C[P+11],T,3174756917);W=t(W,V,Y,X,C[P+2],R,718787259);X=t(X,W,V,Y,C[P+9],O,3951481745);Y=K(Y,h);X=K(X,E);W=K(W,v);V=K(V,g);}var i=B(Y)+B(X)+B(W)+B(V);return i.toLowerCase();};  
return{
    getMD5Value : function(str){
        return md5(str);
    }
};
}]);






/** 
	 * 格式化数字显示方式  
	 * 用法 
	 * formatNumber(12345.999,'#,##0.00'); 
	 * formatNumber(12345.999,'#,##0.##'); 
	 * formatNumber(123,'000000'); 
	 * @param num 
	 * @param pattern 
	 */  
xbmtServices.factory('FormatService', ['$filter', 'DateService', function ($filter, DateService) {
	return{
        number:function (num,pattern){
        	var strarr = num?num.toString().split('.'):['0'];  
			  var fmtarr = pattern?pattern.split('.'):[''];  
			  var retstr='';  
			  
			  // 整数部分  
			  var str = strarr[0];  
			  var fmt = fmtarr[0];  
			  var i = str.length-1;    
			  var comma = false;  
			  for(var f=fmt.length-1;f>=0;f--){  
			    switch(fmt.substr(f,1)){  
			      case '#':  
			        if(i>=0 ) retstr = str.substr(i--,1) + retstr;  
			        break;  
			      case '0':  
			        if(i>=0) retstr = str.substr(i--,1) + retstr;  
			        else retstr = '0' + retstr;  
			        break;  
			      case ',':  
			        comma = true;  
			        retstr=','+retstr;  
			        break;  
			    }  
			  }  
			  if(i>=0){  
			    if(comma){  
			      var l = str.length;  
			      for(;i>=0;i--){  
			        retstr = str.substr(i,1) + retstr;  
			        if(i>0 && ((l-i)%3)==0) retstr = ',' + retstr;   
			      }  
			    }  
			    else retstr = str.substr(0,i+1) + retstr;  
			  }  
			  
			  retstr = retstr+'.';  
			  // 处理小数部分  
			  str=strarr.length>1?strarr[1]:'';  
			  fmt=fmtarr.length>1?fmtarr[1]:'';  
			  i=0;  
			  for(var f=0;f<fmt.length;f++){  
			    switch(fmt.substr(f,1)){  
			      case '#':  
			        if(i<str.length) retstr+=str.substr(i++,1);  
			        break;  
			      case '0':  
			        if(i<str.length) retstr+= str.substr(i++,1);  
			        else retstr+='0';  
			        break;  
			    }  
			  }  
			  return retstr.replace(/^,+/,'').replace(/\.$/,''); 
		},
		date:function (strDate,pattern){
			return $filter('date')(DateService.getDate(strDate), pattern);
		}
    };
}]);

/**
 * 数字类型长度验证
 */
xbmtServices.factory('Validate', ['DateService' , function (DateService) {
	return{
		//true：正确；false：不正确
        isInteger:function (obj, len){
        	--len;
        	var reg =  new RegExp("^[1-9][0-9]{0," + len + "}$");
			if(!reg.test(obj)){
				return false;
			}else{
				return true;
			}
		},
		/**
		 * obj:需要验证值
		 * len1:整数位长度
		 * len2:小数位长度
		 */
        isDouble:function (obj, len1, len2){//
        	--len1;
        	//var str = "^(([0-9]\.[0-9]{0," + len2 + "})|([1-9][0-9]{0," + len1 + "}(\\.[0-9]{0," + len2 + "})?))$";
        	var str = "^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$";
        	//var exp =  new RegExp(str);
        	//var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
        	//alert("/^([1-9][\\d]{0," + len1 + "}|0)(\.[\\d]{1," + len2 + "})?$/");
        	var exp = eval("/^([1-9][\\d]{0," + len1 + "}|0)(\\.[\\d]{1," + len2 + "})?$/");
			if(exp.test(obj)){
				//正确
				return true;
			}else{
				//错误
				return false;
			}
		},
		isDate: function (obj){//
			return DateService.isDate(obj);
		},
		//日期差计算(返回天数，不做四舍五入)
		getOffDays: function (startDate, endDate){
			return DateService.getOffDays(startDate, endDate); 
		},
		//日期差计算(返回毫秒数)
		getOffTimes: function (startDate, endDate){
			return DateService.getOffTimes(startDate, endDate); 
		},
		/**
		 * 日期差计算 
		 * daysSrc:相差天数值(小数，不做四舍五入)
		 * daysMax:相差天数值(四舍五入)
		 * days:相差天数值(整数天数)
		 * hours:相差小时值(除去天数)
		 * minutes:相差分钟数(除去天数、小时)
		 * seconds:相差秒数(除去天数、小时、分钟)
		 * times:相差毫秒数(总毫秒数)
		 */
		getOffInfo: function (startDate, endDate){
			return DateService.getOffInfo(startDate, endDate); 
		},
		//字符串转日期格式，strDate要转为日期格式的字符串
		getDate: function (strDate){
		  	return DateService.getDate(strDate);
		}
    };
}]);


/**
 * 日期操作Service定义 DateService
 */
xbmtServices.factory('DateService', [function () {
	
	/**
	 * 获取采购产品 报价剩余天数 显示html字符串
	 */
	var getProductOfferSurplusDayStr = function(bgnDateParam , endDateParam , sysDateParam) {
			var bgnDate = new Date(bgnDateParam);//报价开始时间
			var endDate = new Date(endDateParam);//报价截止日期
			var sysDate = new Date(sysDateParam);//系统当前时间
			var isBgnDays = getOffDays(bgnDate , sysDate); //如系统日期下于开始日期，显示为“报价即将开始” 系统日期-开始日期<0 。
			// console.log("isBgnDays(string)=", typeof(isBgnDays));
			var prefix = '<span style="font-size: 10px;">';
			var suffix = '</span>';
			if (isBgnDays < 0) {
				return prefix+"报价即将开始"+suffix;
			}
			var priDays = getOffDays(sysDate , endDate); //报价剩余时间少于一天，则显示为“报价即将结束”0 <截止日期-系统日期<1
			if (1 > priDays && priDays > 0) {
				return prefix+"报价即将结束"+suffix;
			}
			var days = getOffDays(endDate , sysDate);//系统日期大于截止日期，显示为“报价已结束” 系统日期-截止日期>0;
			if (days > 0) {
				return prefix+"报价已结束"+suffix;
			}
			
			//计算两个日期相差天数
			var diffDay = getDateDiffDay(endDate , sysDate);
			
			return "还剩<span>"+diffDay+"</span>天";
		};
	
    
	/**
	 *计算两个日期(开始时间、结束时间)相差天数
	 *返回结果四舍五入
	 *date1结束时间
	 *date2 当前时间
	 */
	 var getDateDiffDay = function(endDate , nowDate){
		 var endDate = new Date(endDate);
		 var nowDate = new Date(nowDate);
		 var days = (endDate.getTime() - nowDate.getTime())/24/60/60/1000;
		// console.log("days(string)=", Math.round(days));
		 return Math.round(days);//Math.round() 我们数学中常用到的四舍五入取整。
	 };
	 
	 /*****
	  * 日期 正则表达式验证
	  * true 正确， false 错误
	  */
	 var isDate = function (obj){//
			var exp = /((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$))/ig;//eval("/((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$))/ig");
			if(exp.test(obj)){
				//正确
				return true;
			}else{
				//错误
				return false;
			}
	 };
	 
	 /****
	 * 日期差计算(返回天数，不做四舍五入)
	 */
	 var getOffDays = function (startDate, endDate){
		 
		//得到时间戳相减 得到以毫秒为单位的差   
		var mmSec = (endDate.getTime() - startDate.getTime()); 
		//console.log("mmSec="+mmSec);
		//单位转换为天并返回  
		return (mmSec / 3600000 / 24); 
	};
	
	/****
	 * 日期差计算(返回毫秒数)
	 */
	var getOffTimes = function (startDate, endDate){
		//得到时间戳相减 得到以毫秒为单位的差   
		var mmSec = (endDate.getTime() - startDate.getTime()); 
		return mmSec; 
	};
	
	/**
	 * 日期差计算 
	 * daysSrc:相差天数值(小数，不做四舍五入)
	 * daysMax:相差天数值(四舍五入)
	 * days:相差天数值(整数天数)
	 * hours:相差小时值(除去天数)
	 * minutes:相差分钟数(除去天数、小时)
	 * seconds:相差秒数(除去天数、小时、分钟)
	 * times:相差毫秒数(总毫秒数)
	 */
	var getOffInfo = function(startDate, endDate){
		var obj = {
			daysSrc:0,
			daysMax:0,
			days:0,
			hours:0,
			minutes:0,
			seconds:0,
			times:0
		};
		//得到时间戳相减 得到以毫秒为单位的差   
		var mmSec = (endDate.getTime() - startDate.getTime()); 
		obj.times = mmSec;
		obj.daysSrc = mmSec / (24*3600*1000);
		//计算出相差天数
		obj.days = Math.floor(obj.daysSrc);
		obj.daysMax = Math.round(obj.daysSrc);
		//计算出小时数
		var leave1 = mmSec % (24*3600*1000);    //计算天数后剩余的毫秒数
		obj.hours = Math.floor(leave1/(3600*1000));
		//计算相差分钟数
		var leave2 = leave1 % (3600*1000);        //计算小时数后剩余的毫秒数
		obj.minutes = Math.floor(leave2 / (60*1000));
		//计算相差秒数
		var leave3 = leave2 % (60*1000);      //计算分钟数后剩余的毫秒数
		obj.seconds = Math.round(leave3/1000);
		
		return obj; 
	};
	
	/***
	 * 字符串转日期格式，strDate要转为日期格式的字符串
	 */
	var getDate = function (strDate){
		var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/, 
	   	function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
	  	return date;
	};
	
	return{
		getDateDiffDay: getDateDiffDay,
		getPOSDayStr: getProductOfferSurplusDayStr ,
		isDate: isDate,
		getOffDays: getOffDays,
		getOffTimes: getOffTimes,
		getOffInfo: getOffInfo ,
		getDate: getDate
	}
}]);



/**
 * 数据转换操作Service定义 DataTransformService
 */
xbmtServices.factory('DataTransformService', [function () {
	
	var getJsonObjToFormData = function(jsonObj) {
        var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

        for(name in jsonObj) {
            value = jsonObj[name];

            if(value instanceof Array) {
                for(i=0; i<value.length; ++i) {
                    subValue = value[i];
                    fullSubName = name + '[' + i + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += getJsonObjToFormData (innerObj) + '&';
                }
            }
            else if(value instanceof Object) {
                for(subName in value) {
                    subValue = value[subName];
                    fullSubName = name + '.' + subName;
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += getJsonObjToFormData (innerObj) + '&';
                }
            }
            else if(value !== undefined && value !== null)
                query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
        }
        return query.length ? query.substr(0, query.length - 1) : query;
    };
	
	return {
		getJsonObjToFormData: getJsonObjToFormData
	};
}]);

/**
 * 判断是否隐藏返回按钮  操作HideBackBtnService
 * true  隐藏返回按钮
 * false 显示返回按钮
 */
xbmtServices.factory('HideBackBtnService', [function () {
	
	var HideBtn = function(fromState,toState) {
		 var hideBtn = true;
		 //判断是否隐藏函数
		 var isHideBtnFrom = function(url){
			 if(url==null||url =="^"||url ==""){
				 return true;
			 }else{
				if(url =="/find" ||url =="/index"||url =="/my"){
					return false;
				}
			 }
		 };
		 
		 var isHideBtnTo = function(url){
				if(url =="/find" ||url =="/index"||url =="/my"){
					return true;
				}else{
					return false;
				}
		   };
		 
		hideBtn = isHideBtnFrom(fromState.url) || isHideBtnTo(toState.url);
		return hideBtn;
    };
	
	return {
		HideBtn: HideBtn
	};
}]);




/**
 * 分页数据处理service 
 * 如：当前页滑动加载新的数据
 * 
 */
xbmtServices.factory('PageDataService', ['$log' , function ($log) {
	
	/***
	 * 每页10条
		总共5页
		当前第5页
		总条数45条
		
		计算最后一页数据索引位置  =  (当前页 - 1) * 每页大小
		index = (5 - 1) * 10 = 40
		最后一页条数 = 总记录数 - 最后一页索引
		count = 45 - 40 = 5 


	 * 
	 * 最新分页参数信息
	 * newParam{currentpage: 1 ,//当前页
				totalpages: 0 ,// 总页数
				totalitems: 0, //总数据条数
				itemsperpage: 0 //每页条数} 
	   newData 请求最新数据
	   前一次请求的分页参数信息
	   param{currentpage: 1 ,//当前页
				totalpages: 0 ,// 总页数
				totalitems: 0, //总数据条数
				itemsperpage: 0 //每页条数} 
	   pageData 页面上数据data
	   isLastLoad 是否最后一页 加载新数据
	 * 
	 */
	var getPageData = function(newParam , newData , param , pageData , isLastLoad) {
		//$log.debug("newParam=", newParam);
		//$log.debug("param=", param);
		 if(newParam.totalpages >= param.totalpages){//1、总页数不变 或 增加
			 if(isLastLoad){//最后一页重新加载，首页删除最后一页数据 
				//计算最后一页数据索引位置    (当前页 - 1) * 每页大小
				 var index = (newParam.currentpage - 1) * newParam.itemsperpage;
				//最后一页条数 = 总记录数 - 最后一页索引
				 var count = newParam.totalitems - index;
				//从start的位置开始向后删除delCount个元素，然后从start的位置开始插入一个或多个新元素
				 pageData.splice(index , count);
			 }
			//追加 数据
			 angular.forEach(newData, function(value , index){
				 pageData.push(value);
			 });
		}
		
		return pageData;
    };
	
	return {
		getPageData: getPageData
	};
}]);