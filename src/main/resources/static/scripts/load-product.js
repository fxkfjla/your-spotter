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

      var postButton = document.querySelector(".post-button")
      
      postButton.addEventListener('click', input => 
      {
        input.preventDefault();
      
        comment = document.getElementById('comment').value;
        
        fetch("comments/addComment", 
        {
          method: 'POST',
          headers: 
          {
            Accept: 'application/json',
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ "comment": comment, "product": result })
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
          commentDiv.innerHTML = "<div class='text'>" + comment.comment + "</div>";

          section.appendChild(commentDiv);
        }) 
      })
    }); 
  })
}