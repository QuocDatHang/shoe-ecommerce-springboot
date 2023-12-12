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
    $('.renderCartDetail').remove();
    $('#subtotal, #total').text('$0');
    $('.cartDetailQuantity').remove();
    const response = await fetch("api/carts")
    const result = await response.json();
    const listCartDetail = result.cartDetailResDTOList;
    renderCartDetail(listCartDetail);
    renderCartDetailQuantity(listCartDetail.length);
    $('#subtotal, #total').text('$' + result.totalAmount);
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

function deleteCartDetail(productId) {
    Swal.fire({
        title: "Are you sure to remove this cart item?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
    }).then(async (result) => {
        if (result.isConfirmed) {
            const cartDetailReqDTO = {
                productId
            }
            const response = await fetch("api/carts/delete-cart-detail", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(cartDetailReqDTO)
            });
            if (response.ok) {
                const result = await response.json();
                let listCartDetail = result.cartDetailResDTOList;
                $('.renderCartDetail').remove();
                renderCartDetail(listCartDetail);
                renderCartDetailQuantity(listCartDetail.length);
                $('#subtotal, #total').text('$' + result.totalAmount)
            }
            Swal.fire({
                title: "Deleted!",
                text: "Your cart item has been deleted.",
                icon: "success"
            });
        }
    });
}

$('#checkout').on('click', async () => {
    Swal.fire({
        title: "CONFIRM CHECKOUT",
        text: "You won't be able to revert this!",
        icon: "result",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Confirm"
    }).then(async (result) => {
        if (result.isConfirmed) {
            const response = await fetch("api/orders/checkout")
            if (response.ok) {
                const res = await fetch("api/carts/delete-cart")
                if (res.ok) {
                    Swal.fire({
                        title: "Checkout successfully!",
                        text: "",
                        icon: "success"
                    });
                    await getAllCartDetail();
                } else {
                    Swal.fire({
                        title: "Delete cart failed!",
                        text: "",
                        icon: "error"
                    });
                }
            } else {
                Swal.fire({
                    title: "Checkout failed!",
                    text: "",
                    icon: "error"
                });
            }
        }
    });

})

$(document).ready(async function () {
    await getAllCartDetail();

})