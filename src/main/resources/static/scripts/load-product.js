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

      var postButton = document.querySelector(".post-button")
      user = null

      postButton.addEventListener('click', input => 
      {
        input.preventDefault();
      
        comment = document.getElementById('comment').value;
        
        // fetch("/users/lookup?id=" + userId).then(res => res.json()).then(data =>
        // {
        //   user = data
        //   console.log(data)
        // })

        console.log(user.id)

        fetch("comments/addComment", 
        {
          method: 'POST',
          headers: 
          {
            Accept: 'application/json',
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ "comment": comment, "product": result, "user": user })
        }).then(() => location.href = "/product.html");
      });

      fetch("/comments/productId?id=" + result.id)
      .then(res => res.json())
      .then(comments =>
      {
        section = document.querySelector(".comments")
      
        comments.forEach(comment =>
        {
          var commentDiv = document.createElement('div');
          commentDiv.classList.add('product-opinion');
          commentDiv.innerHTML = "<span>" + currentData() + "</span> by<div class='text'>" + comment.comment + "</div>";

          section.appendChild(commentDiv);
        }) 
      })
    }); 
  })
}

function currentData()
{
  var today = new Date();
  var dd = String(today.getDate()).padStart(2, '0');
  var mm = String(today.getMonth() + 1).padStart(2, '0');
  var yyyy = today.getFullYear();

  today = mm + '/' + dd + '/' + yyyy;
  return today
}