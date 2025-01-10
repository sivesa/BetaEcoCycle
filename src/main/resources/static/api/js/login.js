async function loginUser() {
    const membershipNumber = document.getElementById('identifier').value;
    const password = document.getElementById('password').value;

    const data = new URLSearchParams();
    data.append('identifier', membershipNumber);
    data.append('password', password);

    try {
        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: data
        });

        if (response.ok) {
            window.location.href = '/landing.html'; // Redirect to landing page
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
