const productTemplate = document.querySelector("[product-template]");
const productsContainer = document.querySelector("[products-container]")

fetch("/products/categoryName?name=Gainer")
.then(res => res.json())
.then(data =>
{
    data.forEach(product =>
    {
        const template = productTemplate.content.cloneNode(true).children[0];

        const image = template.querySelector("[product-image]")
        const name = template.querySelector("[product-name]")
        const amount = template.querySelector("[product-amount]")
        const price = template.querySelector("[product-price]")

        // image.textContent = product.image
        name.textContent = product.name
        // amount.textContent = product.amount
        price.textContent = product.price + " PLN"

        productsContainer.append(template)
    }); 
})