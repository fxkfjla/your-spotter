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
            if(data.enabled)
            {
                fetch("users/lookup/compare?email=" + email + "&password=" + password)
                .then(response => response.text())
                .then(isValid => 
                {
                    if(JSON.parse(isValid)) 
                    {
                        userId = data.id
                        location.href = "/index.html"
                    }
                })
            }
        }
    })
})