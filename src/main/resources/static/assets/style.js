
const sortProducts = async () => {
    $('.renderProduct').remove();
    await $('.btn').on('click', () => {
        $('.btn').removeClass('active');
        $('.btn:focus').addClass('active');
    })

    let companyName = $('.btn.active').val();
    let categoryName = $('input[name="categorySort"]:checked').val();
    let colorName = $('input[name="colorSort"]:checked').val();
    let priceRange = $('input[name="priceSort"]:checked').val();
    let search = $('#searchByTitle').val();
    let minPrice;
    let maxPrice;
    if (priceRange === 'allPrice') {
        minPrice = 0;
        maxPrice = 0;
    } else {
        priceRange = priceRange.split(",");
        minPrice = parseInt(priceRange[0]);
        maxPrice = parseInt(priceRange[1]);
    }

    let filter = {
        search,
        companyName,
        categoryName,
        colorName,
        minPrice,
        maxPrice
    }
    console.log(filter)
    const response = await fetch('api/products', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(filter)
    });
    const result = await response.json();
    renderProducts(result);
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

async function getAllCompanies() {
    const response = await fetch('api/companies');
    const result = await response.json();
    renderCompanies(result);
}

function renderCompanies(companies) {
    let str =  `
            <button type="button" class="btn btn-outline-secondary active" value="allCompany"
            onclick="sortProducts()">All Products</button>
            `
    $('.btnCompany').append(str);
    companies.forEach(company => {
        let str =
            `
            <button type="button" class="btn btn-outline-secondary" value="${company.nameCompany}" onclick="sortProducts()">
                ${company.nameCompany}
            </button>
            `
        $('.btnCompany').append(str);
    })
}

async function getAllCategories() {
    const response = await fetch("api/categories");
    const result = await response.json()
    renderCategories(result);
}

function renderCategories(categories) {
    let str =
        `
        <h5>Category</h5>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="categorySort" id="allCategory"
                   value="allCategory" onchange="sortProducts()" checked>
            <label class="form-check-label" for="allCategory">All</label>
         </div>
        `
    $('.showCategory').append(str);
    categories.forEach(category => {
        let str =
            `
            <div class="form-check">
                <input class="form-check-input" type="radio" name="categorySort" id="category-${category.id}" value="${category.nameCategory}"
                       onchange="sortProducts()">
                <label class="form-check-label" for="category-${category.id}">
                    ${category.nameCategory}
                </label>
            </div>
            `
        $('.showCategory').append(str);
    })
}

async function getAllColors() {
    const response = await fetch("api/colors");
    const result = await response.json()
    renderColors(result);
}

function renderColors(colors) {
    let str =
        `
        <h5>Color</h5>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="colorSort" id="allColor" 
                value="allColor" onchange="sortProducts()" checked>
            <label class="form-check-label" for="allColor">All</label>
        </div>
        `
    $('.showColor').append(str);
    colors.forEach(color => {
        let str =
            `
            <div class="form-check">
                <input class="form-check-input" type="radio" name="colorSort" id="color-${color.id}" value="${color.nameColor}"
                       onchange="sortProducts()">
                <label class="form-check-label" for="color-${color.id}">
                    ${color.nameColor}
                </label>
            </div>
            `
        $('.showColor').append(str);
    })
}

async function getAllPrices() {
    const response = await fetch("api/prices");
    const result = await response.json()
    renderPrices(result);
}

function renderPrices(prices) {
    let str =
        `
        <h5>Price</h5>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="priceSort" id="allPrice" value="allPrice"
                   onchange="sortProducts()" checked>
            <label class="form-check-label" for="allPrice">All</label>
        </div>
        `
    $('.showPrice').append(str);
    prices.forEach(price => {
        let str =
            `
            <div class="form-check">
                <input class="form-check-input" type="radio" name="priceSort" id="price-${price.id}" 
                value="${price.value}" onchange="sortProducts()">
                <label class="form-check-label" for="price-${price.id}">
                    ${price.namePrice}
                </label>
            </div>
            `
        $('.showPrice').append(str);
    })
}

// async function getAllProducts() {
//     const response = await fetch("api/products");
//     const result = await response.json()
//     renderProducts(result);
// }

const renderProducts = (products) => {
    products.forEach(item => {
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

$(document).ready(async function () {
    // sortByCompany('all');
    let sortedProductsByCompany;
    let sortedProductsByCategory;
    let sortedProductsByColor;
    let sortedProductsByPrice;
    let sortedProductsByTitle;

    await getAllCompanies();
    await getAllCategories();
    await getAllColors();
    await getAllPrices();
    $('.btn:first-child').addClass('active');
    await sortProducts()
})
