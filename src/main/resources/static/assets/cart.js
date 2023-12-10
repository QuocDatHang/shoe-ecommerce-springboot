function renderCartDetail(listCartDetail) {
    listCartDetail.forEach(item => {
        let totalAmountProduct = +(item.amount) * +(item.quantity);
        let str = `
                    <tr class="renderCartDetail">
                        <td><img src="${item.img}" alt="error">
                        </td>
                        <td style="width: 13rem; text-align: left;">${item.title}</td>
                        <td>$${item.amount}</td>
                        <td>
                            <div class="quantityBtn">
                                <span onclick="decrement(${item.productId})">-</span>
                                <span id="productQty">${item.quantity}</span>
                                <span onclick="increment(${item.productId})">+</span>
                            </div>
                        </td>
                        <td>$${totalAmountProduct}</td>
                        <td><i class="fa-solid fa-circle-xmark fa-2xl" onclick="deleteCartDetail(${item.productId})"></i></td>
                    </tr>    
                     `
        $('#showCartDetail').append(str)
    })
}

function renderCartDetailQuantity(cartDetailQuantity) {
    $('.cartDetailQuantity').remove();
    let str = `<span class="position-absolute top-0 start-100 bg-danger translate-middle badge 
                        border border-light rounded-circle cartDetailQuantity">${cartDetailQuantity}</span>`
    $('.cartBtn').append(str);
}


async function getAllCartDetail() {
    const response = await fetch("api/carts")
    const result = await response.json();
    const listCartDetail = result.cartDetailResDTOList;
    renderCartDetail(listCartDetail)
    renderCartDetailQuantity(listCartDetail.length)
    $('#subtotal, #total').text('$' + result.totalAmount)
}

async function increment(productId) {
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
    const result = await response.json();
    let listCartDetail = result.cartDetailResDTOList;
    $('.renderCartDetail').remove();
    renderCartDetail(listCartDetail);
    $('#subtotal, #total').text('$' + result.totalAmount)
}

async function decrement(productId) {
    const cartDetailReqDTO = {
        productId
    }
    const response = await fetch("api/carts/minus-product", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(cartDetailReqDTO)
    });
    const result = await response.json();
    let listCartDetail = result.cartDetailResDTOList;
    $('.renderCartDetail').remove();
    renderCartDetail(listCartDetail);
    $('#subtotal, #total').text('$' + result.totalAmount)
}

async function deleteCartDetail(productId) {
    const confirmed = confirm("Are you sure to remove this cart item?");
    if (confirmed) {
        const cartDetailReqDTO = {
            productId
        }
        const response = await fetch("api/carts/delete", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(cartDetailReqDTO)
        });
        if (response.ok) {
            alert("Delete product successfully!")
            const result = await response.json();
            let listCartDetail = result.cartDetailResDTOList;
            $('.renderCartDetail').remove();
            renderCartDetail(listCartDetail);
            renderCartDetailQuantity(listCartDetail.length);
            $('#subtotal, #total').text('$' + result.totalAmount)
        }
    }
}

$(document).ready(async function () {
    await getAllCartDetail();
})