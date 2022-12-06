const form = document.getElementById('form')

form.addEventListener('click', input =>
{
    input.preventDefault()

    const email = document.getElementById('email').value
    const password = document.getElementById('password').value

    fetch("users/lookup?email=" + email)
    .then(res => res.json())
    .then(data =>
    {
        if(data.email == email)
        {
            fetch("users/lookup/compare?email=" + email + "&password=" + password)
            .then(response => response.text())
            .then(isValid => 
            {
                if(isValid)
                {
                    location.href = "index.html"
                }
            })
        }
    })
})