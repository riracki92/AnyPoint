package com.anypoint.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PAYMENT")
public class Payment {

    @Id
    private UUID id;

    @Min(0)
    @Max(Integer.MAX_VALUE)
    @Column(name = "AMOUNT")
    private float amount;

    @Column(name = "POINTS")
    private int points;

    @Nullable
    @Size(min = 3, max = 20)
    @Column(name = "BANK_NAME")
    private String bankName;

    @Nullable
    @Size(min = 16, max = 16)
    @Column(name = "BANK_ACCOUNT_NUMBER")
    private String bankAccountNumber;

    @Nullable
    @Size(min = 4, max = 4)
    @Column(name = "LAST_4")
    private String last4;

    @Size(min = 16, max = 16)
    @Column(name = "CHEQUE_NUMBER")
    private String chequeNumber;

    @Column(name = "DATE")
    private Date date;
}
