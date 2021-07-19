$(document).ready(function () {
    getActiveUser();
    getAllUsers();
});

function getActiveUser() {
    fetch('http://localhost:8080/admin/getActiveUser')
        .then((response) => response.json())
        .then((user) => {
            document.getElementById('activeUserUsername').textContent = user.email;
            let rolesAsString = "";
            user.roles.forEach(role => rolesAsString += role.role + " ");
            document.getElementById('activeUserRoles').textContent = rolesAsString;
        });
}

function getAllUsers() {
    fetch('http://localhost:8080/admin/user')
        .then((response) => response.json())
        .then((userList) => {
            let table = "";
            userList.forEach(user => {

                let rolesAsString = "";
                user.roles.forEach(role => rolesAsString += role.role + " ");

                let tableRow = '<tr>' +
                    "<td>" + user.id + "</td>" +
                    "<td>" + user.firstname + "</td>" +
                    "<td>" + user.lastname + "</td>" +
                    "<td>" + user.age + "</td>" +
                    "<td>" + user.email + "</td>" +
                    "<td>" + rolesAsString + "</td>" +
                    '<td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#editModal"' +
                    ' onclick="editUser(' + user.id + ')" id="btnEdit">Edit</button></td>' +
                    '<td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal"' +
                    ' onclick="getUserForDeleteModal(' + user.id + ')" id="btnDelete">Delete</button></td>'
                table += tableRow;
            });
            document.getElementById("allUsers").innerHTML += table;
        });
}

function getUserForDeleteModal(id) {
    let closeButton = document.getElementById("closeButtonInModal");
    let deleteButton = document.getElementById("deleteButtonInModal");
    let modalDelete = document.getElementById("deleteModal");
    let closeButtonHeader = document.getElementById("closeButtonInModalHeader");

    fetch('http://localhost:8080/admin/user/' + id)
        .then(response => response.json())
        .then((user) => {
            $('#deleteUserFirstname').val(user.firstname)
            $('#deleteUserLastname').val(user.lastname)
            $('#deleteUserAge').val(user.age)
            $('#deleteUserEmail').val(user.email)
            $('#deleteUserId').val(user.id)
        });

    deleteButton.onclick = function () {
        fetch('http://localhost:8080/admin/user/' + id, {
            method: 'DELETE'
        });
    }

    closeButton.onclick = function () {
        modalDelete.style.display = "none";
    }

    closeButtonHeader.onclick = function () {
        modalDelete.style.display = "none";

    }

    window.onclick = function (event) {
        if (event.target == modalDelete) {
            modalDelete.style.display = "none";
        }
    }
}

function editUser(id) {
    let closeButton = document.getElementById("closeButtonInEditModal");
    let editButton = document.getElementById("editButtonInModal");
    let modalEdit = document.getElementById("editModal");
    let closeButtonHeader = document.getElementById("closeButtonInEditModalHeader");
    let editUserForm = document.forms['editUserForm']

    fetch('http://localhost:8080/admin/user/' + id)
        .then(response => response.json())
        .then((user) => {
            $('#editUserFirstname').val(user.firstname)
            $('#editUserLastname').val(user.lastname)
            $('#editUserAge').val(user.age)
            $('#editUserEmail').val(user.email)
            $('#editUserId').val(user.id)
        });

    editButton.onclick = function () {
        fetch('http://localhost:8080/admin/user/' + id)
            .then(response => response.json())
            .then((user) => {
                fetch('http://localhost:8080/admin/user/' + id, {
                    method: 'PUT',
                    body: JSON.stringify({
                        firstname: editUserForm.firstname.value,
                        lastname: editUserForm.lastname.value,
                        age: editUserForm.age.value,
                        email: editUserForm.email.value,
                        password: user.password,
                        role_id: editUserForm.role_id.value
                    })
                });
            });
    }

    closeButtonHeader.onclick = function () {
        modalEdit.style.display = "none";
    }

    closeButton.onclick = function () {
        modalEdit.style.display = "none";
    }
}

function addNewUser() {
    let addUserForm = document.forms['addUserForm']
    fetch('http://localhost:8080/admin/user', {
        method: 'POST',
        body: JSON.stringify({
            firstname: addUserForm.firstname.value,
            lastname: addUserForm.lastname.value,
            age: addUserForm.age.value,
            email: addUserForm.email.value,
            password: addUserForm.password.value,
            role_id: addUserForm.role_id.value
        })
    });
}
