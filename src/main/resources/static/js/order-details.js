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
    const id = event.target.parentNode.id;
    const endpoint = setEndpoint(id);
    redirect(endpoint);
}

for (let row of orderRows) {
    row.addEventListener("click", handleClick);
}
