package indradwiprabowo.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments_credit_card")
public class PaymentCreditCard extends Payment {

    @Column(name = "master_card")
    private String masterCard;

    @Column(name = "bank")
    private String bank;

    public String getMasterCard() {
        return masterCard;
    }

    public void setMasterCard(String masterCard) {
        this.masterCard = masterCard;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
