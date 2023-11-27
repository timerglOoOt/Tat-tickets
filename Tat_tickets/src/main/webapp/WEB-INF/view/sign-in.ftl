<#include "includes/_headerUp.ftl">
<title>Sign in</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Assistant:400,700">
<link rel="stylesheet" href="resources/css/styleSignIn.css">
<#include "includes/_headerDown.ftl">

<div class='login'>
    <div class='head'>
        <h1 class='auth'>Авторизация</h1>
    </div>
    <p class='msg'>Добро пожаловать!</p>
    <div class='form'>
        <form method="post">
            <input type="text" placeholder='Почта' class='text' name="email" required><br>
            <div class='password-container'>
                <input type="password" placeholder='Пароль' class='password' name="password" id="password" required>
                <img src="imgs/show.png" alt="Show Password" class='eye-img' onclick="togglePasswordVisibility()">
            </div>
            <input type="submit" class='btn-login' value="Войти">
            <a href="sign-up" class='forgot'>Создать новый аккаунт?</a>
        </form>
    </div>
</div>

<#include "includes/_footer.ftl">

<script>
    function togglePasswordVisibility() {
        const passwordField = document.getElementById('password');
        const eyeImg = document.querySelector('.eye-img');

        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            eyeImg.src = 'imgs/hide.png';
        } else {
            passwordField.type = 'password';
            eyeImg.src = 'imgs/show.png';
        }
    }
</script>
