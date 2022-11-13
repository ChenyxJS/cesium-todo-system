<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <c:choose>
        <c:when test="${success}">
            <title>${article.articleTitle}</title>
        </c:when>
        <c:otherwise>
            <title>未找到该文章</title>
        </c:otherwise>
    </c:choose>
    <style type="text/css">
        html,body{
            margin: 0 auto;
            padding: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
            background: #f6f6f6;
        }
        .content{
            width: 100%;
            height: 100%;
            overflow: auto;
        }
        .content .inner{
            width: 100%;
            box-sizing: border-box;
            padding: 5px 10px;
            background: #ffffff;
        }
        .tip-view{
            width: 100%;
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #565656;
        }
        p{
            width: 100%;
            font-size: 14px;
        }
        .wscnph{
            width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${success}">
        <div class="content">
            <div class="inner">
                    ${article.articleContent}
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="tip-view">
            <span>未找到该文章</span>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
