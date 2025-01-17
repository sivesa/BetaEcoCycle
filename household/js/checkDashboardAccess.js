async function checkAccess() {
    const response = await fetch('http://127.0.0.1:8080/api/household/dashboard', {
        credentials: 'include', // Include cookies for session validation
    });

    if (response.ok) {
        document.getElementById('content').innerText = 'Dashboard!';
    } else {
        window.location.href = '/login.html';
    }
}

async function logout() {
    // Optionally implement logout functionality
    window.localStorage.removeItem('authenticated');
    window.location.href = '/login.html';
}

checkAccess();
