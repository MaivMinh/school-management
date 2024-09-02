const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/websocket-connect'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/comments', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/create-comment",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect());
    $("#disconnect").click(() => disconnect());
    $("#send").click(() => sendName());
});

// Hàm thực hiện fetch thông tin của User rồi bỏ vào sessionStorage.
async function fetchUser () {
    // Define the API URL
    const apiUrl = 'http://localhost:8080/api/v1/user';
    // Function to fetch user information
    try {
        const response = await fetch(apiUrl, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
        });
        // Check if the response is ok (status code 200-299)
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        // Parse the JSON response
        const userData = await response.json();
        sessionStorage.setItem("user", JSON.stringify(userData));

        // Handle the fetched user data here (e.g., update the UI)
        // For example, you can display user data in the console or DOM
        //document.getElementById('user-info').innerText = JSON.stringify(userData, null, 2);

    } catch (error) {
        console.error('Error fetching user information:', error);
    }
}
fetchUser();
