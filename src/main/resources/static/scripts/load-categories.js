var categoryTemplate = document.querySelector("[category-template]");
var categoriesContainer = document.querySelector("[categories-container]")

fetch("products/categories/all")
.then(res => res.json())
.then(data =>
{
    data.forEach(category =>
    {
        const template = categoryTemplate.content.cloneNode(true).children[0];

        const image = template.querySelector("[category-image]")
        const name = template.querySelector("[category-name]")

        template.href = "categories/" + category.name + ".html"
        image.src = category.imageUrl
        name.textContent = category.name

        categoriesContainer.append(template)
    }); 
})