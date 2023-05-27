if (
    document.getElementsByClassName("product-card").length > 0 ||
    document.querySelector("input[type='text']").value.length === 0
) {
    document.getElementById("no-result").style.display = "none";
}

const handleClick = event => {
    const inputElement = event.target;
    inputElement.setSelectionRange(0, inputElement.value.length);
}

document
    .querySelector('input[name="keywords"]')
    .addEventListener("click", handleClick);