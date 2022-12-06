const form = document.getElementById('form')

form.addEventListener('click', input =>
{
    input.preventDefault()

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const rePassword = document.getElementById('re-password').value;

    if(password == rePassword)
    {
        fetch('registration', 
        {
            method: 'POST',
            headers:
            {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ "password": password, "email": email})
        })

        location.href = "login.html"
    }
})