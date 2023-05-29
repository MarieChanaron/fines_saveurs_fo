const orderRows = document.getElementsByClassName("order-row");

const redirect = endpoint => {
    window.location.replace(endpoint);
}

const setEndpoint = id => {
    const currentUrl = new URL(window.location.href);
    const endpoint = new URL('customers', currentUrl);
    endpoint.searchParams.append('orderId', id);
    return endpoint;
}

const handleClick = event => {
    const row = event.target.parentNode;
    const id = row.id;
    const endpoint = setEndpoint(id);
    redirect(endpoint);
}

const getUrlParameter = () => {
    const url = new URL(window.location.href);
    const param = url.searchParams.get("orderId");
    return param;
}

for (let row of orderRows) {
    if (row.id === getUrlParameter()) {
        row.classList.add("highlight-row");
    }
    row.addEventListener("click", handleClick);
}
