const form = document.getElementById('ticket-form');

const URL = 'http://localhost:8080/webapi/tickets/create';

const postValues = async(jsonObject) => {
    try {
        const response = await fetch(URL, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify(jsonObject)
        });
        if (response.ok) {
            return response;
        }
    } catch (error) {
        if (error) console.log(error);
    }
}

const handleSubmit = event => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const data = {
        "userEmail": formData.get("userEmail"),
        "adminId": formData.get("adminId"),
        "textInput" : formData.get("textInput")
    };
    const p = document.getElementById('confirmation');
    postValues(data)
        .then(() => p.innerText = "Votre message a été envoyé avec succès.")
        .catch(() => p.innerText = "Une erreur s'est produite.");
    event.target.reset();
}

form.onsubmit = handleSubmit;