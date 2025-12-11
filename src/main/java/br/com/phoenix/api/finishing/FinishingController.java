package br.com.phoenix.api.finishing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/finishing")
public class FinishingController {

    private final FinishingRepository repo;

    public FinishingController(FinishingRepository repo) {
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
    public ResponseEntity<Finishing> findOne(@PathVariable("id") Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Finishing> update(@PathVariable("id") Long id,
            @RequestBody Finishing body) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        body.setId(id);
        return ResponseEntity.ok(repo.save(body));
    }

    @PostMapping
    public Finishing create(@RequestBody Finishing body) {
        body.setId(null);
        return repo.save(body);
    }

    @GetMapping
    public java.util.List<Finishing> list() {
        return repo.findAll();
    }

}
