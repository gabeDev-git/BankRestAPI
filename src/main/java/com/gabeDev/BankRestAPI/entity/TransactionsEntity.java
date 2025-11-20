package com.gabeDev.BankRestAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
public class TransactionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_id", nullable = false)
    private Long senderId;
    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "sender_wallet_id", nullable = false)
    private WalletEntity senderWallet;

    @ManyToOne
    @JoinColumn(name = "receiver_wallet_id", nullable = false)
    private WalletEntity receiverWallet;
}
