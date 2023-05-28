const ENDPOINT_ADMINS = 'http://localhost:8080/webapi/admins';

const fetchAdmins = async(endpointString) => {
    try {
        const adminsResponse = await fetch(endpointString);
        if (adminsResponse) {
            const adminsJsonResponse = await adminsResponse.json();
            return adminsJsonResponse;
        } else {
            throw new Error("Request failed");
        }
    } catch (error) {
        console.log(error);
        return [];
    }
}

const fillSelectElement = adminsArray => {
    adminsArray.forEach(admin => {
        const id = admin.id;
        const firstname = admin.firstname;
        const lastname = admin.lastname;
        const adminSelect = document.getElementById("admin-select");
        if (id && firstname && lastname) {
            let option = document.createElement('option');
            option.value = id;
            option.text = `${firstname} ${lastname}`;
            adminSelect.appendChild(option);
        }
    });
}

const admins = fetchAdmins(ENDPOINT_ADMINS)
    .then (admins => fillSelectElement(admins));