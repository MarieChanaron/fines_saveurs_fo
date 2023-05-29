const stock = document.getElementById('stock-dd').innerText;

const checkValue = event => {
    const pEl = document.getElementById('stock-alert');
    const inputEl = event.target;
    if (inputEl.value >= stock) {
        inputEl.value = stock;
        pEl.classList.remove('hidden');
    } else {
        pEl.classList.add('hidden');
    }
}

const qtyInputElement = document.getElementById('qty-input');
qtyInputElement.onchange = checkValue;