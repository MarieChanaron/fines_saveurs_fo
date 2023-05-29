const ENDPOINT_CATEGORIES = 'http://localhost:8080/webapi/categories';

const fetchCategories = async(endpointString) => {
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

const fillDropdownMenu = categoriesArray => {
    categoriesArray.forEach(category => {
        const id = category.id;
        const name = category.name;
        const dropdown = document.getElementById("dropdown-categories");
        if (id && name) {
            const li = document.createElement('li');
            const a = document.createElement('a');
            a.classList.add("dropdown-item");
            a.innerText = name;
            a.setAttribute("href", `/products?cat=${id}`);
            li.appendChild(a);
            dropdown.prepend(li);
        }
    });
}

const categories = fetchCategories(ENDPOINT_CATEGORIES)
    .then (categories => fillDropdownMenu(categories));