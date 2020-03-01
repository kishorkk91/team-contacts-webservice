const contactsApi = 'http://localhost:8080/team/contacts';

function fetchContacts(){
    fetch(contactsApi)
        .then(res => res.json())
        .then(renderContacts)
        .catch(err => console.log(err));
}
const contactsCollection = document.getElementById('contacts');

function renderContacts(contacts){
    contactsCollection.innerHTML = '';
    contacts.forEach(function (contact) {
        contactsCollection.innerHTML +=
            `<div class="card border-secondary mb-3" style="max-width: 20rem; margin-right: 20px;">
                <div class="card-header" style="font-size: 20px;"> <i class="fas fa-user" style="color: var(--orange);"></i> Team contact Info </div>
                <div class="card-body">
                    <h6 class="card-subtitle mb-2 text-muted">` + contact.email + `</h6>
                    <a class="btn btn-info" style="color: white; padding-left: 40px; padding-right: 40px; font-size: 16px;" onclick="getSingleContact(\`` + contact.id + `\`)"> Details </a>
                     <a class="btn btn-info" style="color: white; padding-left: 40px; padding-right: 40px; font-size: 16px;" onclick="deleteContact(\`` + contact.id + `\`)"> Delete </a>
                </div>
             </div>`;
    });
}

function getSingleContact(id){
    document.getElementById('contacts').style.display = "none";
    document.getElementById('contact').style.display = "block";

    fetch(contactsApi+ '/' + id)
        .then(res => res.json())
        .then(renderSingleContact)
        .catch(err => console.log(err));
}

const contactCollection = document.getElementById('contact');

function renderSingleContact(contact){
    contactCollection.innerHTML = '';
    contactCollection.innerHTML +=
            `<div class="card border-secondary mb-3" style="max-width: 20rem; margin-right: 20px;">
                <div class="card-header" style="font-size: 20px;"> <i class="fas fa-user" style="color: var(--orange);"></i> Contacts Info </div>
                <div class="card-body">
                    <h6 class="card-subtitle mb-2 text-muted">` + contact.firstName + `</h6>
                    <h6 class="card-subtitle mb-2 text-muted">` + contact.lastName + `</h6>
                    
                    <a href="index.html" class="btn btn-primary" style="color: white; padding-left: 40px; padding-right: 40px; font-size: 16px;"> Back to Contacts </a>
                </div>
             </div>`;
}


function createContact(){
    const data = {
        firstName: document.getElementById("firstname").value,
        lastName: document.getElementById("lastname").value,
        email: document.getElementById("email").value
    };

    fetch(contactsApi, {
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

function deleteContact(id) {
    fetch(contactsApi+'/'+id, {
        method: 'DELETE'
    });
}


