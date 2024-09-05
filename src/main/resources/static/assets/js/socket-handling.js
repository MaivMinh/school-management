const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/websocket-comment-connect'
});


stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

stompClient.activate();

function disconnect() {
    stompClient.deactivate();
    console.log("Disconnected");
}

// Hàm thực hiện fetch thông tin của User rồi bỏ vào sessionStorage.
async function fetchUser() {
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

export default stompClient;