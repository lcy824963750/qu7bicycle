<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>权限管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="css/index.css">

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/tendina.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>


</head>

<body>

	<!--顶部-->
	<div class="top">
		<div style="float: left">
			<span
				style="font-size: 16px;line-height: 45px;padding-left: 20px;color: #fff">权限管理系统
				</h1>
			</span>
		</div>
		<div id="ad_setting" class="ad_setting">
			<a class="ad_setting_a" href="javascript:; ">${adminUser.name}</a>
			<ul class="dropdown-menu-uu" style="display: none" id="ad_setting_ul">
				<li class="ad_setting_ul_li"><a href="userInfoUpdate.jsp"  target="menuFrame"><i
						class="icon-cog glyph-icon"></i>信息修改</a></li>
				<li class="ad_setting_ul_li"><a href="javascript:exit();"><i
						class="icon-signout glyph-icon"></i> <span class="font-bold">退出</span>
				</a></li>
			</ul>
			<img class="use_xl" src="images/right_menu.png" />
		</div>
	</div>
	<!--顶部结束-->
	<!--菜单-->
	<div class="left-menu">
        <ul id="menu">
            <li class="menu-list">
               <a style="cursor:pointer" class="firsta"><i  class="glyph-icon xlcd"></i>系统属性管理<s class="sz"></s></a>
                <ul>
                    <li><a href="sysPropertiesAdmin.jsp" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>系统属性一览</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!--菜单右边的iframe页面-->
    <div id="right-content" class="right-content">
        <div class="content">
            <div id="page_content">
                <iframe id="menuFrame" name="menuFrame" src="sysPropertiesAdmin.jsp" style="overflow:visible;"
                        scrolling="yes" frameborder="no" width="100%" height="100%"></iframe>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	function exit() {
    		window.location.href="exit.action";
    	}
    </script>
    
    
</body>
</html>