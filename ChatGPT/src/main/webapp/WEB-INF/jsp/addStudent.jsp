<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增学生信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/view.css"/>
</head>
<body>
<div class="layui-content">
    <div class="layui-row">
        <div class="layui-card">
            <div class="layui-card-header">新增学生信息</div>
            
            <form class="layui-form layui-card-body" enctype="multipart/form-data" action="${pageContext.request.contextPath}/student/doAdd" method="post">



                <div class="layui-form-item">
                    <label class="layui-form-label">学生姓名：</label>
                    <div class="layui-input-block">
                        <input type="text" name="no" required lay-verify="required" placeholder="请输入学生姓名" autocomplete="off" class="layui-input" style="width:190px">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">学生班级：</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" required    lay-verify="required" placeholder="请输入学生班级" autocomplete="off" class="layui-input " style="width:190px">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">学生年龄：</label>
                    <div class="layui-input-block">
                        <input type="text" name="age" required    lay-verify="required" placeholder="请输入学生年龄" autocomplete="off" class="layui-input " style="width:190px">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">登录名：</label>
                    <div class="layui-input-block">
                        <input type="text" name="uname" required    lay-verify="required" placeholder="请输入登录名" autocomplete="off" class="layui-input " style="width:190px">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label">密码：</label>
                    <div class="layui-input-block">
                        <input type="text" name="pwd" required    lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input " style="width:190px">
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
<script  >
function changer(){

    var jb = $("input[name='jb']").val();

    if(jb==undefined||jb==null||jb==''){
        alert('请填写基本工资');
        return;
    }
    var yl = $("input[name='yl']").val((Number(jb)*0.08).toFixed(2));
    var ylbx = $("input[name='ylbx']").val((Number(jb)*0.02).toFixed(2)+3);
    var sy = $("input[name='sy']").val((Number(jb)*0.005).toFixed(2));
    var gjj = $("input[name='gjj']").val((Number(jb)*0.08).toFixed(2));






}


//乘法
function accMul(arg1,arg2) {
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{m+=s1.split(".")[1].length}catch(e){}
    try{m+=s2.split(".")[1].length}catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}
</script>
<script src="${pageContext.request.contextPath}/assets/layui.all.js"></script>
<script>
    var form = layui.form
            ,layer = layui.layer;
</script>
<script src="${pageContext.request.contextPath}/assets/jquery-3.4.1.js"></script>
</body>
</html>