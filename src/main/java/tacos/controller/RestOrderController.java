package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.expression.spel.ast.OpOr;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.data.OrderRepository;
import tacos.domain.Order;
import tacos.domain.User;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/orders")
@SessionAttributes("order")
public class RestOrderController {

    private OrderRepository orderRepository;

    @Autowired
    public RestOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
        Order order = orderRepository.findById(orderId).get();

        if (order.getDeliveryName() == null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        return orderRepository.save(order);
    }


    @DeleteMapping("/{orderId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {

        }
    }

}
