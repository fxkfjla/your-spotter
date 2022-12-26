var productTemplate = document.querySelector("[product-template]");
var productsContainer = document.querySelector("[products-container]")

var value = localStorage.getItem('category')

if(value != null)
{
    fetch("/products/categoryName?name=" + value + "&order=" + order + "&by=" + by)
    .then(res => res.json())
    .then(data =>
    {
        data.forEach(product =>
        {
            const template = productTemplate.content.cloneNode(true).children[0];

            const image = template.querySelector("[product-image]")
            const name = template.querySelector("[product-name]")
            const price = template.querySelector("[product-price]")

            image.src = product.imageUrl
            name.textContent = product.name
            price.textContent = product.price + " PLN"

            productsContainer.append(template)
        }); 
    })
}