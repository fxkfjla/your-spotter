function waitForElm(selector) {
    return new Promise(resolve => {
        if (document.querySelector(selector)) {
            return resolve(document.querySelector(selector));
        }

        const observer = new MutationObserver(mutations => {
            if (document.querySelector(selector)) {
                resolve(document.querySelector(selector));
                observer.disconnect();
            }
        });

        observer.observe(document.body, {
            childList: true,
            subtree: true
        });
    });
}

waitForElm('.product').then(() =>
{
    const productsContainer = document.querySelector("[products-container]")
    const products = productsContainer.querySelectorAll("[product]")

    products.forEach(product => 
    {
        product.addEventListener("click", ()=>
        {
            const productName = product.querySelector("[product-name]").textContent

            localStorage.setItem("product", productName)

            location.href = "/product.html"
        })
    })
})