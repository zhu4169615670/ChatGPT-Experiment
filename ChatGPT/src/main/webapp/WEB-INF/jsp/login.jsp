<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>毕业答辩评分系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login1.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login2style.css">
</head>

<body>
    <div id="formContainer" class="dwo">
    <!-- 
        <div class="formLeft">
            <img src="${pageContext.request.contextPath}/images/bg.png">
        </div>
         -->
        <div class="formRight">
            <!-- Login form -->
            
            <form id="login" method="post" action="${pageContext.request.contextPath}/login">
                <header>
                    <h1>毕业答辩评分系统</h1>
                </header>
                <section>
                    <label>
                        <p>用户名:</p>
                        <input type="text" placeholder="" name="no">
                        <div class="border"></div>
                    </label>
                    <label>
                        <p>密码:</p>
                        <input type="password" placeholder="" name="password">
                        <div class="border"></div>
                       
                    </label>

                    <label>
                        <p>请选择用户角色:</p>
                        <select name="roleid">
                            <option value="2">学生</option>
                            <option value="3">老师</option>
                            <option value="4">管理员</option>
                        </select>
                        <div class="border"></div>

                    </label>
                    <button type="submit" style="width: 50%">登 录</button>
                     <button type="reset" style="width: 50%" onclick="window.location.href='toReg'">注 册</button>
                </section>
               <%--  <footer>
                <h6 style="text-align: center;color:#fffffb;text-shadow: 5px 5px 10px #f1f1f1;" class="text">${message}</h6>
            </footer> --%>
            </form>

            <!-- Register form -->
            <%--登录提示信息--%>
                <span style="text-align: center;color: red">${message}</span>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/assets/lgjquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/lgscript.js"></script>

<script type="text/javascript">
    /**
     * 登录超时 展示区跳出iframe
     */
    var _topWin = window;
    while (_topWin != _topWin.parent.window) {//判断是不是顶级页面
        _topWin = _topWin.parent.window;
    }
    if (window != _topWin)
        _topWin.document.location.href = '${pageContext.request.contextPath}/login.jsp';
</script>
</body>
</html>