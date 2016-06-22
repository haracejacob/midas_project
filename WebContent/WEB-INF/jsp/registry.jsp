<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
	<script src="/midasWeb/js/jquery-2.2.4.min.js"></script>
    <title>Signin for Sandang</title>

    <!-- Bootstrap core CSS -->
    <link href="/midasWeb/js/node_modules/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/midasWeb/less/style.less" type="text/css" rel="stylesheet/less">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="form-signin-background">

<div class="container">
    <form class="form-signin is-Transformed" action="signupProcess" method="POST" style="height:500px;">
        <div class="valign">
            <h1 class="form-signin-heading">Sign up</h1>
            <p class="form-signin-description">몇가지 입력으로 회원이 되실 수 있습니다!</p>
            <p id="err_msg" class="form-signin-description"></p>

            <label for="inputEmail" class="sr-only">Email</label>
            <input type="email" name="user_email" id="user_email" class="form-control" placeholder="Email" required autofocus value="${user_email}">
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" name="user_passwd" id="user_passwd" class="form-control" placeholder="Password" required>
            <label for="inputName" class="sr-only">Name</label>
            <input type="name" name="user_name" id="user_name" class="form-control" placeholder="Name" required>
            <label for="inputPhone" class="sr-only">Phone</label>
            <input type="number" name="user_phone" id="user_phone" class="form-control" placeholder="Phone" required>
            
            <button class="btn btn-lg btn-primary btn-block sadang-btn" type="submit">Register</button>
        </div>
    </form>
</div> <!-- /container -->

<script src="/midasWeb/js/node_modules/less/dist/less.min.js" type="text/javascript"></script></body>
</html>

<script>
$(document).ready(function(){
    $(".sadang-btn").click(function(){
        // 만약 데이터가 잘못되었으면
        $(".alert").show();
    });
    
    $.urlParam = function(name){
    	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    	return results[1] || 0;
    };

    msg = $.urlParam('err_msg');
});
</script>
