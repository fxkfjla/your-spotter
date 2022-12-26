var productTemplate = document.querySelector("[product-template]");
var productsContainer = document.querySelector("[products-container]")

var value = localStorage.getItem('search')

if(value != null)
{
    fetch("/products/search?name=" + value)
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

            image.src = product.imageUrl
            name.textContent = product.name
            // amount.textContent = product.amount
            price.textContent = product.price + " PLN"

            productsContainer.append(template)
        }); 
    })
}