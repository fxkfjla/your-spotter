const headerShrink = document.querySelector("header");

window.addEventListener("scroll", () =>
{
    if(window.pageYOffset > 100)
	{
		headerShrink.classList.add("fixed-header");
    }
	else 
	{
		headerShrink.classList.remove("fixed-header");
    }
});
