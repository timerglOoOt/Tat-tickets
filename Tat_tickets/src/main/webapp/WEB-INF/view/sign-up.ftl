<<<<<<< HEAD
<#include "includes/_headerUp.ftl">
<title>Sign up</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Assistant:400,700">
<link rel="stylesheet" href="resources/css/styleSignIn.css">
<#include "includes/_headerDown.ftl">

<div class='login'>
    <div class='head'>
        <h1 class='auth'>Регистрация</h1>
    </div>
    <p class='msg'>Добро пожаловать!</p>
    <div class='form'>
        <form method="post">
            <input type="text" placeholder='Имя' class='text' name="firstName" required><br>
            <input type="text" placeholder='Фамилия' class='text' name="lastName" required><br>
            <input type="email" placeholder='Почта' class='text' name="email" required><br>
            <div class='password-container'>
                <input type="password" placeholder='Пароль' class='password' name="password" id="password" required>
                <img src="imgs/show.png" alt="Show Password" class='eye-img' onclick="togglePasswordVisibility()">
            </div>
            <#if errorMessage??>
                <div class="error_message">${errorMessage}</div>
            </#if>
            <a href="sign-in" class='forgot'>Авторизоваться</a>
            <input type="submit" class='btn-login' value="Зарегистрироваться">
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
=======
<#include "includes/_headerUp.ftl">
<title>Sign up</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Assistant:400,700">
<link rel="stylesheet" href="resources/css/styleSignIn.css">
<#include "includes/_headerDown.ftl">

<div class='login'>
    <div class='head'>
        <h1 class='auth'>Регистрация</h1>
    </div>
    <p class='msg'>Добро пожаловать!</p>
    <#if error??>
        <p class="error-message">${error}</p>
    </#if>
    <div class='form'>
        <form method="post">
            <input type="text" placeholder='Имя' class='text' name="firstName" required><br>
            <input type="text" placeholder='Фамилия' class='text' name="lastName" required><br>
            <input type="email" placeholder='Почта' class='text' name="email" required><br>
            <div class='password-container'>
                <input type="password" placeholder='Пароль' class='password' name="password" id="password" required>
                <img src="imgs/show.png" alt="Show Password" class='eye-img' onclick="togglePasswordVisibility()">
            </div>
            <a href="sign-in" class='forgot'>Авторизоваться</a>
            <input type="submit" class='btn-login' value="Зарегистрироваться">
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
>>>>>>> d23f224 (feat: add done project)
