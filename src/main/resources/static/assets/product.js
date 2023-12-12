toastr.options = {
    "closeButton": true,
    "newestOnTop": false,
    "progressBar": true,
    "positionClass": "toast-top-right",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "1500",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}

let pageCurrent = 0;

const sortProducts = async () => {
    $('.renderProduct').remove();

    let companyName = $('input[name="companySort"]:checked').val();
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
        maxPrice,
        page: pageCurrent,
        size: 30,
        sortField: "title",
        direction: "DESC"
    }

    const response = await fetch('api/products', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(filter)
    });
    const result = await response.json();
    const products = result.content;
    renderProducts(products);

    $('.btnAddToCart').on('click',  async function () {
        const productId = $(this).data("id");
        const cartDetailReqDTO = {
            productId
        }
        const response = await fetch("api/carts/add-to-cart", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(cartDetailReqDTO)
        });
        if (response.ok) {
            toastr.success("Add to cart successfully!");
            const result = await response.json();
            const cartDetailQuantity = result.cartDetailResDTOList.length;
            renderCartDetailQuantity(cartDetailQuantity);
        }
    })
}

function renderCartDetailQuantity(cartDetailQuantity) {
    $('.cartDetailQuantity').remove();
    let str = `<span class="position-absolute top-0 start-100 bg-danger translate-middle badge 
                        border border-light rounded-circle cartDetailQuantity">${cartDetailQuantity}</span>`
    $('.cartBtn').append(str);
}

async function getAllCompanies() {
    const response = await fetch('api/companies');
    const result = await response.json();
    renderCompanies(result);
}

function renderCompanies(companies) {
    let str =  `
                <input type="radio" class="btn-check brand" onclick="sortProducts()" name="companySort" id="allCompany" 
                autocomplete="off" value="allCompany" checked>
                <label class="btn btn-outline-secondary" for="allCompany">All Products</label>
            `
    $('#btnCompany').append(str);
    companies.forEach(company => {
        let str =
            `
            <input type="radio" class="btn-check brand" onclick="sortProducts()" name="companySort" id="company-${company.id}" 
            autocomplete="off" value="${company.nameCompany}">
            <label class="btn btn-outline-secondary" for="company-${company.id}"> ${company.nameCompany}</label>
            `
        $('#btnCompany').append(str);
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
                <input class="form-check-input" type="radio" name="categorySort" id="category-${category.id}" 
                value="${category.nameCategory}" onchange="sortProducts()">
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
                    <div class="d-flex align-items-center justify-content-between mt-2">
                        <div class="d-flex">
                            <div class="oldPrice">$${item.prevPrice}</div>
                            <div>$${item.newPrice}</div>
                        </div>
                        <div class="btnAddToCart" data-id="${item.id}">
                            <i class="fa-solid fa-cart-arrow-down fa-lg"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        `
        $('#showProduct').append(strProduct);
    })
};

async function getAllCartDetail() {
    const response = await fetch("api/carts")
    const result = await response.json();
    const cartDetailQuantity = result.cartDetailResDTOList.length;
    renderCartDetailQuantity(cartDetailQuantity);
}


$(document).ready(async function () {
    await getAllCompanies();
    await getAllCategories();
    await getAllColors();
    await getAllPrices();
    await sortProducts()
    await getAllCartDetail();
})
