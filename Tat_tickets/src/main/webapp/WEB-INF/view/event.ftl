<!DOCTYPE html>
<html lang="en">
<#include "includes/_headerUp.ftl">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link href="resources/css/styleMenu.css" rel="stylesheet">
<link href="resources/css/styleEvent.css" rel="stylesheet">

<#include "includes/_headerDown.ftl">
<#include "menu.ftl">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hockey Match Ticket Booking</title>
</head>
<body>

<div id="sections">
    <h2>Выберите секцию:</h2>
    <#list sections as section>
        <button onclick="showSeatLayout('${section}')">${section}</button>
    </#list>
</div>

<div id="seatLayout" style="display: none;">
    <h2>Выбор места - <span id="selectedSection"></span></h2>
    <div id="seats"></div>
    <button onclick="reserveSeats()">Забронировать</button>
    <button onclick="goBack()">К секциям</button>
</div>

<script>
    async function showSeatLayout(section) {
        try {
            const seatLayoutData = await getSeatLayout(section);

            document.getElementById('selectedSection').innerText = section;

            const seatsDiv = document.getElementById('seats');
            seatsDiv.innerHTML = '';

            seatLayoutData.forEach(seatRow => {
                const rowDiv = document.createElement('div');
                seatRow.forEach(seat => {
                    const seatButton = document.createElement('button');

                    seatButton.id = seat.split(" ")[1]
                    seatButton.innerText = seat.split(" ")[0];

                    seatButton.classList.add('seat');

                    if (seatButton.innerText.endsWith('R')) {
                        seatButton.disabled = true;
                        seatButton.classList.add('disabled');
                        seatButton.innerText = seat.split(" ")[0].replace('R', '');
                    } else {
                        seatButton.onclick = () => toggleSeatSelection(seatButton);
                    }

                    rowDiv.appendChild(seatButton);
                });
                seatsDiv.appendChild(rowDiv);
            });

            document.getElementById('sections').style.display = 'none';
            document.getElementById('seatLayout').style.display = 'block';
        } catch (error) {
            console.error(error.message);
        }
    }

    function getSeatLayout(section) {
        let json = [section, ${id}];
        return $.ajax({
            url: `/tickets/seatLayout`,
            type: 'GET',
            data: {json:json},
            dataType: 'json',
            success: function (data) {},
            error: function () {
                console.error(`Error fetching seat layout`);
            }
        });
    }

    function toggleSeatSelection(seatButton) {
        seatButton.classList.toggle('selected');
    }

    function reserveSeats() {
        const selectedSeats = document.querySelectorAll('.seat.selected');
        const seatIds = Array.from(selectedSeats).map(seat => seat.id);
        const seatNumbers = Array.from(selectedSeats).map(seat => seat.innerText);


        if (seatNumbers.length === 0) {
            alert("Please select at least one seat");
        } else {
            let jsonSeat = seatIds;
            let jsonGame = [${id}];
            console.log(jsonSeat)
            console.log(jsonGame)
            return $.ajax({
                url: `/tickets/seatLayout`,
                type: 'POST',
                data: {jsonSeat:jsonSeat, jsonGame:jsonGame},
                dataType: 'json',
                success: function () {
                    alert("Selected Seats: " + seatNumbers.join(', '));
                    window.location.replace("/tickets/profile");
                },
                error: function () {
                    console.error(`Error reserving seats`);
                }
            });
        }
    }

    function goBack() {
        document.getElementById('sections').style.display = 'block';
        document.getElementById('seatLayout').style.display = 'none';
    }
</script>
</body>
<#include "includes/_footer.ftl">
</html>
