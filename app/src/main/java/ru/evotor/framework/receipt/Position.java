package ru.evotor.framework.receipt;

import java.math.BigDecimal;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public class Position {
    private String productUuid;
    private BigDecimal price;
    private BigDecimal quantity;
    private String name;

    public Position(String productUuid, BigDecimal price, BigDecimal quantity, String name) {
        this.productUuid = productUuid;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
    }

    public String getProductUuid() {
        return productUuid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
}
