var product = document.querySelector("[product]")

var value = localStorage.getItem('product')

if(value != null)
{
    fetch("/products/search?name=" + value)
    .then(res => res.json())
    .then(data =>
    {
        data.forEach(result =>
        {
            var image = product.querySelector("[product-image]")
            var name = product.querySelector("[product-name]")
            var price = product.querySelector("[product-price]")

            image.src = result.imageUrl
            name.textContent = result.name
            price.textContent = result.price + " PLN"
        }); 
    })
}