package com.example.service.cartDetail;

import com.example.Model.Cart;
import com.example.Model.CartDetail;
import com.example.Model.Customer;
import com.example.Model.Product;
import com.example.Model.dto.CartDetailReqDTO;
import com.example.Model.dto.CartDetailResDTO;
import com.example.Model.dto.CartResDTO;
import com.example.repository.ICartDetailRepository;
import com.example.service.IGeneralService;
import com.example.service.cart.CartService;
import com.example.service.product.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartDetailService implements IGeneralService<CartDetail, Long> {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @Autowired
    private ICartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetail> findAll() {
        return null;
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return Optional.empty();
    }

    public Optional<CartDetail> findByCartAndProduct(Cart cart, Product product) {
        return cartDetailRepository.findByCartAndProduct(cart, product);
    }

    public List<CartDetail> findAllByCart(Cart cart) {
        return cartDetailRepository.findAllByCart(cart);
    }

    @Override
    public void update(CartDetail cartDetail) {

    }

    @Override
    public void create(CartDetail cartDetail) {

    }

    public CartResDTO create(CartDetailReqDTO cartDetailReqDTO, Customer customer) {
        Product product = productService.findById(cartDetailReqDTO.getProductId()).orElseThrow(()
                -> new RuntimeException("Cannot found product"));

        BigDecimal amount = product.getNewPrice();
        int quantity = 1;
        BigDecimal totalAmount = amount.multiply(BigDecimal.valueOf(quantity));

        Cart cart = new Cart();
        cart.setTotalAmount(totalAmount);
        cart.setCustomer(customer);
        cartService.create(cart);

        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setAmount(amount);
        cartDetail.setQuantity(quantity);
        cartDetail.setProduct(product);
        cartDetailRepository.save(cartDetail);

        List<CartDetailResDTO> cartDetailResDTOList = cartDetailRepository.getAllCartDetailResDTOByCart(cart);

        CartResDTO cartResDTO = new CartResDTO();
        cartResDTO.setTotalAmount(totalAmount);
        cartResDTO.setCartDetailResDTOList(cartDetailResDTOList);
        return cartResDTO;
    }

    public CartResDTO update(CartDetailReqDTO cartDetailReqDTO, Cart cart) {
        Product product = productService.findById(cartDetailReqDTO.getProductId()).orElseThrow(()
                -> new RuntimeException("Cannot found product"));

        Optional<CartDetail> cartDetailOptional = findByCartAndProduct(cart, product);
        if (cartDetailOptional.isEmpty()) {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setAmount(product.getNewPrice());
            cartDetail.setQuantity(1);
            cartDetail.setProduct(product);
            cartDetailRepository.save(cartDetail);
        }
        else {
            CartDetail cartDetail = cartDetailOptional.get();
            cartDetail.setQuantity(cartDetail.getQuantity() + 1);
            cartDetailRepository.save(cartDetail);
        }
        BigDecimal totalAmount = cartDetailRepository.getTotalAmount(cart);
        cart.setTotalAmount(totalAmount);
        cartService.update(cart);

        List<CartDetailResDTO> cartDetailResDTOList = cartDetailRepository.getAllCartDetailResDTOByCart(cart);

        CartResDTO cartResDTO = new CartResDTO();
        cartResDTO.setTotalAmount(totalAmount);
        cartResDTO.setCartDetailResDTOList(cartDetailResDTOList);
        return cartResDTO;
    }

    public CartResDTO minusProduct(CartDetailReqDTO cartDetailReqDTO, Cart cart) {
        Product product = productService.findById(cartDetailReqDTO.getProductId()).orElseThrow(()
                -> new RuntimeException("Cannot found product"));

        Optional<CartDetail> cartDetailOptional = findByCartAndProduct(cart, product);
        if (cartDetailOptional.isEmpty()) {
            throw new RuntimeException("Product not exist in your cart");
        }
        else {
            CartDetail cartDetail = cartDetailOptional.get();
            if (cartDetail.getQuantity() <= 1) {
                cartDetail.setQuantity(1);
                cartDetailRepository.save(cartDetail);
            }
            else {
                cartDetail.setQuantity(cartDetail.getQuantity() - 1);
                cartDetailRepository.save(cartDetail);
            }
        }
        BigDecimal totalAmount = cartDetailRepository.getTotalAmount(cart);
        cart.setTotalAmount(totalAmount);
        cartService.update(cart);

        List<CartDetailResDTO> cartDetailResDTOList = cartDetailRepository.getAllCartDetailResDTOByCart(cart);

        CartResDTO cartResDTO = new CartResDTO();
        cartResDTO.setTotalAmount(totalAmount);
        cartResDTO.setCartDetailResDTOList(cartDetailResDTOList);
        return cartResDTO;
    }

    public CartResDTO delete(CartDetailReqDTO cartDetailReqDTO, Cart cart) {
        Product product = productService.findById(cartDetailReqDTO.getProductId()).orElseThrow(()
                -> new RuntimeException("Cannot found product"));

        Optional<CartDetail> cartDetailOptional = findByCartAndProduct(cart, product);
        if (cartDetailOptional.isEmpty()) {
            throw new RuntimeException("Product not exist in your cart");
        }
        else {
            CartDetail cartDetail = cartDetailOptional.get();
            cartDetailRepository.delete(cartDetail);
        }
        BigDecimal totalAmount = cartDetailRepository.getTotalAmount(cart);
        cart.setTotalAmount(totalAmount);
        cartService.update(cart);

        List<CartDetailResDTO> cartDetailResDTOList = cartDetailRepository.getAllCartDetailResDTOByCart(cart);

        CartResDTO cartResDTO = new CartResDTO();
        cartResDTO.setTotalAmount(totalAmount);
        cartResDTO.setCartDetailResDTOList(cartDetailResDTOList);
        return cartResDTO;
    }

    public void delete(Cart cart) {
        List<CartDetail> cartDetailList = findAllByCart(cart);
        cartDetailRepository.deleteAll(cartDetailList);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
