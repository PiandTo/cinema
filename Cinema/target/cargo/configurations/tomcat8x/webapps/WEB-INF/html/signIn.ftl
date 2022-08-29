<#--<!DOCTYPE html>-->
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <title>Cinema Admin Panel</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/styles.css" type="text/css"><!-- Google Tag Manager -->
    <style>
        h1 {
            padding: 40px 0;
        }
        .wrapper {
            width:  100%;
            margin: 0 auto;
            padding:  0;
            background: #ebebf5;
        }
        .inside-wrapper {
            background: white;
            width: 1000px;
            margin: 0 auto;
            padding: 0 20px 20px 20px;
            min-height: 1000px;
        }

        .cards {
            display:  flex;
        }

        .wrapper .picture {
            opacity: 0.5;
            height: 400px;
            background: url('/images/okko.webp') center;
            background-size: 800px 600px;
        }

        .wrapper input {
            width:  500px;
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
        .wrapper .btn {
            display: block;
            width:  130px;
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
        .card-img-top {
            opacity: 0.4;
        }
        .card-img-top:hover {
            opacity: 1;
        }
        .card {
            margin:  20px;
        }
        .transition {
            transition: 3s;
        }
        #navbarNav {
            width: 1000px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <#include "components/homeNav.ftl">
    <div class="inside-wrapper">
        <div class="picture"></div>
        <br>
        <h2 class="text-primary">
            Sign In
        </h2>
        <form action="profile" method="post">
            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Enter password">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
    <#include "components/footer.ftl">
</div>
</body>
</html>

