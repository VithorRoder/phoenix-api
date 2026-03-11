package br.com.phoenix.api.machines;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/machines")
public class MachinesController {

    private final MachinesRepository repo;

    public MachinesController(MachinesRepository repo) {
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
    public ResponseEntity<Machines> findOne(@PathVariable("id") Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Machines> update(@PathVariable("id") Long id,
            @RequestBody Machines body) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        body.setId(id);
        return ResponseEntity.ok(repo.save(body));
    }

    @PostMapping
    public Machines create(@RequestBody Machines body) {
        body.setId(null);
        return repo.save(body);
    }

    @GetMapping
    public java.util.List<Machines> list() {
        return repo.findAll();
    }

}
