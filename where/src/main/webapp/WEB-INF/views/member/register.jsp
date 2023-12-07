<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | Registration Page</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- Bootstrap 3.3.4 -->
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- Font Awesome Icons -->
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet"
	type="text/css" />
<!-- iCheck -->
<link href="/resources/plugins/iCheck/square/blue.css" rel="stylesheet"
	type="text/css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>



<body class="login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="/member/login"><b>어디야</b>?</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">Sign in to start your session</p>


			<form action="/member/register" method="post">
				<div class="form-group has-feedback">
					<input type="text" id="name" name="name" class="form-control"
						placeholder="이름을 입력해주세요"> <span
						class="glyphicon glyphicon-user form-control-feedback"></span>
					<c:if  test="${errors.name}"> 이름을 입력하세요.</c:if>
				</div>
				<div class="form-group has-feedback">
					<input type="text" id="niname" name="niname" class="form-control"
						placeholder="닉네임을 입력해주세요"> <span
						class="glyphicon glyphicon-user form-control-feedback"></span>
					<c:if test="${errors.niname}"> 닉네임을 입력하세요.</c:if>
				</div>
				<div class="form-group has-feedback">
					<input type="text" id="email" name="email" class="form-control"
						placeholder="이메일을 입력해주세요"> <span
						class="glyphicon glyphicon-user form-control-feedback"></span>
					<c:if test="${errors.email}"> 이메일 입력하세요.</c:if>
					<c:if test="${errors.duplicateUid}"> 이미 사용중인 이메일입니다.</c:if>
				</div>

				<div class="form-group has-feedback">
					<input type="password" id="pw" name="pw" class="form-control"
						placeholder="비밀번호를 입력해주세요"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
					<c:if test="${errors.pw}"> 비밀번호를 입력하세요.</c:if>
				</div>

				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox"> I agree to the <a
								href="#">terms</a>
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button id="submit" type="submit"
							class="btn btn-primary btn-block btn-flat">가입</button>
					</div>
					<!-- /.col -->
				</div>
			</form>
			<div class="social-auth-links text-center">
				<p>- OR -</p>
				<a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i
					class="fa fa-facebook"></i> Sign up using Facebook</a> <a href="#"
					class="btn btn-block btn-social btn-google btn-flat"><i
					class="fa fa-google-plus"></i> Sign up using Google+</a>
			</div>

			<a href="/member/login" class="text-center">I already have a
				membership</a>
		</div>
		<!-- /.form-box -->
	</div>
	<!-- /.register-box -->



	<!-- jQuery 2.1.4 -->
	<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.2 JS -->
	<script src="/resources/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<!-- iCheck -->
	<script src="/resources/plugins/iCheck/icheck.min.js"
		type="text/javascript"></script>
	<script> 
	 
	$("#submit").on("click", function(){
		if($("#email").val()==""){
			alert("아이디를 입력해주세요.");
			$("#email").focus();
			return false;
		}
		if($("#pw").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#pw").focus();
			return false;
		}
		if($("#niname").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#niname").focus();
			return false;
		}
		if($("#name").val()==""){
			alert("성명을 입력해주세요.");
			$("#name").focus();
			return false;
		} else {
			alert("회원가입 되셨습니다.");
		}
	});
	
		
	
})
 
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
</body>
</html>