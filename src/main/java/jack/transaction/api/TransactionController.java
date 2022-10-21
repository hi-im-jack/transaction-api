package jack.transaction.api;

import com.google.gson.Gson;
import jack.transaction.api.data.Transaction;
import jack.transaction.api.data.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    public TransactionRepository transactionRepository;

    @PostMapping("/add")
    public void addTransaction(@Valid @RequestBody Transaction transaction) {
        transaction.setId(String.valueOf(transactionRepository.findAll().size() + 1));
        transactionRepository.save(transaction);
    }

    @GetMapping("/list")
    public String listTransactions() {
        if(transactionRepository.findAll().isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return new Gson().toJson(transactionRepository.findAll());
    }

    @PostMapping("/edit/{id}")
    public void editTransaction(@PathVariable("id") String id, @Valid @RequestBody Transaction transaction) {
        if(transactionRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        transactionRepository.save(transaction);
    }

    @GetMapping("/delete/{id}")
    public void deleteTransaction(@PathVariable("id") String id) {
        if(transactionRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        transactionRepository.delete(transactionRepository.findById(id).get());
    }
}
