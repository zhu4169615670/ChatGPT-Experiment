<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
<!--    <link rel="icon" href="/favicon.ico">-->
    <title>毕业答辩评分系统</title>
    
    
</head> 
<body class="layui-layout-body"> 
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header custom-header">
            
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item slide-sidebar" lay-unselect>
                    <a href="javascript:;" class="icon-font"><i class="ai ai-menufold"></i></a>
                </li>
            </ul>

            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                
                    <a href="javascript:;">${USER_SESSION.name}</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/logout" style="color:#130c0e">退出系统</a></dd>
                    </dl>
                    
                </li>
            </ul>
        </div>
 <div class="layui-footer">
            <p>当前登录用户：<a href="" target="_blank">${USER_SESSION.name}</a></p>
        </div>
        
        <div class="layui-side custom-admin">
            <div class="layui-side-scroll">

                <div class="custom-logo">

                </div>
                
                
                <ul id="Nav" class="layui-nav layui-nav-tree">
                    <li class="layui-nav-item">
                        <a href="">
                            <i class="layui-icon">&#xe609;</i>
                            <em>主页</em>
                        </a>
                        
                    </li>
                    
                    <li class="layui-nav-item">
                        <a href="${pageContext.request.contextPath}/student/findMe">
                            <i class="layui-icon">&#xe612;</i>
                            <em>个人上传论文</em>
                        </a>
                        <dl class="layui-nav-child">
                        </dl>
                    </li>





                </ul>

            </div>
        </div>
        
        
    <div>
	<div class=blank style="padding-top: 50px;padding-left: 230px; text-align:center;">
		<!-- <font color="gray" size="20">欢迎登录高校宿舍管理系统</font>  -->
		<img src="${pageContext.request.contextPath}/images/04.jpg" />
	</div>
	</div>

        <div class="layui-body">
             <div class="layui-tab app-container" lay-allowClose="true" lay-filter="tabs">
                <ul id="appTabs" class="layui-tab-title custom-tab"></ul>
                <div id="appTabPage" class="layui-tab-content"></div>
            </div>
        </div>

       

        <div class="mobile-mask"></div>
    </div>

    <script src="${pageContext.request.contextPath}/assets/layui.js"></script>
    <script src="index.js" data-main="home"></script>
</body>
</html>