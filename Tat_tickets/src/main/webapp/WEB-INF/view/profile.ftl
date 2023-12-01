<#include "includes/_headerUp.ftl">
    <title>Профиль</title>

    <link href="resources/css/styleMenu.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/styleProfile.css">
<#include "includes/_headerDown.ftl">
<#include "menu.ftl">

<div class="container">
    <div class="center-content">
        <div class="container">
            <div id="profile" class="white-container">
<#--                  <#if user.avatarId??>-->
<#--                    <img class="avatar" width="600px" src="/files/${user.avatarId}"/>-->
<#--                <#else>-->
<#--                    <img width="600px" src="https://img.freepik.com/free-vector/teacher-standing-near-blackboard-holding-stick-isolated-flat-vector-illustration-cartoon-woman-character-near-chalkboard-pointing-alphabet_74855-8600.jpg" >-->

                <img class="user-avatar" alt="IMAGE" src="no-avatar.png"/>
                <div class="user-info-text">
                    <div class="user-info">${user.firstName}</div>
                    <div class="user-info">${user.lastName}</div>
                    <div class="user-info">${user.email}</div>
                </div>
            </div>
            <div class="divider"></div>
        </div>
    </div>
</div>

<#include "includes/_footer.ftl">