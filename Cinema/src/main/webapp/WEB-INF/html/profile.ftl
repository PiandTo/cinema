<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv='content-type' content='text/php; charset=windows-1251'>
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <title>Profile page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/styles.css" type="text/css"><!-- Google Tag Manager -->
    <!-- End Google Tag Manager -->
    <style>
        body {
            box-sizing: border-box;
        }

        #Profile  {
            margin: 0 auto;
            display: block;
            width:  850px;
            background: white;
        }
        form, h1 {
            width: 300px;
            margin: 0 auto;
        }

        h1 {
            margin: 40px auto;
        }

        .avatar {
            width: 200px;
        }

        .avatar img {
            width: 100%;
        }

        .avatar button {
            text-align: center;
            display: block;
            margin: 0 auto;
        }

        .wrapper {
            display: flex;
            flex-direction: column;
            width: 100%;
            margin: 0 auto;
            padding: 0;
            background: #ebebf5;
        }

        .table1, .table2 {
            display: flex;
            width: 610px;
        }

        .table1 div, .table2 div {
            width: 200px;
            display: block;
            border: 1px solid grey;
            border-right: none;

        }

        .table1 .right-border, .table2 .right-border {
            border-right: 2px solid grey;
        }

        .table1 p, .table2 p {
            padding: 2px;
            margin: 0;
            text-align: center;
        }

        .main-chars {
            font-weight: bold;
        }

        .main-chars p {
            margin-bottom: 5px;
        }

        .main-chars span {
            padding-left: 10px;
            font-weight: normal;
        }

        .table1 .colored, .table2 .colored {
            background: lightgray;
            box-sizing: border-box;

        }

        .table1 .right-border .colored, .table2 .right-border .colored {
            width: 197px;
            display: block;
        } h2{
              padding: 40px 0;
          }
        .inside-wrapper {
            background: white;
            width: 1000px;
            margin: 0 auto;
            padding: 0 20px 20px 20px;
            min-height: 1000px;
        }
        .wrapper input {
            width:  180px;
            margin: 20px;
        }
        .wrapper label {
            width:  100px;
            align-self: center;
        }
        .wrapper table {
            width: 600px;
        }
        .form-group  {
            display: flex;
            flex-direction: row;
            margin: 15px 0;
        }
        .wrapper .picture {
            opacity: 0.5;
            height: 400px;
            background: url('/images/profile.webp') center;
            background-size: 1000px 600px;
        }
        footer {
            height:  60px;
        }
        footer p {
            padding-left: 10px;
            padding-top: 18px;
            width: 1000px;
            margin: 0 auto;
        }
        #navbarNav {
            width: 1000px;
            margin: 0 auto;
        }
        form b {
            color: red;
            padding-left: 100px;
        }
        .btn-logout input {
            width: 80px;
        }
        .wrapper_user {
            display: flex;
        }
        .main-chars {
            width:  50%;
            margin: 20px 0;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <#if user.isAdmin == true>
        <#include "components/adminNav.ftl">
    <#else>
        <#include "components/userNav.ftl">
    </#if>
    <div class="inside-wrapper">
        <div class="picture"></div>
    <h2 class="text-primary">
        Profile page
    </h2>
        <div class="wrapper_user">
            <div class="main-chars">
                <p>Name: <span>${user.firstName}</span></p>
                <p>Surname: <span>${user.lastName}</span></p>
                <p>E-mail:<span>${user.email}</span></p>
                <#if user.isAdmin == true>
                    <p>Права: <span>Администратор</span></p>
                <#else>
                    <p>Права: <span>Пользователь</span></p>
                </#if>
            </div>
            <div class="avatar">
                <#if user.avatar??>
                    <a href="/avatars/${user.id?c}/${user.avatar}" target="_blank">
                        <img src="/avatars/${user.id?c}/${user.avatar}" alt="User avatar" width="200">
                    </a>
                <#else>
                    <a href="/avatars/user_avatar.gif" target="_blank">
                        <img src="/avatars/user_avatar.gif" alt="User avatar" width="200">
                    </a>
                </#if>
                <form method="post" action="/uploadAvatar/${user.id?c}" enctype="multipart/form-data">
                    <input type="file" name="avatar_to_upload"/>
                    <input type="submit"/>
                </form>
                </form>
            </div>
        </div>

        <div>
            <div class="table1">
                        <div>
                            <div class="colored"><p>Date</p></div>
                            <#if logs??>
                                <#list logs as item>
                                    <p>${item.date}</p>
                                </#list>
                            </#if>
                        </div>
                        <div>
                            <div class="colored"><p>Time</p></div>
                            <#if logs??>
                                <#list logs as item>
                                    <p>${item.time}</p>
                                </#list>
                            </#if>
                        </div>
                        <div class="right-border">
                            <div class="colored"><p>IP</p></div>
                            <#if logs??>
                                <#list logs as item>
                                    <#if item.ip == '0:0:0:0:0:0:0:1'>
                                        <p>127.0.0.1</p>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                    </div>
            <br><br>
            <h2>Uploaded files</h2>
            <br>
            <div class="table2">
                <div>
                    <div class="colored"><p>File name</p></div>
                    <#if lstAvatar??>
                        <#list lstAvatar as avatar>
                            <div>
                                <p>
                                    <a href="/avatars/${user.id?c}/${avatar.avatarUrl}" target="_blank">${avatar.avatarUrl}</a>
                                </p>
                            </div>
                        </#list>
                    </#if>
                </div>
                <div>
                    <div class="colored"><p>Size</p></div>
                    <#if lstAvatar??>
                        <#list lstAvatar as avatar>
                            <div> <p>${avatar.avatarFileSize}</p></div>
                        </#list>
                    </#if>
                </div>
                <div class="right-border">
                    <div class="colored"><p>MIME</p></div>
                    <#if lstAvatar??>
                        <#list lstAvatar as avatar>
                            <#if avatar.avatarMimeType??>
                                <div> <p>${avatar.avatarMimeType}</p></div>
                            <#else>
                                <div><p>n/a</p></div>
                            </#if>
                        </#list>
                    </#if>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>