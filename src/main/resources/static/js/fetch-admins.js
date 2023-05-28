const ENDPOINT = 'http://localhost:8080/webapi/admins';

const fetchData = async(endpointString) => {
    try {
        const response = await fetch(endpointString);
        if (response) {
            const jsonResponse = await response.json();
            return jsonResponse;
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

const admins = fetchData(ENDPOINT)
    .then (admins => fillSelectElement(admins));