package ru.evotor.framework.receipt;

import java.math.BigDecimal;

import ru.evotor.framework.inventory.Measure;
import ru.evotor.framework.inventory.ProductType;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public class Position {
    /**
     * UUID позиции
     */
    private String uuid;
    /**
     * UUID товара.
     */
    private String productUuid;
    /**
     * Вид товара.
     */
    private ProductType productType;
    /**
     * Наименование.
     */
    private String name;
    /**
     * Единица измерения.
     */
    private Measure measure;
    /**
     * Цена без скидок.
     */
    private BigDecimal price;
    /**
     * Цена с учетом скидки на позицию.
     */
    private BigDecimal priceWithDiscountPosition;
    /**
     * Количество.
     */
    private BigDecimal quantity;
    /**
     * Штрихкод, по которому товар был найден.
     */
    private String barcode;
    /**
     * Алкогольная марка.
     */
    private String mark;
    /**
     * Крепость.
     */
    private BigDecimal alcoholByVolume;
    /**
     * Код вида продукции ФСРАР.
     */
    private Long alcoholProductKindCode;
    /**
     * Объём тары.
     */
    private BigDecimal tareVolume;
    /**
     * Группа печати.
     */
    private PrintGroup printGroup;

    public Position(
            String uuid,
            String productUuid,
            ProductType productType,
            String name,
            Measure measure,
            BigDecimal price,
            BigDecimal priceWithDiscountPosition,
            BigDecimal quantity,
            String barcode,
            String mark,
            BigDecimal alcoholByVolume,
            Long alcoholProductKindCode,
            BigDecimal tareVolume,
            PrintGroup printGroup
    ) {
        this.uuid = uuid;
        this.productUuid = productUuid;
        this.productType = productType;
        this.name = name;
        this.measure = measure;
        this.price = price;
        this.priceWithDiscountPosition = priceWithDiscountPosition;
        this.quantity = quantity;
        this.barcode = barcode;
        this.mark = mark;
        this.alcoholByVolume = alcoholByVolume;
        this.alcoholProductKindCode = alcoholProductKindCode;
        this.tareVolume = tareVolume;
        this.printGroup = printGroup;
    }

    public String getUuid() {
        return uuid;
    }

    public String getProductUuid() {
        return productUuid;
    }

    public ProductType getProductType() {
        return productType;
    }

    public String getName() {
        return name;
    }

    public Measure getMeasure() {
        return measure;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPriceWithDiscountPosition() {
        return priceWithDiscountPosition;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getMark() {
        return mark;
    }

    public BigDecimal getAlcoholByVolume() {
        return alcoholByVolume;
    }

    public Long getAlcoholProductKindCode() {
        return alcoholProductKindCode;
    }

    public BigDecimal getTareVolume() {
        return tareVolume;
    }

    public PrintGroup getPrintGroup() {
        return printGroup;
    }
}
