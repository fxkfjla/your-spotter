const productTemplate = document.querySelector("[product-template]");
const productsContainer = document.querySelector("[products-container]")

counter = localStorage.getItem("counter")

for(let i = 0; i < counter; i++)
{
    const template = productTemplate.content.cloneNode(true).children[0];

    const image = template.querySelector("[product-image]")
    const name = template.querySelector("[product-name]")
    const amount = template.querySelector("[product-amount]")
    const price = template.querySelector("[product-price]")

    // image.textContent = localStorage.getItem("image" + i)
    name.textContent = localStorage.getItem("name " + i)
    // amount.textContent = localStorage.getItem("amount " + i)
    price.textContent = localStorage.getItem("price " + i) + " PLN"

    productsContainer.append(template)
}