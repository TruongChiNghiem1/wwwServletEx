const $ = document.querySelector.bind(document);
function load(selector, path) {
	const cached = localStorage.getItem(path);
	if (cached) {
		$(selector).innerHTML = cached;
	}

	fetch(path)
		.then((res) => res.text())
		.then((html) => {
			if (html !== cached) {
				$(selector).innerHTML = html;
				localStorage.setItem(path, html);
			}
		})
		.finally(() => {
			window.dispatchEvent(new Event("template-loaded"));
		});
}

const handleChangeSelection = () => {
	let selectedValue = document.getElementById('codeSupplier');
	let code = selectedValue.value;
	const contextPath = selectedValue.getAttribute('data-context-path');
	const path = `${contextPath}/listPhone?maNCC=${code}`;
	window.location.href = path;
};

const handleSearch = () => {
	let searchValue = document.getElementById('search_name').value;
	if (searchValue !== '') {
		const contextPath = document.getElementById('search_name').getAttribute('data-context-path');
		const path = `${contextPath}/listPhone?search=${searchValue}`;
		window.location.href = path;
	}
}
function populatePhoneDetailModal(button) {
	const phoneId = button.getAttribute('data-phone-id');
	const phoneName = button.getAttribute('data-phone-name');
	const phoneYear = button.getAttribute('data-phone-year');
	const phoneSpecs = button.getAttribute('data-phone-specs');
	const phoneImage = button.getAttribute('data-phone-image');
	const supplierId = button.getAttribute('data-supplier-id');
	const dataContextPath = button.getAttribute('data-context-path');


	document.getElementById('deletePhone').setAttribute('href', `${dataContextPath}/managerForm?deletePhone=${phoneId}`);
	document.getElementById('supplieid').value = supplierId;
	document.getElementById('phoneName').value = phoneName;
	document.getElementById('idPhone').value = phoneId;
	document.getElementById('phoneYear').value = phoneYear;
	document.getElementById('phoneSpecs').value = phoneSpecs;
	const imagePreview = document.getElementById('imagePreview');
	imagePreview.src = phoneImage;
}

document.getElementById('phoneDetailModal').addEventListener('hidden.bs.modal', function() {
	document.getElementById('phoneDetailForm').reset();
});
function populateSupplierDetailModal(button) {
	const supplierName = button.getAttribute('data-supplier-name');
	const supplierAddress = button.getAttribute('data-supplier-address');
	const supplierPhone = button.getAttribute('data-supplier-phone');
	const supplierId = button.getAttribute('data-supplier-id');
	const dataContextPath = button.getAttribute('data-context-path');

	document.getElementById('deleteSupplier').setAttribute('href', `${dataContextPath}/managerForm?deleteSupplier=${supplierId}`);
	document.getElementById('idSupplier').value = supplierId;
	document.getElementById('supplierName').value = supplierName;
	document.getElementById('supplierAdress').value = supplierAddress;
	document.getElementById('supplierPhone').value = supplierPhone;
}

function previewImage(event) {
	const imagePreview = document.getElementById('imagePreview');
	const file = event.target.files[0];

	if (file) {
		const reader = new FileReader();
		reader.onload = function(e) {
			imagePreview.src = e.target.result;
		}
		reader.readAsDataURL(file);
	}
}
const handleUpdatePhone = () => {
	document.getElementById('phoneDetailForm').submit();
}
function handleDeletePhone(button) {
	const url = button.getAttribute('href');
	window.location.href = url;
}
const handleUpdateSupplier = () => {
	document.getElementById('supplierDetailForm').submit();
}
function handleDeleteSupplier(button) {
	const url = button.getAttribute('href');
	window.location.href = url;
}
function messageOk() {
	document.getElementById('error_container').innerHTML = "";
}
document.getElementById('searchInput').addEventListener('keypress', (event) => {
	if (event.key === 'Enter') {
		const searchValue = event.target.value;
		const selectedOption = document.getElementById('searchSelect').value;
		const contextPath = document.querySelector('[data-context-path]').dataset.contextPath;
		const url = `${contextPath}/managerForm?find=${selectedOption}:${searchValue}`;
		window.location.href = url;
	}
});