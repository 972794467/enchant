<!DOCTYPE html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>admin</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/navbar-fixed-top.css" rel="stylesheet">
    <link rel="stylesheet" href="css/music_list.css" />
  </head>
  <body>
  <%!
      private String name;
  %>
  <%

      Cookie cookies[]=cookies = request.getCookies();
      for (int i=0;i<cookies.length;i++){
          if(cookies[i].getName().equals("name")){
              name=cookies[i].getValue();
          }
      }
      if(name == null){
          response.sendRedirect("login.html");
      }
  %>

    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon- bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand">enchant</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li id="page-home" class="page active" onclick="changePage(this,1)"><a href="#home">首页</a></li>
            <li id="page-notice" class="page" onclick="changePage(this,2)"><a href="#notice">公告</a></li>
            <li id="page-feedback" class="page" onclick="changePage(this,3)"><a href="#feedback ">反馈</a></li>
            <li id="page-user-list" class="page" onclick="changePage(this,6)"><a href="#user-list ">用户</a></li>
            
            <li id="navbar-music" class=" dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">歌曲<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li id="page-music-list" class=" page dropdown-li" onclick="changePage(this,4)"><a href="#music-list">歌曲列表</a></li>
                <li id="page-music-upload" class=" page dropdown-li" onclick="changePage(this,5)"><a href="#music-upload">歌曲上传</a></li>
              </ul>
            </li>
            
            <!--<li id="navbar-user" class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">用户<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li id="page-user-info" class=" page dropdown-li" onclick="changePage(this,6)"><a href="#user-info">用户信息</a></li>
                <li id="page-user-push" class=" page dropdown-li" onclick="changePage(this,8)"><a href="#user-push">消息推送</a></li>
              </ul>
            </li>-->
          </ul>
          <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">

                  <u><span id="login_user">
                      <%=name %></span>
                  </u>
                  <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li class=" page dropdown-li"><a href="/enchant/getMyinfo.action">我的信息</a></li>
                <li class=" page dropdown-li"><a href="/enchant/logout.action">注销</a></li>
              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
   <div id="page-content">
       
   </div>
       
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/change_page.js" ></script>
    <script type="text/javascript" src="js/lib.js" ></script>
    <script type="text/javascript" src="js/vue.min.js" ></script>
    <script type="text/javascript" src="js/page.js" ></script>
    <script>
      var strLocalUrl = window.location.hash;
      var arrPageInfo = switchPage(strLocalUrl);
      var activePageLi = document.getElementById(arrPageInfo['li_id']);
      $("#page-content").load("page/home.html");
      changePage(activePageLi,arrPageInfo['page_num']);
    </script>
   
  </body>
</html>