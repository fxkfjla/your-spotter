var sortSelect = document.querySelector('[sort-select]')

var selected = localStorage.getItem("selected")

if(selected != null)
    sortSelect.value = selected

sortSelect.addEventListener("change", () =>
{
    if(sortSelect.options[sortSelect.selectedIndex ].value == "most-popular")
    {
        localStorage.setItem("order", "ASC")
        localStorage.setItem("by", "_id")
        localStorage.setItem("selected", "most-popular")
    }
    else if(sortSelect.options[sortSelect.selectedIndex ].value == "price-asc")
    {
        localStorage.setItem("order", "ASC")
        localStorage.setItem("by", "price")
        localStorage.setItem("selected", "price-asc")
    }
    else if(sortSelect.options[sortSelect.selectedIndex ].value == "price-desc")
    {
        localStorage.setItem("order", "DESC")
        localStorage.setItem("by", "price")
        localStorage.setItem("selected", "price-desc")
    }

    location.href = "/products.html"
})