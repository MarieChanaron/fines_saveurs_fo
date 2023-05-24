const handleSubmit = event => {
    const children = event.target.childNodes;
    const token = children[1].value;
    sessionStorage.setItem("token", token);
}

const formElement = document.getElementById("add-to-cart");
formElement.addEventListener("submit", handleSubmit);

const generateRandomToken = (length) => {
    const DIGITS38 = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','-','.'];
    const buf = [];
    for (let i = 0; i < length; i++) {
        const rand = Math.floor(Math.random() * DIGITS38.length);
        buf[i] = DIGITS38[rand];
    }
    return buf.join('');
}

let token;

if (sessionStorage.getItem("token") == null) {
    token = generateRandomToken(32);
} else {
    token = sessionStorage.getItem("token");
}

document.getElementById("token").value = token;

