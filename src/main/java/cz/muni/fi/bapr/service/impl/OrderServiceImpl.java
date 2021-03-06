package cz.muni.fi.bapr.service.impl;

import cz.muni.fi.bapr.dao.CartDAO;
import cz.muni.fi.bapr.dao.OrderDAO;
import cz.muni.fi.bapr.dao.OrderProductDAO;
import cz.muni.fi.bapr.entity.*;
import cz.muni.fi.bapr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@code OrderService} service interface
 *
 * @author Andrej Kuročenko <andrej@kurochenko.net>
 */
@Service
@Transactional
public class OrderServiceImpl extends AbstractServiceImpl<Order, OrderDAO> implements OrderService {

    private OrderDAO orderDAO;

    private CartDAO cartDAO;

    private OrderProductDAO orderProductDAO;


    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setCartDAO(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Autowired
    public void setOrderProductDAO(OrderProductDAO orderProductDAO) {
        this.orderProductDAO = orderProductDAO;
    }

    @Override
    public OrderDAO getDao() {
        return orderDAO;
    }

    @Override
    public void createAndMoveProducts(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order is null");
        }

        orderDAO.create(order);

        for (Cart cart : cartDAO.findByCustomer(order.getCustomer())) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setAmount(cart.getAmount());
            orderProduct.setProduct(cart.getProduct());
            orderProduct.setOrder(order);
            orderProductDAO.create(orderProduct);
            cartDAO.remove(cart);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findByDeliveryType(DeliveryType deliveryType) {
        if (deliveryType == null) {
            throw new IllegalArgumentException("Delivery type is null");
        }

        return orderDAO.findByDeliveryType(deliveryType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findByPaymentType(PaymentType paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("Payment type is null");
        }

        return orderDAO.findByPaymentType(paymentType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findNotAttendedByCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is null");
        }

        return orderDAO.findNotAttendedByCustomer(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAttendedByCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is null");
        }

        return orderDAO.findAttendedByCustomer(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findNotAttended() {
        return orderDAO.findNotAttended();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAttended() {
        return orderDAO.findAttended();
    }
}
