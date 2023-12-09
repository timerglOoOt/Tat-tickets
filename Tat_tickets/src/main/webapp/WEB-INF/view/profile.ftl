<<<<<<< HEAD
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
=======
<#include "includes/_headerUp.ftl">
<title>Профиль</title>

<link href="resources/css/styleMenu.css" rel="stylesheet">
<link href="resources/css/styleProfile.css" rel="stylesheet" >
<#include "includes/_headerDown.ftl">
<#include "menu.ftl">

<div class="container">
    <div class="center-content">
        <div class="container">
            <div class="tabs">
                <button class="tab-btn" onclick="openTab('profileTab')">Профиль</button>
                <button class="tab-btn" onclick="openTab('ticketsTab')">Билеты</button>
            </div>

            <div id="profileTab" class="tab-content">
                <div id="profile" class="white-container">
                    <#if error??>
                        <p class="error-message">${error}</p>
                    </#if>
                    <div class="user-info">
                        <img src="http://localhost:8080/tickets/avatarServlet" alt="Avatar"  class="user-avatar">
                    </div>
                    <div class="user-info-text">
                        <div class="user-info">
                            <label for="firstName">Имя:</label>
                            <span id="firstName">${user.firstName}</span>
                        </div>
                        <div class="user-info">
                            <label for="lastName">Фамилия:</label>
                            <span id="lastName">${user.lastName}</span>
                        </div>
                        <div class="user-info">
                            <label for="email">Email:</label>
                            <span id="email">${user.email}</span>
                        </div>
                    </div>

                    <div class="profile-buttons">
                        <button id="changeProfileBtn" class="btn" onclick="openProfileModal()">Изменить профиль</button>

                        <form action="avatarServlet" method="post" enctype="multipart/form-data">
                            <label for="file" class="btn">Загрузить аватар</label>
                            <input type="file" id="file" name="file" accept="image/png, image/jpeg" style="display: none;">
                            <input type="submit" value="Изменить аватар" name="submit" id="changeAvatarBtn">
                        </form>
                    </div>

                    <div id="profileModal" class="modal">
                        <div class="modal-content">
                            <form method="post">
                                <label for="firstName">First Name:</label>
                                <input type="text" id="firstNameInput" name="firstName" value="${user.firstName}">
                                <label for="lastName">Last Name:</label>
                                <input type="text" id="lastNameInput" name="lastName" value="${user.lastName}">

                                <button type="submit" name="action" value="changeProfile">Change</button>
                            </form>
                            <span class="close" onclick="closeProfileModal()">&times;</span>
                        </div>
                    </div>
                </div>
            </div>


            <div id="ticketsTab" class="tab-content">
                <h2>Ваши билеты</h2>

                <h3>Активные билеты</h3>
                <#list activeBookings as booking>
                    <div class="booking-item">
                        <p>Номер брони: ${booking.id}</p>
                        <p>Дата матча: ${booking.bookingDate?string("dd/MM/yyyy HH:mm:ss")}</p>
                        <p>Матч: ${booking.gameName}</p>
                    </div>
                </#list>

                <#if activeBookings?size == 0>
                    <p>Активных билетов не нашлось.</p>
                </#if>

                <h3>Истекшие билеты</h3>
                <#list expiredBookings as booking>
                    <div class="booking-item">
                        <p>Номер брони: ${booking.id}</p>
                        <p>Дата матча: ${booking.bookingDate?string("dd/MM/yyyy HH:mm:ss")}</p>
                        <p>Матч: ${booking.gameName}</p>
                    </div>
                </#list>

                <#if expiredBookings?size == 0>
                    <p>Истекших билетов не нашлось.</p>
                </#if>
            </div>


            <div class="divider"></div>
        </div>
    </div>
</div>

<script>
    function openTab(tabName) {
        var i, tabContent;
        tabContent = document.getElementsByClassName("tab-content");
        for (i = 0; i < tabContent.length; i++) {
            tabContent[i].style.display = "none";
        }
        document.getElementById(tabName).style.display = "block";
    }

    function openProfileModal() {
        document.getElementById("profileModal").style.display = "block";
    }

    function closeProfileModal() {
        document.getElementById("profileModal").style.display = "none";
    }

    openTab("profileTab");
</script>

<#include "includes/_footer.ftl">
>>>>>>> d23f224 (feat: add done project)
