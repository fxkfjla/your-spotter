const form = document.getElementById('form')
const enterKey = 13;

form.addEventListener('keypress', input =>
{
  if(input.keyCode === enterKey)
  {
    input.preventDefault()

    localStorage.clear();
    localStorage.setItem('counter', 0)

    const value = input.target.value.toLowerCase()

    fetch("/search=" + value)
    .then(res => res.json())
    .then(data =>
    {
      data.map(product =>
      {
        counter = localStorage.getItem('counter')

        // localStorage.setItem('image ' + counter, product.image)
        localStorage.setItem('name ' + counter, product.name)
        localStorage.setItem('amount ' + counter, product.amount)
        localStorage.setItem('price ' + counter, product.price)

        counter++
        localStorage.setItem('counter', counter)
      })

      location.href = "products.html"
    })
  }
})