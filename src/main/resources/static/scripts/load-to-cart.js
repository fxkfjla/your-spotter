clearButton = document.querySelector(".clear-button")

clearButton.addEventListener("click", () =>
{
    fetch("/cart/clear?userId=" + userId,
    {
        method: 'DELETE',
        headers:
        {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(() => location.href = "/shopping-cart.html")
})

fetch("/cart?userId=" + userId)
  .then(response => response.json())
  .then(cart => 
  {
    products = cart.products

    if (products.length > 0)
    {
      let tableRows = "";

      totalPrice = 0

      products.forEach(product =>
      {
        tableRows += `<tr>
          <td class='product-image'><img product-image src='${product.imageUrl}'></td>
          <td class='product-name'>${product.name}</td>
          <td class='product-amount'>${product.amount}</td>
          <td class='product-price'>${product.price}PLN</td>
          <td class='product-remove'><button class='product-delete-button' delete-button>delete</button></td>
          </tr>`;
        totalPrice += product.price
      });

      document.querySelector(".product-cart tbody").innerHTML = tableRows;

      document.querySelector(".summary-price").innerHTML = "TOTAL: " + totalPrice 

      const deleteButtons = document.querySelectorAll(".product-delete-button");
      deleteButtons.forEach((button, index) => 
      {
        button.addEventListener("click", () => 
        {
            const product = products[index];
            fetch("/cart/removeProduct?userId=" + userId, 
            {
                method: 'DELETE',
                headers:
                {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                    body: JSON.stringify(
                {
                    "name": product.name,
                    "amount": product.amount,
                    "price": product.price,
                    "imageUrl": product.imageUrl,
                    "category": {"id": product.category.id}
                })
            }).then(() => location.href = "/shopping-cart.html")
        })
    })
    }
})