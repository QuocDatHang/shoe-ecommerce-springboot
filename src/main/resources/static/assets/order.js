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

$('.orderElement').on('click', (event) => {
    event.target.style.backgroundColor = "rgb(196 237 255)"
    $('.productElement').removeAttr('style');
})

$('.productElement').on('click', (event) => {
    event.target.style.backgroundColor = "rgb(196 237 255)"
    $('.orderElement').removeAttr('style');
})

async function getALlOrder() {
    const response = await fetch("/api/orders");
    if (response.ok) {
        const result = await response.json();
        renderOrder(result);
    } else {
        toastr.error("Cannot found order")
    }

}

function renderOrder(orderList) {
    orderList.forEach((order, index) => {
        let strOrder = `
                <tr>
                    <td>${order.orderDate}</td>
                    <td>${order.orderDetailResDTOList.length}</td>
                    <td>$${order.subtotal}</td>
                    <td>${order.shipping}</td>
                    <td>$${order.totalAmount}</td>
                    <td><span>${order.status}</span></td>
                    <td>${order.customer.fullName}</td>
                    <td>
                        <a class="btnDetail" data-bs-toggle="modal" data-bs-target="#modalOrderDetail-${index}">
                            <i class="fa-solid fa-list"></i>
                        </a>
                    </td>
                </tr>
                `;
        $("#renderOrder").append(strOrder);

        let strModal = `
                <div class="modal fade" id="modalOrderDetail-${index}" tabindex="-1">
                        <div class="modal-dialog modal-dialog-scrollable">
                            <div class="modal-content modal-order-detail">
                                <div class="modal-header">
                                    <h2 class="modal-title fs-5">Order Details</h2>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="modal-body-item">
                                        <h6>Order Information</h6>
                                        <div class="d-flex justify-content-between info">
                                            <div>Subtotal</div>
                                            <div>${order.subtotal}</div>
                                        </div>
                                        <div class="d-flex justify-content-between info">
                                            <div>Shipping</div>
                                            <div>${order.shipping}</div>
                                        </div>
                                        <div class="d-flex justify-content-between info">
                                            <div>Total</div>
                                            <div>${order.totalAmount}</div>
                                        </div>
                                    </div>
                                    <div class="modal-body-item">
                                        <h6>Customer Information</h6>
                                        <div class="d-flex justify-content-between info">
                                            <div>Fullname</div>
                                            <div>${order.customer.fullName}</div>
                                        </div>
                                        <div class="d-flex justify-content-between info">
                                            <div>Email</div>
                                            <div>${order.customer.email}</div>
                                        </div>
                                        <div class="d-flex justify-content-between info">
                                            <div>Mobile</div>
                                            <div>${order.customer.mobile}</div>
                                        </div>
                                        <div class="d-flex justify-content-between info">
                                            <div>Address</div>
                                            <div>${order.customer.address}</div>
                                        </div>
                                    </div>
                                    <div class="modal-body-item">
                                        <h6>Order details</h6>
                                        <table class="table table-striped tbOrderDetail-${index}">

                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    `
        $(".orderDetailModal").append(strModal);

        order.orderDetailResDTOList.forEach(orderDetail => {
            let strOrderDetail = `
                            <tr>
                                <td><img src="${orderDetail.img}" alt="Not found"></td>
                                <td>${orderDetail.title}</td>
                                <td>${orderDetail.quantity}</td>
                                <td>$${orderDetail.price}</td>
                                <td>$${orderDetail.totalPrice}</td>
                            </tr>
                            `
            $(`.tbOrderDetail-${index}`).append(strOrderDetail);
        })
    })
}
$(document).ready(async function () {
    await getALlOrder();
    $('.orderElement').click();
})

