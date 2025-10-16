package br.com.phoenix.api.quote.type;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quote/type")
public class QuoteTypeController {

    private final QuoteTypeRepository repo;

    public QuoteTypeController(QuoteTypeRepository repo) {
        this.repo = repo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteType> findOne(@PathVariable("id") Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuoteType> update(@PathVariable("id") Long id,
            @RequestBody QuoteType body) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        body.setId(id);
        return ResponseEntity.ok(repo.save(body));
    }

    @PostMapping
    public QuoteType create(@RequestBody QuoteType body) {
        body.setId(null);
        return repo.save(body);
    }

    @GetMapping
    public java.util.List<QuoteType> list() {
        return repo.findAll();
    }

}
