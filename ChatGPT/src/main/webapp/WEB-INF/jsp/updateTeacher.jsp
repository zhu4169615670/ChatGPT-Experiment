<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改教师信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/view.css"/>
</head>
<body>
<div class="layui-content">
    <div class="layui-row">
        <div class="layui-card">
            <div class="layui-card-header">修改教师信息</div>

            <form class="layui-form layui-card-body" action="${pageContext.request.contextPath}/teacher/doUpdate" method="post">

                <input type="hidden" name="id" value="${list.id}">

                <div class="layui-form-item">
                    <label class="layui-form-label">教师姓名：</label>
                    <div class="layui-input-block">
                        <input type="text"  readonly= readonly name="no" required value="${list.no}" lay-verify="required" placeholder="请输入教师姓名" autocomplete="off" class="layui-input" style="width:190px">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">教师职称：</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" required   value="${list.name}"  lay-verify="required" placeholder="请输入教师职称" autocomplete="off" class="layui-input " style="width:190px">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">教师年龄：</label>
                    <div class="layui-input-block">
                        <input type="text" name="age" required  value="${list.age}"   lay-verify="required" placeholder="请输入教师年龄" autocomplete="off" class="layui-input " style="width:190px">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">教师爱好：</label>
                    <div class="layui-input-block">
                        <input type="text" name="habby" required    value="${list.habby}"   lay-verify="required" placeholder="请输入教师爱好" autocomplete="off" class="layui-input " style="width:190px">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">登录名：</label>
                    <div class="layui-input-block">
                        <input type="text" name="uname" required   value="${list.uname}"    lay-verify="required" placeholder="请输入登录名" autocomplete="off" class="layui-input " style="width:190px">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label">密码：</label>
                    <div class="layui-input-block">
                        <input type="text" name="pwd" required  value="${list.pwd}"   lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input " style="width:190px">
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="submit" class="layui-btn layui-btn-blue" lay-submit lay-filter="formDemo">提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>

            </form>

        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/assets/layui.all.js"></script>
<script>
    var form = layui.form
            ,layer = layui.layer;
</script>
<script src="${pageContext.request.contextPath}/assets/jquery-3.4.1.js"></script>
</body>
</html>