async function registerUser() {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const identifier = document.getElementById('identifier').value;
    const password = document.getElementById('password').value;
    const repeatPassword = document.getElementById('repeatPassword').value;

    if (password !== repeatPassword) {
        alert('Passwords do not match. Please try again.');
        return;
    }
    
    const payload = {
        firstName: firstName,
        lastName: lastName,
        identifier: identifier,
        password: password,
        repeatPassword: repeatPassword,
    };
    console.log(JSON.stringify(payload));

    try {
        console.log("Sending request to http://127.0.0.1:8080/api/household/register");
        
        const response = await fetch('http://127.0.0.1:8080/api/household/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload),
            mode: "cors",
        });
        console.log(response);
        if (response.ok) {
            const result = await response.json();
            console.log(result);
            alert('Registration successful: ' + JSON.stringify(result));
            //window.location.href = '/landing.html'; // Redirect to landing page
        } else {
            alert('Registration failed. Please try again.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred. Please try again later.');
    }
}

function registerWithGoogle() {
    // TODO: API call for Google registration
    window.location.href = '/api/auth/google'; // Replace with actual Google registration API
}
function registerWithFacebook() {
    // TODO: API call for Facebook registration
    window.location.href = '/api/auth/facebook'; // Replace with actual Facebook registration API
}
