async function fetchUsers() {
    try {
        const response = await fetch('/api/users'); 
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const users = await response.json(); // Parse JSON data

        // Display users in the table
        const userTableBody = document.getElementById('userTableBody');
        userTableBody.innerHTML = ''; // Clear previous data

        users.forEach(user => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${user.id}</td>
                <td>${user.first_name}</td>
                <td>${user.last_name}</td>
                <td>${user.email}</td>
            `;
            userTableBody.appendChild(row);
        });
    } catch (error) {
        console.error('Error fetching users:', error);
        alert('Failed to retrieve user data. Please try again later.');
    }
}

// Fetch users when the page loads
window.onload = fetchUsers;
