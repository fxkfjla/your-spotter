window.addEventListener("load", ()=>
{
    const categoriesContainer = document.querySelector("[categories-container]")
    const categories = categoriesContainer.querySelectorAll("[category]")

    categories.forEach(category => 
    {
        category.addEventListener("click", ()=>
        {
            const categoryName = category.querySelector("[category-name]").textContent

            localStorage.clear() 
            localStorage.setItem("category", categoryName)

            location.href = "/products.html"
        })
    })
})