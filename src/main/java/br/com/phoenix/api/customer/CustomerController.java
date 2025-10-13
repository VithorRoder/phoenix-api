package br.com.phoenix.api.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository repo;

    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findOne(@PathVariable("id") Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") Long id,
            @RequestBody Customer body) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        body.setId(id);
        return ResponseEntity.ok(repo.save(body));
    }

    @PostMapping
    public Customer create(@RequestBody Customer body) {
        body.setId(null);
        return repo.save(body);
    }

    @GetMapping
    public java.util.List<Customer> list() {
        return repo.findAll();
    }
}
