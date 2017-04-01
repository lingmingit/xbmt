<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.3/jquery.portal.js"></script>

	<!-- <h2>EasyUI Portal</h2>
    <p>This example shows how to build a simple portal system with floating panels.</p> -->
    <!-- 定义  主页 的 panel 布局列数 -->
    <div id="pp" class="easyui-panel" data-options="fit:true,border:false" style="position:relative"> <!-- width:700px; -->
        <div style="width:30%;"></div>
        <div style="width:40%;"></div>
        <div style="width:30%;"></div>
    </div>
    <style type="text/css">
        .demo-rtl .portal-column-left{
            padding-left: 10px;
            padding-right: 10px;
        }
        .demo-rtl .portal-column-right{
            padding-left:10px;
            padding-right: 0;
        }
    </style>
    <script type="text/javascript">
    	//${pageContext.request.contextPath}/html/portal_clock.html
    	//http://www.jeasyui.com/demo/main/portal_p2.html?_=1444792995260
        var panels = [
            {id:'p1',title:'Tutorials-1',height:200,collapsible:true,href:'portal_p1.html'},
            {id:'p2',title:'Clock-2',href:'${pageContext.request.contextPath}/html/portal_clock2.html'},
            {id:'p3',title:'PropertyGrid-3',height:200,collapsible:true,closable:true,href:'portal_p3.html'},
            {id:'p4',title:'DataGrid-4',height:200,closable:true,href:'portal_p4.html'},
            {id:'p5',title:'Searching-5',href:'portal_p5.html'},
            {id:'p6',title:'Graph-6',href:'portal_p6.html'},
            {id:'p7',title:'test-7',href:'portal_p7.html'}
        ];
    	
    	/****获取 cookies 中的portal 布局状态码 ****/
        function getCookie(name){
            var cookies = document.cookie.split(';');
            if (!cookies.length) return '';
            for(var i=0; i<cookies.length; i++){
                var pair = cookies[i].split('=');
                if ($.trim(pair[0]) == name){
                    return $.trim(pair[1]);
                }
            }
            return '';
        }
        function getPanelOptions(id){
            for(var i=0; i<panels.length; i++){
                if (panels[i].id == id){
                    return panels[i];
                }
            }
            return undefined;
        }
        //获取 当前portal 中panel 的 布局 状态 ，如： p1,p2:p3,p4:p5,p6,p7
        function getPortalState(){
            var aa = [];
            for(var columnIndex=0; columnIndex<3; columnIndex++){
                var cc = [];
                var panels = $('#pp').portal('getPanels', columnIndex);
                for(var i=0; i<panels.length; i++){
                    cc.push(panels[i].attr('id'));
                }
                aa.push(cc.join(','));
            }
            return aa.join(':');
        }
        
        /****
        	添加 portal 块
        ****/
        function addPanels(portalState){
            var columns = portalState.split(':');
           // console.log("columns="+columns);
            for(var columnIndex=0; columnIndex<columns.length; columnIndex++){
                var cc = columns[columnIndex].split(',');
                for(var j=0; j<cc.length; j++){
                    var options = getPanelOptions(cc[j]);
                    if (options){
                        var p = $('<div/>').attr('id',options.id).appendTo('body');
                        p.panel(options);
                        $('#pp').portal('add',{
                            panel:p,
                            columnIndex:columnIndex
                        });
                    }
                }
            }
        }
        
        $(function(){
            $('#pp').portal({
                onStateChange:function(){
                    var state = getPortalState();
                    var date = new Date();
                    date.setTime(date.getTime() + 24*3600*1000);
                    console.log("onStateChange:function.state="+state);
                    document.cookie = 'portal-state='+state+';expires='+date.toGMTString();
                }
            });
            var state = getCookie('portal-state');
            console.log("state="+state);
            if (!state){//首次访问 portal时，默认布局样式 排版
                state = 'p1,p2:p3,p4:p5,p6,p7';    // the default portal state
            }
            addPanels(state);
            $('#pp').portal('resize');
        });
    </script>
