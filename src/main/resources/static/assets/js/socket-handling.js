let brokenLocalURL = 'ws://localhost:8080/websocket-comment-connect';
let brokenAzureURL = 'wss://passio-school.azurewebsites.net/websocket-comment-connect';

const stompClient = new StompJs.Client({
    brokerURL: brokenLocalURL
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
export default stompClient;
