<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="로그인" />

<%@ include file="../../part/head.jspf"%>

<h1>${pageTitle}</h1>

<div>
	<script>
		let DoLoginForm__submited = false; // 안보내는 걸 가정으로 시작(true로 바꾸면 보냄)
		function DoJoinForm__submit(form) {

			if (DoLoginForm__submited) {
				alert('처리중 입니다.');
				return;
			}

			form.loginId.value = form.loginId.value.trim(); // 공백이 있더라도 넘어가지 않게

			if (form.loginId.value.length == 0) {
				alert('로그인 아이디를 입력해주세요.')
				form.loginId.focus();

				return;
			}

			form.loginPw.value = form.loginPw.value.trim();

			if (form.loginPw.value.length == 0) {
				alert('로그인 비밀번호를 입력해주세요.')
				form.loginPw.focus();

				return;
			}

			form.submit();
			DoLoginForm__submited = true; // 한번 submit 으로 보내면 true로 변경(중복입력 방지)
		}
	</script>

	<form action="doLogin" method="POST"
		onsubmit="DoLoginForm__submit(this); return false;">
		<hr />
		<div>
			<div>로그인 아이디</div>
			<div>
				<input name="loginId" type="text" maxlength="50"
					placeholder="로그인 아이디를 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>
			<div>로그인 비번</div>
			<div>
				<input name="loginPw" type="password" maxlength="50"
					placeholder="로그인 비밀번호를 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>
			<div>로그인</div>
			<div>
				<input type="submit" value="로그인" />
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</form>
</div>


<%@ include file="../../part/foot.jspf"%>