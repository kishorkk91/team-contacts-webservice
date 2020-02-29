const userApi = 'http://localhost:8080/team/contacts';

function fetchUsers(){
    fetch(userApi)
        .then(res => res.json())
        .then(renderUsers)
        .catch(err => console.log(err));
}
const usersCollection = document.getElementById('users');

function renderUsers(users){
    usersCollection.innerHTML = '';
    users.forEach(function (user) {
        usersCollection.innerHTML +=
            `<div class="card border-secondary mb-3" style="max-width: 20rem; margin-right: 20px;">
                <div class="card-header" style="font-size: 20px;"> <i class="fas fa-user" style="color: var(--orange);"></i> Team contact Info </div>
                <div class="card-body">
                    <h6 class="card-subtitle mb-2 text-muted">` + user.email + `</h6>
                    <a class="btn btn-info" style="color: white; padding-left: 40px; padding-right: 40px; font-size: 16px;" onclick="getSingleUser(\`` + user.id + `\`)"> Details </a>
                     <a class="btn btn-info" style="color: white; padding-left: 40px; padding-right: 40px; font-size: 16px;" onclick="deleteUser(\`` + user.id + `\`)"> Delete </a>
                </div>
             </div>`;
    });
}

function getSingleUser(id){
    document.getElementById('users').style.display = "none";
    document.getElementById('user').style.display = "block";

    fetch(userApi+ '/' + id)
        .then(res => res.json())
        .then(renderSingleUser)
        .catch(err => console.log(err));
}

const userCollection = document.getElementById('user');

function renderSingleUser(user){
    userCollection.innerHTML = '';
    userCollection.innerHTML +=
            `<div class="card border-secondary mb-3" style="max-width: 20rem; margin-right: 20px;">
                <div class="card-header" style="font-size: 20px;"> <i class="fas fa-user" style="color: var(--orange);"></i> users Info </div>
                <div class="card-body">
                    <h6 class="card-subtitle mb-2 text-muted">` + user.firstName + `</h6>
                    <a href="index.html" class="btn btn-primary" style="color: white; padding-left: 40px; padding-right: 40px; font-size: 16px;"> Back to Users </a>
                </div>
             </div>`;
}


function createUser(){
    const data = {
        firstName: document.getElementById("firstname").value,
        lastName: document.getElementById("lastname").value,
        email: document.getElementById("email").value
    };

    fetch(userApi, {
        method: 'POST', // or 'PUT'
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then((response) => response.json())
        .then((data) => {
            console.log('Success:', data);
            document.getElementById("firstname").value="";
            document.getElementById("lastname").value="";
            document.getElementById("email").value="";
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function deleteUser(id) {
    fetch(userApi+'/'+id, {
        method: 'DELETE'
    });
}


