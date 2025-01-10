async function registerUser() {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const email = document.getElementById('identifier').value;
    const password = document.getElementById('password').value;
    const repeatPassword = document.getElementById('repeatPassword').value;
    if (password !== repeatPassword) {
        alert('Passwords do not match. Please try again.');
        return;
    }

    const hashedPassword = await bcrypt.hash(password, 10); // Hash the password with a salt round of 10
    
    
    const data = new URLSearchParams();
    data.append('firstName', firstName);
    data.append('lastName', lastName);
    data.append('identifier', email);
    data.append('password', hashedPassword);
    try {
        const response = await fetch('/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: data
        });
        if (response.ok) {
            window.location.href = '/landing.html'; // Redirect to landing page
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
