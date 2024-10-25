/**
 * 
 */
const currentUrl = window.location.pathname;
const navLinks = document.querySelectorAll('.nav-link-header');
navLinks.forEach(link => {
	link.classList.remove('active');
	if (currentUrl.includes(link.getAttribute('href'))) {
		link.classList.add('active');
	}
});