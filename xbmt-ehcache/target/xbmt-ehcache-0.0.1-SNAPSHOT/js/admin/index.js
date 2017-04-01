



/****
 * 动态添加页签方法
 * @param menuId 菜单id
 * @param title 页签标题
 * @param href 页签url地址
 * @param icon 图标样式
 */
function addMainTab(menuId , title , href , icon){
    var tt = $('#mainTabs');  
    console.log("tt.tabs('exists', menuId)="+tt.tabs('exists', menuId));
    if (tt.tabs('exists', title)){//如果tab已经存在,则选中并刷新该tab          
        tt.tabs('select', title); //根据title 进行选中 
        refreshTab({tabTitle:title , url:href}); 
    } else {  
    	
        if (href){  
            var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:99%;"></iframe>';
        } else {  
            var content = '未实现';  
        }
        //content : content,//使用iframe 加载进行隔离
        //href : href, //通过href加载html片段存在问题，两个页面所使用的控件id都是相同的，同时加载两个页面，就会导致混乱
        tt.tabs('add',{
            title : title ,  
            closable : true,  
            content : content,
            iconCls : icon||'icon-default' ,
            menuId : menuId
        });  
    }  
} 


/**     
 * 刷新tab 
 * @cfg  
 *example: {tabTitle:'tabTitle',url:'refreshUrl'} 
 *如果tabTitle为空，则默认刷新当前选中的tab 
 *如果url为空，则默认以原来的url进行reload 
 */  
function refreshTab(cfg){  
    var refresh_tab = cfg.tabTitle?$('#mainTabs').tabs('getTab',cfg.tabTitle):$('#mainTabs').tabs('getSelected');
    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
	    var _refresh_ifram = refresh_tab.find('iframe')[0];  
	    var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;  
	    //_refresh_ifram.src = refresh_url;  
	    _refresh_ifram.contentWindow.location.href=refresh_url;  
    }
}  



function addPanelURL(){
	var url = getWebRootPath()+"/xbmtadmin/console/test";
	index++;
	$('#mainTabs').tabs('add',{
		title: 'Tab'+index,
		href: url,
		iconCls: 'icon-help',
		closable: true
	});
}


function addUserTab(){
	var url = getWebRootPath()+"/xbmtadmin/console/sys/user/index";
	addMainTab('1' , '用户管理' , url , "icon-ok");
}
function addModuleTab(){
	var url = getWebRootPath()+"/xbmtadmin/console/sys/modules/index";
	addMainTab('2' , '模块管理' , url );
}


function addMenuTab(){
	var url = getWebRootPath()+"/xbmtadmin/console/sys/menu/index";
	addMainTab('3' , '菜单管理' , url );
}

function addFunctionActionTab(){
	var url = getWebRootPath()+"/xbmtadmin/console/sys/functionAction/index";
	addMainTab('4' , '系统功能' , url );
}

