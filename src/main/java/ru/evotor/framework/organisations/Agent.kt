package ru.evotor.framework.organisations

class Agent(
        val type: Type?,
        val operation: String?,
        phone: String?,
        val transactionOperator: TransactionOperator
) : Organisation(null, null, null, phone) {

    enum class Type {
        BANK_PAYMENT_AGENT,
        BANK_PAYMENT_SUBAGENT,
        PAYMENT_AGENT,
        PAYMENT_SUBAGENT,
        COMMISSIONER,
        AGENT,
        ATTORNEY_IN_FACT
    }

    class TransactionOperator(
            name: String?,
            inn: String?,
            address: String?,
            phone: String?
    ) : Organisation(name, inn, address, phone)

}