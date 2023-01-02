var product = document.querySelector("[product]")

var value = localStorage.getItem('product')
var button = document.querySelector('[buy-button]')

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

            button.addEventListener('click', ()=>
            {
                fetch("/cart/addProduct?userId=" + userId, 
                {
                    method: 'POST',
                    headers:
                    {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(
                    {
                        "name": result.name,
                        "amount": 1,
                        "price": result.price,
                        "imageUrl": result.imageUrl,
                        "category": {"id": result.category.id}
                    })
                })
            });
        }); 
    })
}