var product = document.querySelector("[product]")

var value = localStorage.getItem('product')
var button = document.querySelector('[buy-button]')

var tmp = null

async function main()
{
  const user = await getUser()

  if(value != null)
  {
    fetch("/products/search?name=" + value)
    .then(res => res.json())
    .then(data =>
    {
      data.forEach(result =>
      {
        tmp = result
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

        postButton.addEventListener('click', input => 
        {
          input.preventDefault();

          if(userId == -1) {
            alert("You must have an account to post comments!");
          }
          else
          {
            comment = document.getElementById('comment').value;
            fetch("comments/addComment",
            {
              method: 'POST',
              headers:
              {
                Accept: 'application/json',
                'Content-Type': 'application/json',
              },
              body: JSON.stringify({ "comment": comment, "product": tmp, "userEmail": user.email, "data": currentData() })
            }).then(() => location.href = "/product.html");
          }
        });
      });

      fetch("/comments/productId?id=" + tmp.id)
      .then(res => res.json())
      .then(comments =>
      {
        section = document.querySelector(".comments")

        comments.forEach(comment =>
        {
          var commentDiv = document.createElement('div');
          commentDiv.classList.add('product-opinion');
          commentDiv.innerHTML = "<span>" + comment.data + "</span> by <strong>" + comment.userEmail + "</strong><div class='text'>" + comment.comment + "</div>";

          section.appendChild(commentDiv);
        })
      })
    });
  }
}
waitForUserId().then(main);

function currentData()
{
  var today = new Date();
  var dd = String(today.getDate()).padStart(2, '0');
  var mm = String(today.getMonth() + 1).padStart(2, '0');
  var yyyy = today.getFullYear();

  today = dd + '/' + mm + '/' + yyyy;
  return today
}

async function getUser()
{
  if(userId != -1)
    return await fetch("users/getById?id=" + userId)
          .then(res => res.json())
          .then(data => {return data})
}