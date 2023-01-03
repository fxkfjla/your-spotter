const form = document.getElementById('form')

form.addEventListener('click', input =>
{
    input.preventDefault()

    fetch("/users/logout?sessionId=" + getCookie("SESSION_ID"),
    {
        method: "POST",
        headers: { "Content-Type": "application/json" }
    }).then(() => location.href = "/")
})
