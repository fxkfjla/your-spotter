const form = document.getElementById('form')
const enterKey = 13;

form.addEventListener('keypress', input =>
{
  if(input.keyCode === enterKey)
  {
    input.preventDefault()

    const value = input.target.value.toLowerCase()

    localStorage.clear();
    localStorage.setItem('search', value)

    location.href = "/products.html"
  }
})