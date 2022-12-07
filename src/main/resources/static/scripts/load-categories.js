const categoryTemplate = document.querySelector("[category-template]");
const categoriesContainer = document.querySelector("[categories-container]")

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
        name.textContent = category.name
        // image.textContent = category.image

        categoriesContainer.append(template)
    }); 
})