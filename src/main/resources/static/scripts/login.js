async function main()
{
    const isLoggedIn = await loggedIn()
    if(isLoggedIn) location.href = "/loged.html"

    const form = document.getElementById('form')

    form.addEventListener('click', input =>
    {
        input.preventDefault()

        const email = document.getElementById('email').value
        const password = document.getElementById('password').value

        fetch("/users/lookup?email=" + email)
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
                            fetch("/users/login?email=" + email, 
                            {
                                method: 'POST',
                                credentials: 'include'
                            }).then(response => {
                            if (response.ok)
                            {
                                // The request was successful
                                console.log("Request successful");
                                
                                // Try to retrieve the SESSION_ID cookie
                                const sessionId = getCookie("SESSION_ID");
                                if (sessionId != null) {
                                    console.log("SESSION_ID cookie: " + sessionId);
                                } else {
                                    console.log("SESSION_ID cookie not found");
                                }
                            }
                            else
                                console.log("Request failed");
                            }).then(() => location.href = "/")
                        }
                    })
                }
            }
        })
    })
}

main()