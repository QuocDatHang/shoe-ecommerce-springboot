
const renderProducts = (listProducts) => {
    listProducts.forEach(item => {
        strProduct = `
        <div class="col renderProduct">
            <div class="card">
                <img src="${item.img}"
                    class="card-img-top h-50" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${item.title}</h5>
                    <div class="d-flex">
                        <div class="cols-2">
                            <i class="fa-solid fa-star fa-xs" style="color: #f4f41a;"></i>
                            <i class="fa-solid fa-star fa-xs" style="color: #f4f41a;"></i>
                            <i class="fa-solid fa-star fa-xs" style="color: #f4f41a;"></i>
                            <i class="fa-solid fa-star fa-xs" style="color: #f4f41a;"></i>
                        </div>
                        <div class="cols-2">(${item.reviews} viewers)</div>
                    </div>
                    <div class="d-flex align-items-center mt-2">
                        <span class="oldPrice">$${item.prevPrice}</span>
                        <span>$${item.newPrice}</span>
                        <i class="fa-solid fa-cart-arrow-down fa-lg"></i>
                    </div>
                </div>
            </div>
        </div>
        `
        $('#showProduct').append(strProduct);
    })
};


async function sortByCompany(company, event) {
    $('.btn').removeClass('active');
    $('.btn:focus').addClass('active');
    sortedProductsByCompany = new Array();
    const response = await fetch("https://jsonserver-vercel-api.vercel.app/products");
    const products = await response.json();
    products.forEach(item => {
        if (item.company == company) {
            sortedProductsByCompany.push(item);
        }
        if (company == 'all') {
            sortedProductsByCompany.push(item);
        }
    });
    sortProducts();
}

const sortProducts = () => {
    $('.renderProduct').remove();

    sortedProductsByCategory = new Array();
    const checkedCategory = Array.from($('[name="categorySort"]'));
    checkedCategory.forEach(radio => {
        if (radio.checked === true) {
            sortedProductsByCompany.forEach(item => {
                if (radio.value == 'allCategory') {
                    sortedProductsByCategory.push(item);
                }
                if (radio.value == 'sneakers' && item.category == 'sneakers') {
                    sortedProductsByCategory.push(item);
                }
                if (radio.value == 'flats' && item.category == 'flats') {
                    sortedProductsByCategory.push(item);
                }
                if (radio.value == 'sandals' && item.category == 'sandals') {
                    sortedProductsByCategory.push(item);
                }
                if (radio.value == 'heels' && item.category == 'heels') {
                    sortedProductsByCategory.push(item);
                }
            })
        }
    });

    sortedProductsByColor = new Array();
    const checkedColor = Array.from($('[name="colorSort"]'));
    checkedColor.forEach(radio => {
        if (radio.checked === true) {
            sortedProductsByCategory.forEach(item => {
                if (radio.value == 'allColor') {
                    sortedProductsByColor.push(item);
                }
                if (radio.value == 'black' && item.color == 'black') {
                    sortedProductsByColor.push(item);
                }
                if (radio.value == 'blue' && item.color == 'blue') {
                    sortedProductsByColor.push(item);
                }
                if (radio.value == 'red' && item.color == 'red') {
                    sortedProductsByColor.push(item);
                }
                if (radio.value == 'green' && item.color == 'green') {
                    sortedProductsByColor.push(item);
                }
                if (radio.value == 'white' && item.color == 'white') {
                    sortedProductsByColor.push(item);
                }
            })
        }
    });

    sortedProductsByPrice = new Array();
    const checkedPrice = Array.from($('[name="priceSort"]'));
    checkedPrice.forEach(radio => {
        if (radio.checked === true) {
            sortedProductsByColor.forEach(item => {
                if (radio.value == 'allPrice') {
                    sortedProductsByPrice.push(item);
                }
                if (radio.value == 'price0to50' && item.newPrice >= 0 && item.newPrice < 50) {
                    sortedProductsByPrice.push(item);
                }
                if (radio.value == 'price50to100' && item.newPrice >= 50 && item.newPrice < 100) {
                    sortedProductsByPrice.push(item);
                }
                if (radio.value == 'price100to150' && item.newPrice >= 100 && item.newPrice < 150) {
                    sortedProductsByPrice.push(item);
                }
                if (radio.value == 'priceOver150' && item.newPrice >= 150) {
                    sortedProductsByPrice.push(item);
                }
            })
        }
    })

    sortedProductsByTitle = new Array();
    var searchByTitle = $('#searchByTitle').val();
    searchByTitle = searchByTitle.toLowerCase();
    sortedProductsByPrice.forEach(item => {
        itemTitle = item.title.toLowerCase();
        if (itemTitle.includes(searchByTitle)) {
            sortedProductsByTitle.push(item);
        }
    })

    renderProducts(sortedProductsByTitle);
}

function increment() {
    var currentQty = +$('#productQty').text();
    var newQty = currentQty + 1;
    $('#productQty').text(newQty);
}

function decrement() {
    var currentQty = +$('#productQty').text();
    if (currentQty == 1) {
        return;
    }
    var newQty = currentQty - 1;
    $('#productQty').text(newQty);
}


$(document).ready(function async() {
    console.log('hi');
    sortByCompany('all');
    var sortedProductsByCompany;
    var sortedProductsByCategory;
    var sortedProductsByColor;
    var sortedProductsByPrice;
    var sortedProductsByTitle;

    $('.btn:first-child').addClass('active');
})
