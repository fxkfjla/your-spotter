const productTemplate = document.querySelector("[product-template]");
const productsContainer = document.querySelector("[products-container]")

const searchInput = document.querySelector("[product-search]")

let products = []

const enterKey = 13;

searchInput.addEventListener("keyup", input =>
{
  if(input.keyCode === enterKey)
  {
    const value = input.target.value.toLowerCase()

    fetch("/search=" + value)
    .then(res => res.json())
    .then(data =>
    {
      products = data.map(product =>
      {
        const template = productTemplate.content.cloneNode(true).children[0];

        const name = template.querySelector("[product-name]")
        const amount = template.querySelector("[product-amount]")
        const price = template.querySelector("[product-price]")

        name.textContent = product.name
        amount.textContent = product.amount
        price.textContent = product.price

        productsContainer.append(template)

        return { name: product.name, amount: product.amount, price: product.price, element: template } 
      })
    })
    console.log(products)
  }
})
