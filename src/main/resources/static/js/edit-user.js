document.addEventListener('DOMContentLoaded', () => {
    const editIcons = document.querySelectorAll('.edit-icon');
    const deleteIcons = document.querySelectorAll('.delete-icon');
    const editUserModal = document.getElementById('editUser');
    const closeModalButton = document.getElementById('closeModal');
    const userForm = document.getElementById('user-list');

    // Xử lý khi ấn Edit
    editIcons.forEach((icon) => {
        icon.addEventListener('click', () => {
            const userId = icon.getAttribute('data-id');
            const username = icon.getAttribute('data-username');
            const displayName = icon.getAttribute('data-displayname');
            const role = icon.getAttribute('data-role');

            document.getElementById('userId').value = userId;
            document.getElementById('Username').value = username;
            document.getElementById('Display-name').value = displayName;
            document.getElementById('Role').value = role;

            editUserModal.style.display = 'flex';
        });
    });

    // Xử lý khi ấn Cancel
    if (closeModalButton) {
        closeModalButton.addEventListener('click', () => {
            editUserModal.style.display = 'none';
        });
    }

    // Xử lý khi ấn Delete
    deleteIcons.forEach((icon) => {
        icon.addEventListener('click', () => {
            const userId = icon.getAttribute('data-id');
            if (userId && confirm('Are you sure you want to delete this user?')) {
                fetch(`/admin/user/delete/${userId}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                })
                    .then(response => {
                        if (response.ok) {
                            alert('User deleted successfully!');
                            location.reload();
                        } else {
                            alert('Failed to delete user.');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('Failed to delete user.');
                    });
            }
        });
    });

    // Xử lý Submit form chỉnh sửa
    if (userForm) {
        userForm.addEventListener('submit', (e) => {
            e.preventDefault();

            const userId = document.getElementById('userId').value;
            const username = document.getElementById('Username').value;
            const displayName = document.getElementById('Display-name').value;
            const role = document.getElementById('Role').value;

            fetch('/admin/user/update', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    id: userId,
                    username: username,
                    displayName: displayName,
                    role: role
                })
            })
                .then(response => {
                    if (response.ok) {
                        return response.text();
                    } else {
                        return response.text().then(errMessage => {
                            throw new Error(errMessage);
                        });
                    }
                })
                .then(message => {
                    alert(message);
                    location.reload();
                })
                .catch(error => {
                    console.error('Error:', error.message);
                    alert(`An error occurred: ${error.message}`);
                });

            editUserModal.style.display = 'none';
        });
    }

    // Xử lý khi click bên ngoài modal để tắt
    window.addEventListener('click', (event) => {
        if (event.target === editUserModal) {
            editUserModal.style.display = 'none';
        }
    });
});
