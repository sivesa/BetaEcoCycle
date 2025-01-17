async function loginHousehold() {
    const identifier = document.getElementById('identifier').value;
    const password = document.getElementById('password').value;

    const data = new URLSearchParams();
    data.append('identifier', membershipNumber);
    data.append('password', password);
    
    const payload = {
        identifier: identifier,
        password: password
    }
    console.log(JSON.stringify(payload));

    console.log("Sending request to http://127.0.0.1:8080/api/household/auth");
    try {
        const response = await fetch('/api/household/auth', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload),
            credentials: 'include', // Send cookies with the request
        });

        if (response.ok) {
            window.localStorage.setItem('authenticated', 'true');
            window.location.href = '/index.html'; // Redirect to landing page
        } else {
            alert('Login failed. Please check your credentials.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred. Please try again later.');
    }
}

function loginWithGoogle() {
    // TODO: API call for Google login
    window.location.href = '/api/auth/google'; // Replace with actual Google login API
}

function loginWithFacebook() {
    // TODO: API call for Facebook login
    window.location.href = '/api/auth/facebook'; // Replace with actual Facebook login API
}
