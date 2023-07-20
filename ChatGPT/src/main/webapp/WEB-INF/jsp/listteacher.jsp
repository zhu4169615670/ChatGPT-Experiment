<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/view.css"/>
    <title>毕业答辩评分系统</title>
</head>
<body class="layui-view-body">
<form method="post" action="${pageContext.request.contextPath}/teacher/findAll" id="listform">
    <!--用于标记当前页数的input，默认为1，跳转时-->
    <input type="hidden" name="pageIndex" value="1">
    <div class="layui-content">
        <div class="layui-page-header">
            <div class="pagewrap">
                <h2 class="title">教师管理</h2>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-body">


                    <div class="layui-form layui-form-item">
                        <div class="layui-inline">
                            <!-- 查询name, sex, budld_id, dorm_no -->
                            <a href="${pageContext.request.contextPath}/teacher/toAdd" class="layui-btn "><i
                                    class="layui-icon">&#xe654;</i>添加</a>

                            <div class="layui-form-mid">教师姓名:</div>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="text" value="${no}" autocomplete="off" class="layui-input" name="no">
                            </div>


                            <button class="layui-btn layui-btn-blue">查询</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>

                        </div>
                    </div>
                    <div class="form-box">

                        <!--表内容放这里-->
                        <table id="table_content" class="layui-table" align="center">
                            <tr>
                                <th>教师姓名</th>
                                <th>教师职称</th>
                                <th>教师年龄</th>
                                <th>教师爱好</th>
                                <th>登录名</th>
                                <th>密码</th>
                                <th>操作</th>

                            <c:forEach var="stu" items="${list}">
                                <tr>
                                    <td style="text-align:center; padding-left:20px;">${stu.no}</td>
                                    <td align="center">${stu.name}</td>
                                    <td align="center">${stu.age}</td>
                                    <td align="center">${stu.habby}</td>
                            <td align="center">${stu.uname}</td>
                            <td align="center">${stu.pwd}</td>
                                <td align="center">
                                    <a href="${pageContext.request.contextPath}/teacher/toUpdate?id=${stu.id}"
                                       class="layui-btn layui-btn-blue">修改</a>
                                    <a href="${pageContext.request.contextPath}/teacher/del?id=${stu.id}"
                                       class="layui-btn layui-btn-red" >删除</a>
                                </td>

                                </tr>
                            </c:forEach>
                        </table>

                        <!--判断并输出上一页、下一页按钮-->
                        <ul>
                            <!--获得后端传来的值-->
                            <li>共${totalCount}条记录&nbsp;&nbsp; ${currentPageNo}/${totalPageCount}页</li>
                            <c:if test="${currentPageNo  > 1}">
                                <a href="javascript:page_nav(document.forms[0],1);">首页</a>
                                <a href='javascript:page_nav(document.forms[0],${currentPageNo-1});'>上一页</a>
                            </c:if>
                            <c:if test='${currentPageNo  < totalPageCount }'>
                                <a href='javascript:page_nav(document.forms[0],${currentPageNo+1 });'>下一页</a>
                                <a href='javascript:page_nav(document.forms[0],${totalPageCount});'>最后一页</a>
                            </c:if>
                            &nbsp;&nbsp;
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<script src="${pageContext.request.contextPath}/assets/layui.all.js"></script>
<script>
    //获取表单并提交，此处也可以使用ajax实现，不过多赘述
    function page_nav(frm, num) {
        //改变name 为 pageIndex的input的值
        frm.pageIndex.value = num;
        frm.submit();
    }
</script>
<script>
    //单个删除
    function Del(id) {
        if (confirm("您确定要删除吗?")) {
            return true;
        } else {
            return false;
        }
    }

    // 核心：拼接完整的html格式文档并填充数据
    //使用outerHTML属性获取整个table元素的HTML代码， 包括根标签<table></table>
    // 自定义封装html格式文档<html><head></head><body></body></html>
    // 设置字符集，告诉浏览器以utf8方式解析，避免乱码<meta charset='utf-8'/>
    // 获取table数据并填充到自定义的html格式文档中
    var table_content = document.querySelector("#table_content").outerHTML;
    var html = "<html><head><meta charset='utf-8' /></head><body>" + table_content + "</body></html>";

    // 实例化一个Blob对象，
    // param1：包含文件内容的数组，
    // param2：包含文件类型属性的对象
    var blob = new Blob([html], {
        type: "application/vnd.ms-excel"
    });
    var output_table = document.querySelector("#output_table");
    // 利用URL.createObjectURL()方法为a元素生成blob URL
    output_table.href = URL.createObjectURL(blob);
    // 设置文件名，低级浏览器不支持
    output_table.download = "学生入住信息表.xls";


</script>


</body>
</html>