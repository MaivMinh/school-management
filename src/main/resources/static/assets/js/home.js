// Khi user họ thực hiện login thành công thì chắc chắn sẽ có user bên trong session storage. user object này sẽ tồn tại đến hết session.
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
    } catch (error) {
        console.error('Error fetching user information:', error);
    }
}
fetchUser()
    .then(success => console.log('Success!'))
    .catch(error => console.log(error));