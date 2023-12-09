<#include "includes/_headerUp.ftl">
<title>Календарь матчей</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.0/fullcalendar.min.css"/>
<link href="resources/css/styleMenu.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ru.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.0/fullcalendar.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.0/locale/ru.js"></script>
<#include "includes/_headerDown.ftl">
<#include "menu.ftl">

<div id="calendar"></div>

<style>
    #calendar {
        max-width: 1200px; /* Set the maximum width of the calendar */
        margin: 0 auto;   /* Center the calendar */
    }
</style>

<script>
    $(document).ready(function () {
        $('#calendar').fullCalendar({
            locale: 'ru',
            events: [
                <#list matches as match>
                {
                    title: "${match.teams}",
                    start: "${match.date}",
                    numberOfSeats: "${match.numberOfSeats}",
                    url: "${match.url}"
                }<#if match_has_next>,</#if>
                </#list>
            ],
            eventClick: function (calEvent, jsEvent, view) {
                window.location.href = calEvent.url;
            }
        });
    });
</script>

<#include "includes/_footer.ftl">
