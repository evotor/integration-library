package ru.evotor.framework.receipt;

/**
 * Created by a.kuznetsov on 30/04/2017.
 */

public class PrintGroup {
    private String identifier;
    private Type type;
    private String orgName;
    private String orgInn;
    private String orgAddress;
    private TaxationSystem taxationSystem;

    public PrintGroup(
            String identifier,
            Type type,
            String orgName,
            String orgInn,
            String orgAddress,
            TaxationSystem taxationSystem
    ) {
        this.identifier = identifier;
        this.type = type;
        this.orgName = orgName;
        this.orgInn = orgInn;
        this.orgAddress = orgAddress;
        this.taxationSystem = taxationSystem;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getOrgInn() {
        return orgInn;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public TaxationSystem getTaxationSystem() {
        return taxationSystem;
    }

    public enum Type {
        /**
         * Кассовый чек, напечатанный средствами ККМ
         */
        CASH_RECEIPT,
        /**
         * квитанция
         */
        INVOICE,
        /**
         * ЕНВД чек, напечатанный строками
         */
        STRING_UTII
    }
}
