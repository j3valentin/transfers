package com.ixaris.interview.transfers.rest;

import com.ixaris.interview.transfers.domain.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfer")
public class TransferRestController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    void transfer(@RequestBody Transfer transfer) {
        if (!transfer.getSourceAcct().equals(0l)) {
            Account account = accountRepository.findById(transfer.getSourceAcct())
                    .orElse(new Account(transfer.getSourceAcct()));
            account.subtractAmount(transfer.getAmount());
            accountRepository.save(account);
        }
        Account account = accountRepository.findById(transfer.getDestinationAcct())
                .orElse(new Account(transfer.getDestinationAcct()));
        account.addAmount(transfer.getAmount());
        accountRepository.save(account);
    }

//    @PersistenceContext
//    EntityManager entityManager;
//
//    @PostMapping
//    void transfer(@RequestBody Transfer transfer) {
//        entityManager.flush();
//        entityManager.clear();
//        entityManager.getTransaction().begin();
//        Query query = entityManager.createQuery("UPDATE Account a SET a.amount = a.amount + 1 WHERE number = 2");
//        query.executeUpdate();
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
}
