package br.com.phoenix.api.substrates;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/substrates")
public class SubstratesController {

    private final SubstratesRepository repo;

    public SubstratesController(SubstratesRepository repo) {
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
    public ResponseEntity<Substrates> findOne(@PathVariable("id") Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Substrates> update(@PathVariable("id") Long id,
            @RequestBody Substrates body) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        body.setId(id);
        return ResponseEntity.ok(repo.save(body));
    }

    @PostMapping
    public Substrates create(@RequestBody Substrates body) {
        body.setId(null);
        return repo.save(body);
    }

    @GetMapping
    public java.util.List<Substrates> list() {
        return repo.findAll();
    }

}
